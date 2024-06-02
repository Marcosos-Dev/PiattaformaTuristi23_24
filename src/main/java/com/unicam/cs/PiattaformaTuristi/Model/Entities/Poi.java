package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;
import jakarta.persistence.Entity;

@Entity
public class Poi extends PoiGenerico{

    public Poi(Coordinate c) {
        super(c);
        this.setTipo(TipoPoi.POI);
    }

    public Poi(String titolo, String descrizione, Coordinate c) {
        super(c);
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setTipo(TipoPoi.POI);
    }

    public Poi() {

    }

    public String toString(){
        return "ID POI: " + this.getIdPoi() +
                "; Tipo POI: " + this.getTipo() +
                "; Nome POI: " + this.getTitolo() +
                "; Descrizione POI: " + this.getDescrizione()+
                "; Contenuti POI: " + this.getContenutiValidati();
    }


}
