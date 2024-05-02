package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.RuoliUtenti;

public class UtenteAutenticato implements Utente{
    private int idUtente;
    private RuoliUtenti ruolo;

    public UtenteAutenticato(RuoliUtenti ruolo){
        this.ruolo = ruolo;
    }

    public int getIdUtente() { return idUtente; }

    @Override
    public RuoliUtenti getRuolo() { return ruolo; }

    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtenteAutenticato)) return false;
        UtenteAutenticato utente = (UtenteAutenticato) o;
        return utente.getIdUtente() == this.getIdUtente();
    }
}
