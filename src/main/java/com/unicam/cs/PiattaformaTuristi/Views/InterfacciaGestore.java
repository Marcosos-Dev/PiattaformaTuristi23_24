package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Richiesta;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

public class InterfacciaGestore {
    private UtentiController utentiController;

    public InterfacciaGestore(UtenteAutenticato utente, GestoreUtenti gestore){
        if(!utente.getRuolo().equals(RuoloUtente.GESTORE_PIATTAFORMA))
            throw new IllegalArgumentException("Accesso non consentito");
        this.utentiController = new UtentiController(gestore);
    }

    public void autenticazione(String username, String password){
        //if(!this.utentiController.autenticaUtente(username,password))
        //    throw new IllegalArgumentException("Credenziali errate");
    }

    //Modifica il ruolo a piacimento
    public void gestisciRuolo(int idUtente, RuoloUtente nuovoRuolo){
        //this.utentiController.gestisciRuolo(idUtente,nuovoRuolo);
    }

    //accetta o rifiuta i cambi di ruolo
    public void gestisciRichiestaRuolo(Richiesta richiesta, boolean esito){
        //this.utentiController.gestisciRichiestaRuolo(richiesta,esito);
    }
}
