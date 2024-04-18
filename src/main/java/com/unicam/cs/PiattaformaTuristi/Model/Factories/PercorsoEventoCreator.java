package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PercorsoEvento;

public class PercorsoEventoCreator extends ItinerarioFactory{
    @Override
    public ItinerarioGenerico creaItinerario() {
        return new PercorsoEvento();
    }
}
