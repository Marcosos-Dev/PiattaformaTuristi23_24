package com.unicam.cs.PiattaformaTuristi.Model.DTO;

public class ContestDTO {
    private String titolo;
    private String descrizione;
    private boolean privato;

    public ContestDTO(){

    }

    public ContestDTO(String titolo, String descrizione, boolean privato) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.privato = privato;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean getPrivato(){
        return privato;
    }
}
