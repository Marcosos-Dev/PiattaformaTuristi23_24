package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

public class UtenteAutenticato implements Utente{
    private int idUtente;
    private RuoloUtente ruolo;

    public UtenteAutenticato(RuoloUtente ruolo){
        this.ruolo = ruolo;
    }

    public int getIdUtente() { return idUtente; }

    @Override
    public RuoloUtente getRuolo() { return ruolo; }

    @Override
    public void setRuolo(RuoloUtente ruolo) {this.ruolo=ruolo;}

    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtenteAutenticato)) return false;
        UtenteAutenticato utente = (UtenteAutenticato) o;
        return utente.getIdUtente() == this.getIdUtente();
    }
}
