package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

public class ContenutoContest {
    private UtenteAutenticato utente;
    private Contenuto contenuto;

    public ContenutoContest(){

    }

    public ContenutoContest(Contenuto contenuto, UtenteAutenticato utente){
        this.contenuto = contenuto;
        this.utente = utente;
    }

    public void setUtente(UtenteAutenticato utente) {
        this.utente = utente;
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    public Contenuto getContenuto() {
        return contenuto;
    }

    public UtenteAutenticato getUtente() {
        return utente;
    }
}
