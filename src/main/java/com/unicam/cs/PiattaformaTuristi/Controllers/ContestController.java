package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ContenutoContest;
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
    @Autowired
    private UtenteController utenteController;

    public ContestController(){

    }

    /*public ContestController(Comune comune){
        this.comune = comune;
        this.utentiController = new UtentiController();
    }*/

    public void creaContest(Contest contest, UtenteAutenticato animatore){
        contest.setCreatoreContest(animatore);
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.inserisciContestAperto(contest);
        this.comuneRepository.save(c);
    }


    public Contest getContest(int idContest){ return this.comuneRepository.findById("Camerino").get().getContest(idContest); }

    public List<Contest> getContestUtente(UtenteAutenticato utente){ return this.comuneRepository.findById("Camerino").get().getContestAperti().stream().filter(c -> c.getCreatoreContest().equals(utente)).toList(); }

    public List<ContenutoContest> getContenutiContest(int Idcontest){
        Comune comune = this.comuneRepository.findById("Camerino").get();
        Contest contenuti = comune.getContestAperti().stream().filter(c -> c.getIdContest()==Idcontest).findFirst().orElse(null);
        if(contenuti==null)
            throw new RuntimeException("Contest senza partecipanti");
        return contenuti.getContenutiCaricati();
    }

    public void setVincitoreContest(Contest contest, ContenutoContest vincitore){
        contest.setContenutoVincitore(vincitore);
        Comune comune = this.comuneRepository.findById("Camerino").get();
        comune.chiudiContest(contest);
        this.comuneRepository.save(comune);
    }

    public List<Contest> getContestPrivati(UtenteAutenticato utente){ return this.comuneRepository.findById("Camerino").get().getContestAperti().stream().filter(c -> c.getPrivato() && c.getCreatoreContest().equals(utente)).toList(); }

    public List<Contest> getContestChiusi(){ return this.comuneRepository.findById("Camerino").get().getContestChiusi(); }

    public List<Contest> getContestAperti(){ return this.comuneRepository.findById("Camerino").get().getContestAperti(); }

    public void invitaUtenti(Contest contest, List<UtenteAutenticato> utentiDaInvitare){
        contest.aggiungiInvitati(utentiDaInvitare);
        comuneRepository.save(this.comuneRepository.findById("Camerino").get());
    }

    public void partecipaContest(Contest contest, ContenutoContest contenuto){
        contest.aggiungiContenuto(contenuto);
        comuneRepository.save(this.comuneRepository.findById("Camerino").get());
    }

    public List<Contest> getTuttiContestPartecipabili(UtenteAutenticato utente){
        Comune comune = this.comuneRepository.findById("Camerino").get();
        List<Contest> contestPrivatiNonPartecipati = comune.getContestAperti().stream().filter(c ->
                c.getPrivato() &&
                c.getInvitati().contains(utente) &&
                c.getContenutiCaricati().stream().noneMatch(contenuto -> contenuto.getUtente().equals(utente))).toList();
        List<Contest> contestPubbliciNonPartecipati = comune.getContestAperti().stream().filter(c ->
                !c.getPrivato() &&
                c.getContenutiCaricati().stream().noneMatch(contenuto -> contenuto.getUtente().equals(utente))).toList();

        return Stream.concat(contestPrivatiNonPartecipati.stream(), contestPubbliciNonPartecipati.stream()).toList();
    }

    public List<UtenteAutenticato> getUtentiInvitabili(Contest contest){
        List<UtenteAutenticato> utentiInvitati = contest.getInvitati();
        List<UtenteAutenticato> contributori = utenteController.getTuttiContributori();
        if(!utentiInvitati.isEmpty())
            contributori.removeAll(utentiInvitati);
        return contributori;
    }
}
