package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public abstract class PoiFactoryCreator {
    public abstract PoiGenerico createPoi(Coordinate c);
}
