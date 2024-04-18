package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Poi;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public class PoiCreator extends PoiFactoryCreator{
    @Override
    public PoiGenerico createPoi(Coordinate c) {
        return new Poi(c);
    }
}
