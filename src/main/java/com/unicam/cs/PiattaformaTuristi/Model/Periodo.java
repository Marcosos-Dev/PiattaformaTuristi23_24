package com.unicam.cs.PiattaformaTuristi.Model;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
@Embeddable
public class Periodo {
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    public Periodo(LocalDateTime dataInizio,LocalDateTime dataFine){
        if(dataValida(dataInizio,dataFine)){
            this.dataInizio = dataInizio;
            this.dataFine = dataFine;
        }
    }

    public Periodo() {

    }


    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    private boolean dataValida(LocalDateTime dataInizio,LocalDateTime dataFine){
        if(dataInizio == null || dataFine == null)
            throw new NullPointerException("La data di inizio e di fine non possono essere nulle");
        if(dataInizio.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("La data di inizio non può essere precedente alla data attuale");
        if(dataInizio.isAfter(dataFine))
            throw new IllegalArgumentException("La data di inizio non può essere dopo la data di fine o viceversa");
        return true;
    }
}
