package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.RuoliUtenti;

public class UtenteAutenticato implements Utente{
    private RuoliUtenti ruolo;

    public UtenteAutenticato(RuoliUtenti ruolo){
        this.ruolo = ruolo;
    }

    @Override
    public RuoliUtenti getRuolo() {
        return ruolo;
    }
}
