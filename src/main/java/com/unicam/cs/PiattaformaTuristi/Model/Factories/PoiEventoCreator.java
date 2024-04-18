package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public class PoiEventoCreator extends PoiFactory {
    @Override
    public PoiGenerico creaPoi(Coordinate c) {
        return new PoiEvento(c);
    }
}
