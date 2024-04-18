package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.TipiPoi;

public class Poi extends PoiGenerico{

    public Poi(Coordinate c) {
        super(c);
        this.setTipo(TipiPoi.POI);
    }
}
