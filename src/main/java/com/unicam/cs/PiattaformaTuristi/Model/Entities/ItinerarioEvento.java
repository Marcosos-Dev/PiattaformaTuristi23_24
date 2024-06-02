package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class ItinerarioEvento extends ItinerarioGenerico{
    @Embedded
    private Periodo periodo;

    public ItinerarioEvento(){
        this.setTipo(TipoItinerario.ITINERARIO_EVENTO);
    }

    public ItinerarioEvento(String titolo, String descrizione) {
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setTipo(TipoItinerario.ITINERARIO_EVENTO);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String toString(){
        return "ID Itinerario: " + this.getIdItinerario() +
                " Tipo itinerario: " + this.getTipo() +
                "; Nome Itinerario: " + this.getTitolo() +
                "; Descrizione Itinerario: " + this.getDescrizione() +
                "; Data Inizio Itinerario: " + this.getPeriodo().getDataInizio() +
                "; Data Fine Itinerario: " + this.getPeriodo().getDataFine() +
                "; POI: "+this.getPoi();
    }
}
