package com.unicam.cs.PiattaformaTuristi.Model.DTO;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;


public class PoiDTO {
    private String titolo;
    private String descrizione;
    private Coordinate coord;
    private Periodo periodo;
    private TipoPoi tipo;
    private Contenuto contenuto;

    public PoiDTO() {}

    public PoiDTO(String titolo,String descrizione,Coordinate coord,Periodo periodo,TipoPoi tipo) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.coord = coord;
        this.periodo = periodo;
        this.tipo = tipo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public TipoPoi getTipo() {
        return tipo;
    }

    public Contenuto getContenuto() {
        return contenuto;
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }
}