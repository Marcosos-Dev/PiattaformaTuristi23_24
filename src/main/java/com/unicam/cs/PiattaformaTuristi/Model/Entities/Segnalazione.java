package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "segnalazione_generator")
    private int idSegnalazione;
    private String descrizione;

    public Segnalazione(String descrizione){
        this.descrizione = descrizione;
    }

    public Segnalazione() {

    }

    public void setIdSegnalazione(int idSegnalazione) { this.idSegnalazione = idSegnalazione; }

    public int getIdSegnalazione() { return idSegnalazione; }

    public String getDescrizione() { return descrizione; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segnalazione segnalazione = (Segnalazione) o;
        return segnalazione.getIdSegnalazione() == this.getIdSegnalazione();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idSegnalazione);
    }
}
