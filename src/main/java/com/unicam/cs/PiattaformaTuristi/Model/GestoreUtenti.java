package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

import java.util.ArrayList;
import java.util.List;

public class GestoreUtenti {
    private List<UtenteAutenticato> utenti;
    private List<Richiesta> richiesteCambioRuolo;

    public GestoreUtenti(){
        this.utenti = new ArrayList<>();
        this.richiesteCambioRuolo = new ArrayList<>();
    }

    public void aggiungiUtente(UtenteAutenticato utente){ this.utenti.add(utente); }

    public void aggiungiRichiestaRuolo(Richiesta richiesta){ this.richiesteCambioRuolo.add(richiesta); }

    public void rimuoviRichiestaRuolo(Richiesta richiesta) { this.richiesteCambioRuolo.remove(richiesta); }

    public void modificaRuolo(int idUtente, RuoloUtente ruolo){ getUtente(idUtente).setRuolo(ruolo); }

    public List<Richiesta> getRichiesteCambioRuolo() { return richiesteCambioRuolo; }

    public List<UtenteAutenticato> getUtenti() { return utenti; }

    public List<UtenteAutenticato> getTuttiContributori(){
        return this.utenti.stream().filter(u -> u.getRuolo() == RuoloUtente.CONTRIBUTORE || u.getRuolo() == RuoloUtente.CONTRIBUTORE_AUTORIZZATO).toList();
    }

    public UtenteAutenticato getUtente(int idUtente){
        return getUtenti().stream().filter(u -> u.getIdUtente()==idUtente).findFirst().orElse(null);
    }

    public int getUltimoIdRichiesta(){
        return this.getRichiesteCambioRuolo().isEmpty() ?
                1 : this.getRichiesteCambioRuolo().getLast().getIdRichiesta()+1;
    }
}
