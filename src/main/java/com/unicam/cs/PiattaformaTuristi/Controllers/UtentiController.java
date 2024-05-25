package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.Richiesta;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

import java.util.List;
import java.util.regex.Pattern;

public class UtentiController {
    private GestoreUtenti gestoreUtenti;

    public UtentiController(){
        this.gestoreUtenti = new GestoreUtenti();
    }

    public UtentiController(GestoreUtenti gestoreUtenti){
        this.gestoreUtenti = gestoreUtenti;
    }

    public void registraUtente(String username, String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()_-])(?=\\S+$).{8,}$");
        if(username == null || username.isEmpty())
            throw new IllegalArgumentException("Username non valido (vuoto)");
        if(this.gestoreUtenti.getUtenteTramiteUsername(username)!=null)
            throw new IllegalArgumentException("Username non valido, esiste già un utente con lo stesso username");
        if(password == null || password.isEmpty() || !pattern.matcher(password).matches())
            throw new IllegalArgumentException("Password non valida");
        UtenteAutenticato utente = new UtenteAutenticato(username,password);
        utente.setIdUtente(this.gestoreUtenti.getUltimoIdUtente());
        this.gestoreUtenti.aggiungiUtente(utente);
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

    public void richiediRuolo(int idUtente, RuoloUtente ruolo){
        Richiesta richiesta = new Richiesta(idUtente, ruolo);
        richiesta.setIdRichiesta(gestoreUtenti.getUltimoIdRichiesta());
        this.gestoreUtenti.aggiungiRichiestaRuolo(richiesta);
    }


    public List<UtenteAutenticato> getTuttiContributori(){ return this.gestoreUtenti.getTuttiContributori(); }


}
