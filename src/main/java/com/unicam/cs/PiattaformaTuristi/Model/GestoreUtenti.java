package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

import java.util.ArrayList;
import java.util.List;

public class GestoreUtenti {
    private List<UtenteAutenticato> utenti;

    public GestoreUtenti(){
        this.utenti = new ArrayList<>();
    }

    public void addUtente(UtenteAutenticato utente){ this.utenti.add(utente); }

    public List<UtenteAutenticato> getUtenti() { return utenti; }

    public List<UtenteAutenticato> getTuttiContributori(){
        return this.utenti.stream().filter(u -> u.getRuolo() == RuoloUtente.CONTRIBUTORE || u.getRuolo() == RuoloUtente.CONTRIBUTORE_AUTORIZZATO).toList();
    }
}
