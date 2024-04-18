package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class Percorso extends ItinerarioGenerico{
    public Percorso(){
        this.setTipo(TipoItinerario.PERCORSO);
    }

}
