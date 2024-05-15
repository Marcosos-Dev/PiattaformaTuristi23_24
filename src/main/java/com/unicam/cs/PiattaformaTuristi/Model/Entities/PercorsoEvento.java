package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class PercorsoEvento extends ItinerarioGenerico{
    private Periodo periodo;

    public PercorsoEvento(){
        this.setTipo(TipoItinerario.PERCORSO_EVENTO);
    }

    public PercorsoEvento(String titolo, String descrizione) {
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setTipo(TipoItinerario.PERCORSO_EVENTO);
    }


    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String toString(){
        return "ID Percorso: " + this.getIdItinerario() +
                " Tipo itinerario: " + this.getTipo() +
                "; Nome Itinerario: " + this.getTitolo() +
                "; Descrizione Itinerario: " + this.getDescrizione() +
                "; Data Inizio Itinerario: " + this.getPeriodo().getDataInizio() +
                "; Data Fine Itinerario: " + this.getPeriodo().getDataFine() +
                "; POI: "+this.getPoi();
    }
}
