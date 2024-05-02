package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

import java.util.ArrayList;
import java.util.List;

public class GestoreUtenti {
    private List<UtenteAutenticato> utenti;

    public GestoreUtenti(){
        this.utenti = new ArrayList<>();
    }

    public void aggiungiUtente(UtenteAutenticato utente){ this.utenti.add(utente); }

    public List<UtenteAutenticato> getUtenti() { return utenti; }

    public List<UtenteAutenticato> getTuttiContributori(){
        return this.utenti.stream().filter(u -> u.getRuolo() == RuoliUtenti.CONTRIBUTORE || u.getRuolo() == RuoliUtenti.CONTRIBUTORE_AUTORIZZATO).toList();
    }
}
