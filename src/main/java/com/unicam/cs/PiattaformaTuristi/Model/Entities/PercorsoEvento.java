package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

public class PercorsoEvento extends ItinerarioGenerico{
    private Periodo periodo;

    public PercorsoEvento(){
        this.setTipo(TipoItinerario.PERCORSO_EVENTO);
    }


    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
