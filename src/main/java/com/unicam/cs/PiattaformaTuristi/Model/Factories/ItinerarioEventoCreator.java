package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;

public class ItinerarioEventoCreator extends ItinerarioFactory{
    @Override
    public ItinerarioGenerico creaItinerario() {
        return new ItinerarioEvento();
    }
}
