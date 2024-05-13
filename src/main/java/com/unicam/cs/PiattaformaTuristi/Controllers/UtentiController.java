package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.Richiesta;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

import java.util.List;

public class UtentiController {
    private GestoreUtenti gestoreUtenti;

    public UtentiController(){
        this.gestoreUtenti = new GestoreUtenti();
    }

    public UtentiController(GestoreUtenti gestoreUtenti){
        this.gestoreUtenti = gestoreUtenti;
    }

    public List<RuoloUtente> getPossibiliRuoli(RuoloUtente ruolo){
        return RuoloUtente.getPossibiliRuoliDefault().stream().filter(x -> !x.equals(ruolo)).toList();
    }

    public void gestisciRichiestaRuolo(Richiesta richiesta, boolean esito){
        if(esito) modificaRuolo(
                getUtente(richiesta.getIdUtente()),
                richiesta.getRuoloRichiesto());
        this.gestoreUtenti.rimuoviRichiestaRuolo(richiesta);
    }

    public void richiediRuolo(int idUtente, RuoloUtente ruolo){
        Richiesta richiesta = new Richiesta(idUtente, ruolo);
        richiesta.setIdRichiesta(gestoreUtenti.getUltimoIdRichiesta());
        this.gestoreUtenti.aggiungiRichiestaRuolo(richiesta);
    }

    public void modificaRuolo(UtenteAutenticato utente, RuoloUtente ruolo){ utente.setRuolo(ruolo); }

    public List<UtenteAutenticato> getTuttiContributori(){ return this.gestoreUtenti.getTuttiContributori(); }

    public UtenteAutenticato getUtente(int idUtente){
        return this.gestoreUtenti.getUtenti().stream().filter(u -> u.getIdUtente()==idUtente).findFirst().orElse(null);
    }
}
