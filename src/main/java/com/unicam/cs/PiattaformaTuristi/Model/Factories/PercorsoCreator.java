package com.unicam.cs.PiattaformaTuristi.Model.Factories;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Percorso;

public class PercorsoCreator extends ItinerarioFactory{
    @Override
    public ItinerarioGenerico creaItinerario() {
        return new Percorso();
    }
}
