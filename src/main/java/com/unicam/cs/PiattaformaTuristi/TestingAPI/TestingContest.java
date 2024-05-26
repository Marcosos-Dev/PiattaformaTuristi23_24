package com.unicam.cs.PiattaformaTuristi.TestingAPI;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaAnimatore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaContributore;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestingContest {
    private Comune c;
    private GestoreUtenti gestore;
    private UtenteAutenticato contributor1;
    private UtenteAutenticato contributor2;

    public TestingContest() throws URISyntaxException {
        setup();

        System.out.println("Creazione contest pubblico");
        creaContest("Pubblico");
        System.out.println("\nCreazione contest privato");
        creaContest("Privato");
        System.out.println("\nPartecipa al contest privato (senza essere invitati)");
        partecipaContestSenzaInvito();
        System.out.println("\nInvita al contest privato");
        invitaAlContest();
        System.out.println("\nPartecipa al contest pubblico");
        partecipaContestPubblico();
        System.out.println("\nPartecipa al contest privato");
        partecipaContesPrivato();
        System.out.println("\nSeleziona vincitore contest pubblico");
        selezionaVincitoreContestPubblico();
        System.out.println("\nSeleziona vincitore contest privato");
        selezionaVincitoreContestPrivato();
        System.out.println("\nVisualizza contest");
        testVisualizzaContest();
    }

    private void setup(){
        c = new Comune("Ancona");
        gestore = new GestoreUtenti();
        contributor1 = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE);
        contributor2 = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
    }

    public void testVisualizzaContest(){
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);
        System.out.println(i.visualizzaContest(1));
        System.out.println(i.visualizzaContest(2));
    }

    public void creaContest(String tipo){
        UtenteAutenticato animatore = new UtenteAutenticato(RuoloUtente.ANIMATORE);
        InterfacciaAnimatore i = new InterfacciaAnimatore(c,animatore,gestore);

        Contest contest;
        if(tipo.equals("Pubblico")) contest = new Contest("Primo","primo contenuto",false);
        else  contest = new Contest("Secondo","secondo contenuto",true);
        i.creaContest(contest);
        System.out.println("-----------Lista contest aperti-----------");
        c.stampaContestAperti();
        System.out.println("-----------Lista contest chiusi-----------");
        c.stampaContestChiusi();
    }

    public void partecipaContestSenzaInvito(){
        InterfacciaContributore i = new InterfacciaContributore(c,contributor1,gestore);
        ContestController contestController = new ContestController(c);

        List<Contest> contestTrovati = contestController.getTuttiContestPartecipabili(contributor1);
        if(contestTrovati.size()==1)
            System.out.println("-----------OK solo 1 contest pubblico trovato-----------");
        else
            System.out.println("-----------ERRORE-----------");
    }

    public void partecipaContestPubblico() throws URISyntaxException {
        InterfacciaContributore i1 = new InterfacciaContributore(c,contributor1,gestore);
        InterfacciaContributore i2 = new InterfacciaContributore(c,contributor2,gestore);

        File file = Paths.get(Objects.requireNonNull(getClass().getResource("/correct.pdf")).toURI()).toFile();
        i1.partecipaContest(c.getContestAperti().get(0),new Contenuto(file,"contributor 1"));
        i2.partecipaContest(c.getContestAperti().get(0),new Contenuto(file,"contributor autorizzato 2"));

        if(!c.getContestAperti().get(0).getContenutiCaricati().isEmpty())
            System.out.println("-----------Partecipazione effettuata con successo-----------");
        else
            System.out.println("-----------ERRORE-----------");
    }

    public void partecipaContesPrivato() throws URISyntaxException {
        InterfacciaContributore i1 = new InterfacciaContributore(c,contributor1,gestore);
        InterfacciaContributore i2 = new InterfacciaContributore(c,contributor2,gestore);

        File file = Paths.get(Objects.requireNonNull(getClass().getResource("/correct.pdf")).toURI()).toFile();
        i1.partecipaContest(c.getContestAperti().get(1),new Contenuto(file,"contributor 1"));
        i2.partecipaContest(c.getContestAperti().get(1),new Contenuto(file,"contributor autorizzato 2"));

        if(!c.getContestAperti().get(1).getContenutiCaricati().isEmpty())
            System.out.println("-----------Partecipazione effettuata con successo-----------");
        else
            System.out.println("-----------ERRORE-----------");
    }

    public void invitaAlContest(){
        UtenteAutenticato animatore = new UtenteAutenticato(RuoloUtente.ANIMATORE);
        InterfacciaAnimatore i = new InterfacciaAnimatore(c,animatore,gestore);

        List<UtenteAutenticato> utentiDaInvitare = new ArrayList<>();
        utentiDaInvitare.add(contributor1);
        utentiDaInvitare.add(contributor2);
        i.invitaUtente(c.getContestAperti().get(1),utentiDaInvitare);

        if(c.getContestAperti().get(1).getInvitati().size()==2)
            System.out.println("-----------OK invito effettuato con successo-----------");
        else
            System.out.println("-----------ERRORE-----------");
    }

     public void selezionaVincitoreContestPubblico(){
        UtenteAutenticato animatore = new UtenteAutenticato(RuoloUtente.ANIMATORE);
        InterfacciaAnimatore i = new InterfacciaAnimatore(c,animatore,gestore);

        Contest contest = c.getContestAperti().get(0);
        i.selezionaVincitoreContest(contest,contest.getContenutiCaricati().get(0));

        System.out.println("-----------Contenuto vincitore-----------");
        System.out.println(contest);
    }

    public void selezionaVincitoreContestPrivato(){
        UtenteAutenticato animatore = new UtenteAutenticato(RuoloUtente.ANIMATORE);
        InterfacciaAnimatore i = new InterfacciaAnimatore(c,animatore,gestore);

        Contest contest = c.getContestAperti().get(0);
        i.selezionaVincitoreContest(contest,contest.getContenutiCaricati().get(1));

        System.out.println("-----------Contenuto vincitore-----------");
        System.out.println(contest);
    }


}
