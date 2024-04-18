package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.Itinerario;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;

public class ItinerarioCreator extends ItinerarioFactory{
    @Override
    public ItinerarioGenerico creaItinerario() {
        return new Itinerario();
    }
}
