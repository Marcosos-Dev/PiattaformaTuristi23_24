package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;

public class PoiEvento extends PoiGenerico{
    Periodo periodo;
    public PoiEvento(Coordinate c) {
        super(c);
        this.setTipo(TipoPoi.EVENTO);
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
