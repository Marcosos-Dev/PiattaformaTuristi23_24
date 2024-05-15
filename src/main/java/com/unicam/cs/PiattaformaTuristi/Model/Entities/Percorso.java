package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class Percorso extends ItinerarioGenerico{
    public Percorso(){ this.setTipo(TipoItinerario.PERCORSO); }

    public Percorso(String titolo, String descrizione) {
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setTipo(TipoItinerario.PERCORSO);
    }

    public String toString(){
        return "ID Percorso: " + this.getIdItinerario() +
                " Tipo itinerario: " + this.getTipo() +
                "; Nome Itinerario: " + this.getTitolo() +
                "; Descrizione Itinerario: " + this.getDescrizione() +
                "; POI: "+this.getPoi();
    }
}
