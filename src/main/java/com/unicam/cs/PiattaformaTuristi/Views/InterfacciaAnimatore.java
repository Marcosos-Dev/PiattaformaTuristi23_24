package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

import java.util.List;

public class InterfacciaAnimatore {
    private ContestController contestController;
    private Comune comune;
    private UtenteAutenticato animatore;

    public InterfacciaAnimatore(Comune comune, UtenteAutenticato utente){
        this.comune = comune;
        this.contestController = new ContestController(this.comune);

        this.animatore = utente;
    }

    public void creaContest(Contest contest){
        this.contestController.creaContest(contest, this.animatore);
    }

    public void selezionaVincitoreContest(){

    }

    public void invitaUtente(Contest contest, List<UtenteAutenticato> utentiDaInvitare){
        //Nell'implementazione originale andrebbero usati i metodi
        // -getTuttiContestPartecipabili per ottenere il contest
        // -getUtentiInvitabili per ottenere gli utenti da invitare
        //rimuovendo la necessità dei parametri
        contestController.invitaUtenti(contest, utentiDaInvitare);
    }
}
