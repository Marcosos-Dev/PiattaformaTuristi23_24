package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class Itinerario extends ItinerarioGenerico{
    public Itinerario(){
        this.setTipo(TipoItinerario.ITINERARIO);
    }
}
