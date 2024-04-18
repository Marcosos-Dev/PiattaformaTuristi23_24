package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.TipiPoi;

public abstract class PoiGenerico  {
    private String titolo;
    private String descrizione;

    private Coordinate coord;

    private TipiPoi tipo;

    //Contenuti

    public PoiGenerico(Coordinate c){
        if(c == null) throw new NullPointerException("Coordinate null");
        this.coord = c;
    }

    public void setDescrizione(String descrizione) {
        if(descrizione == null || descrizione.isEmpty())
            throw new NullPointerException("descrizione vuota o nulla");
        this.descrizione = descrizione;
    }

    public void setTitolo(String titolo) {
        if(titolo == null || titolo.isEmpty())
            throw new NullPointerException("titolo vuota o nullo");
        this.titolo = titolo;
    }

    public void setTipo(TipiPoi tipo) {
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

    public TipiPoi getTipo() {
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
