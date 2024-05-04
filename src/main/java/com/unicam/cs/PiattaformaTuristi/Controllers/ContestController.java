package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.ContenutoContest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

import java.util.List;
import java.util.stream.Stream;

public class ContestController {
    private Comune comune;
    private UtentiController utentiController;

    public ContestController(Comune comune){
        this.comune = comune;
        this.utentiController = new UtentiController();
    }

    public void creaContest(Contest contest, UtenteAutenticato animatore){
        if(contest.getTitolo()==null || contest.getTitolo().isEmpty())
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        contest.setIdContest(comune.getLastContestId()); //TODO rimuovere con aggiunto di database
        contest.setCreatoreContest(animatore);
        this.comune.inserisciContestAperto(contest);
    }


    public Contest getContest(Contest contest){
        return comune.getContestAperti().stream().filter(c -> c.equals(contest)).findAny().orElse(null);
    }

    public List<Contest> getContestUtente(UtenteAutenticato utente){
        return comune.getContestAperti().stream().filter(c -> c.getCreatoreContest().equals(utente)).toList();
    }

    public List<ContenutoContest> getContenutiContest(Contest contest){
        Contest contenuti = comune.getContestAperti().stream().filter(c -> c.equals(contest)).findAny().orElse(null);
        if(contenuti==null)
            throw new RuntimeException("Contest senza partecipanti");
        return contenuti.getContenutiCaricati();
    }

    public void setVincitoreContest(Contest c, ContenutoContest vincitore){
        c.setContenutoVincitore(vincitore);
        this.comune.chiudiContest(c);
    }

    public List<Contest> getContestPrivati(UtenteAutenticato utente){
        return comune.getContestAperti().stream().filter(c -> c.getPrivato() && c.getCreatoreContest().equals(utente)).toList();
    }

    public void invitaUtenti(Contest contest, List<UtenteAutenticato> utentiDaInvitare){
        contest.addTuttiInvitati(utentiDaInvitare);
    }

    public void partecipaContest(Contest contest, Contenuto contenuto, UtenteAutenticato utente){
        contest.addContenuto(new ContenutoContest(contenuto,utente));
    }

    //Metodo per far selezionare all'utente il contest
    //Per semplificare l'utilizzo non è stato usato nella vista
    public List<Contest> getTuttiContestPartecipabili(UtenteAutenticato utente){
        return Stream.concat(
                this.comune.getContestAperti().stream().filter(c -> c.getInvitati().contains(utente)).toList().stream(),
                this.comune.getContestAperti().stream().filter(
                        c -> c.getContenutiCaricati().stream().noneMatch(contenuto -> contenuto.getUtente().equals(utente)
                        )).toList().stream()).toList();
    }

    //Metodo di utilità per far selezionare all'utente il contest
    //Per semplificare l'utilizzo non è stato usato nella vista
    private List<UtenteAutenticato> getUtentiInvitabili(Contest contest){
        List<UtenteAutenticato> utentiInvitati = contest.getInvitati();
        List<UtenteAutenticato> contributori = utentiController.getTuttiContributori();
        contributori.removeAll(utentiInvitati);
        return contributori;
    }
}
