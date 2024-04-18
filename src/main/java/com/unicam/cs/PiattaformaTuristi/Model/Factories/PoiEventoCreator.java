package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public class PoiEventoCreator extends PoiFactoryCreator {
    @Override
    public PoiGenerico createPoi(Coordinate c) {
        return new PoiEvento(c);
    }
}
