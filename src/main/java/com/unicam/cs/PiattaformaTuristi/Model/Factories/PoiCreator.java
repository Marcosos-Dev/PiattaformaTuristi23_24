package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Poi;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public class PoiCreator extends PoiFactory {
    @Override
    public PoiGenerico creaPoi(Coordinate c) {
        return new Poi(c);
    }
}
