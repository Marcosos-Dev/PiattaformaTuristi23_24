package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;

public class Poi extends PoiGenerico{

    public Poi(Coordinate c) {
        super(c);
        this.setTipo(TipoPoi.POI);
    }

    public String toString(){
        return "Tipo POI: " + this.getTipo() +
                "; Nome POI: " + this.getTitolo() +
                "; Descrizione POI: " + this.getDescrizione();
    }


}
