package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.ContenutoContest;

import java.util.ArrayList;
import java.util.List;

public class Contest {
    private int idContest;
    private String titolo;
    private String descrizione;
    private boolean privato; //True se privato
    private List<UtenteAutenticato> invitati;
    private List<ContenutoContest> contenutiCaricati;
    private ContenutoContest contenutoVincitore;
    private UtenteAutenticato creatoreContest;

    public Contest(){
        this.invitati = new ArrayList<>();
        this.contenutiCaricati = new ArrayList<>();
    }

    public void addTuttiInvitati(List<UtenteAutenticato> invitati) { this.invitati.addAll(invitati); }

    public void setCreatoreContest(UtenteAutenticato creatoreContest) { this.creatoreContest = creatoreContest; }

    public void setIdContest(int idContest) { this.idContest = idContest; }

    public void setTitolo(String titolo) { this.titolo = titolo; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public void setPrivato(boolean privato) { this.privato = privato; }

    public void setContenutoVincitore(ContenutoContest contenutoVincitore) { this.contenutoVincitore = contenutoVincitore; }

    public void addInvitato(UtenteAutenticato utente) { this.invitati.add(utente); }

    public void addContenuto(ContenutoContest contenuto) { this.contenutiCaricati.add(contenuto); }

    public UtenteAutenticato getCreatoreContest() { return creatoreContest; }

    public int getIdContest() { return idContest; }

    public String getTitolo() { return titolo; }

    public String getDescrizione() { return descrizione; }

    public boolean getPrivato() { return privato; }

    public List<UtenteAutenticato> getInvitati() { return invitati; }

    public List<ContenutoContest> getContenutiCaricati() { return contenutiCaricati; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contest)) return false;
        Contest contest = (Contest) o;
        return contest.getIdContest() == this.getIdContest();
    }
}
