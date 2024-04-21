package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class Itinerario extends ItinerarioGenerico{
    public Itinerario(){
        this.setTipo(TipoItinerario.ITINERARIO);
    }

    public String toString(){
        return "Tipo itinerario: " + this.getTipo() +
                "; Nome Itinerario: " + this.getTitolo() +
                "; Descrizione Itinerario: " + this.getDescrizione() +
                "; POI: ";
    }
}
