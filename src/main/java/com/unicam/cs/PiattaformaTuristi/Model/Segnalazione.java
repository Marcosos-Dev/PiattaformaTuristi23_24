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
