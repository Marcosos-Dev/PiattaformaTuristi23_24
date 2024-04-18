package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;

public abstract class PoiGenerico  {
    private String titolo;
    private String descrizione;

    private Coordinate coord;

    private TipoPoi tipo;

    //Contenuti

    public PoiGenerico(Coordinate c){
        if(c == null) throw new NullPointerException("Coordinate null");
        this.coord = c;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setTipo(TipoPoi tipo) {
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

    public TipoPoi getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoiGenerico)) return false;
        PoiGenerico poi = (PoiGenerico) o;
        return poi.getCoord().equals(this.getCoord());
    }

}
