package com.unicam.cs.PiattaformaTuristi.Model;

public class Segnalazione {
    private int idSegnalazione;
    private String descrizione;

    public Segnalazione(String descrizione){
        this.descrizione = descrizione;
    }

    public void setIdSegnalazione(int idSegnalazione) { this.idSegnalazione = idSegnalazione; }

    public int getIdSegnalazione() { return idSegnalazione; }

    public String getDescrizione() { return descrizione; }
}
