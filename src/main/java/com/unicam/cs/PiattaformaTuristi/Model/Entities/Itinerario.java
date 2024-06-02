package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;
import jakarta.persistence.Entity;

@Entity
public class Itinerario extends ItinerarioGenerico{
    public Itinerario(){
        this.setTipo(TipoItinerario.ITINERARIO);
    }

    public Itinerario(String titolo, String descrizione) {
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setTipo(TipoItinerario.ITINERARIO);
    }

    public String toString(){
        return "ID Itinerario: " + this.getIdItinerario() +
                " Tipo itinerario: " + this.getTipo() +
                "; Nome Itinerario: " + this.getTitolo() +
                "; Descrizione Itinerario: " + this.getDescrizione() +
                "; POI: "+this.getPoi();
    }
}
