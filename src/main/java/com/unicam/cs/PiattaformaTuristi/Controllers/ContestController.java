package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ContenutoContest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ContestController {
    @Autowired
    ComuneRepository comuneRepository;

    private Comune comune;
    private UtentiController utentiController;

    public ContestController(){

    }

    public ContestController(Comune comune){
        this.comune = comune;
        this.utentiController = new UtentiController();
    }

    public void creaContest(Contest contest, UtenteAutenticato animatore){
        contest.setCreatoreContest(animatore);
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.inserisciContestAperto(contest);
        this.comuneRepository.save(c);
    }


    public Contest selezionaContest(int idContest){ return this.comune.getContest(idContest); }

    public List<Contest> getContestUtente(UtenteAutenticato utente){
        return comune.getContestAperti().stream().filter(c -> c.getCreatoreContest().equals(utente)).toList();
    }

    public List<ContenutoContest> getContenutiContest(Contest contest){
        Contest contenuti = comune.getContestAperti().stream().filter(c -> c.equals(contest)).findFirst().orElse(null);
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

    public List<Contest> getContestChiusi(){ return this.comuneRepository.findById("Camerino").get().getContestChiusi(); }

    public List<Contest> getContestAperti(){ return this.comuneRepository.findById("Camerino").get().getContestAperti(); }

    public void invitaUtenti(Contest contest, List<UtenteAutenticato> utentiDaInvitare){
        contest.inserisciTuttiInvitati(utentiDaInvitare);
    }

    public void partecipaContest(Contest contest, Contenuto contenuto, UtenteAutenticato utente){
        int idContenutoContest = contest.getContenutiCaricati().isEmpty() ? 1 : contest.getContenutiCaricati().getLast().getIdContenutoContest()+1;
        contest.inserisciContenuto(new ContenutoContest(idContenutoContest,contenuto,utente));
    }

    //Metodo per far selezionare all'utente il contest
    //Per semplificare l'utilizzo non è stato usato nella vista
    public List<Contest> getTuttiContestPartecipabili(UtenteAutenticato utente){
        List<Contest> contestPrivatiNonPartecipati = this.comune.getContestAperti().stream().filter(c ->
                c.getPrivato() &&
                c.getInvitati().contains(utente) &&
                c.getContenutiCaricati().stream().noneMatch(contenuto -> contenuto.getUtente().equals(utente))).toList();
        List<Contest> contestPubbliciNonPartecipati = this.comune.getContestAperti().stream().filter(c ->
                !c.getPrivato() &&
                c.getContenutiCaricati().stream().noneMatch(contenuto -> contenuto.getUtente().equals(utente))).toList();

        return Stream.concat(contestPrivatiNonPartecipati.stream(), contestPubbliciNonPartecipati.stream()).toList();
    }

    //Metodo di utilità per far selezionare all'utente il contest
    //Per semplificare l'utilizzo non è stato usato nella vista
    public List<UtenteAutenticato> getUtentiInvitabili(Contest contest){
        List<UtenteAutenticato> utentiInvitati = contest.getInvitati();
        List<UtenteAutenticato> contributori = utentiController.getTuttiContributori();
        contributori.removeAll(utentiInvitati);
        return contributori;
    }
}
