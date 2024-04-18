package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class ItinerarioEvento extends ItinerarioGenerico{
    private Periodo periodo;

    public ItinerarioEvento(){
        this.setTipo(TipoItinerario.ITINERARIO_EVENTO);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
