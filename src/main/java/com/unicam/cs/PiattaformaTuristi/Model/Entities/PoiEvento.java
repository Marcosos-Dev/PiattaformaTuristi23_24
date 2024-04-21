package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;

public class PoiEvento extends PoiGenerico{
    private Periodo periodo;
    public PoiEvento(Coordinate c) {
        super(c);
        this.setTipo(TipoPoi.EVENTO);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String toString(){
        return "Tipo POI: " + this.getTipo() +
                "; Nome POI: " + this.getTitolo() +
                "; Descrizione POI: " + this.getDescrizione() +
                "; Data Inizio POI: " + this.getPeriodo().getDataInizio() +
                "; Data Fine POI: " + this.getPeriodo().getDataFine();
    }
}
