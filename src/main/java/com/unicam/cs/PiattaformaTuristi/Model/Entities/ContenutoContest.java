package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import jakarta.persistence.*;

@Entity
public class ContenutoContest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contenutoContest_generator")
    private int idContenutoContest;
    @OneToOne
    private UtenteAutenticato utente;
    @OneToOne
    private Contenuto contenuto;

    public ContenutoContest(){

    }

    public ContenutoContest(int idContenutoContest,Contenuto contenuto, UtenteAutenticato utente){
        this.idContenutoContest = idContenutoContest;
        this.contenuto = contenuto;
        this.utente = utente;
    }

    public void setUtente(UtenteAutenticato utente) {
        this.utente = utente;
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    public int getIdContenutoContest() { return idContenutoContest; }


    public Contenuto getContenuto() {
        return contenuto;
    }

    public UtenteAutenticato getUtente() {
        return utente;
    }

    public String toString(){
        return "ID Contenuto Contest: " + this.getIdContenutoContest() +
                "; Ruolo utente: " + this.utente.getRuolo() +
                "; Contenuto: " + this.contenuto;
    }
}
