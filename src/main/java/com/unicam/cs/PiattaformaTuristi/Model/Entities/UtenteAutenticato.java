package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UtenteAutenticato implements Utente{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_generator")
    private int idUtente;
    private String username;
    private String password;
    private RuoloUtente ruolo;

    public UtenteAutenticato(){
        this.ruolo = RuoloUtente.TURISTA_AUTENTICATO;
    }

    public UtenteAutenticato(RuoloUtente ruolo){
        this.ruolo = ruolo;
    }

    public UtenteAutenticato(String username, String password){
        this.username = username;
        this.password = password;
        this.ruolo = RuoloUtente.TURISTA_AUTENTICATO;
    }

    public int getIdUtente() { return idUtente; }

    public RuoloUtente getRuolo() { return ruolo; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public void setRuolo(RuoloUtente ruolo) { this.ruolo=ruolo; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtenteAutenticato)) return false;
        UtenteAutenticato utente = (UtenteAutenticato) o;
        return utente.getIdUtente() == this.getIdUtente();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idUtente;
        return result;
    }
}
