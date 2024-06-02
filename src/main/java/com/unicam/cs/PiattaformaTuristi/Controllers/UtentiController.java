package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Richiesta;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtentiController {
    @Autowired
    private GestoreUtenti gestoreUtenti;

    public UtentiController(){

    }

    public UtentiController(GestoreUtenti gestoreUtenti){
        this.gestoreUtenti = gestoreUtenti;
    }

    public void registraUtente(String username, String password){
        this.gestoreUtenti.aggiungiUtente(new UtenteAutenticato(username,password));
    }

    public boolean autenticaUtente(String username, String password){
        return this.gestoreUtenti.autenticaUtente(username,password);
    }

    public List<RuoloUtente> getPossibiliRuoli(RuoloUtente ruolo){
        return RuoloUtente.getPossibiliRuoliDefault().stream().filter(x -> !x.equals(ruolo)).toList();
    }

    public void gestisciRichiestaRuolo(Richiesta richiesta, boolean esito){
        if(esito) this.gestoreUtenti.modificaRuolo(richiesta.getIdUtente(), richiesta.getRuoloRichiesto());
        this.gestoreUtenti.rimuoviRichiestaRuolo(richiesta);
    }

    public void gestisciRuolo(int idUtente, RuoloUtente nuovoRuolo){
        this.gestoreUtenti.modificaRuolo(idUtente, nuovoRuolo);
    }

    public void aggiungiRichiestaRuolo(Richiesta richiesta){
        this.gestoreUtenti.aggiungiRichiestaRuolo(richiesta);
    }

    //TODO Rimuovere
    public void richiediRuolo(int idUtente, RuoloUtente nuovoRuolo){

    }

    public UtenteAutenticato getUtenteTramiteUsername(String username){
        return this.gestoreUtenti.getUtenteTramiteUsername(username);
    }

    public List<UtenteAutenticato> getTuttiContributori(){ return this.gestoreUtenti.getTuttiContributori(); }

    public List<UtenteAutenticato> getUtenti(){ return this.gestoreUtenti.getUtenti(); }


}
