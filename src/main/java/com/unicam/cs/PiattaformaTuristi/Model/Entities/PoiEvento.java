package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class PoiEvento extends PoiGenerico{
    @Embedded
    private Periodo periodo;

    public PoiEvento(Coordinate c) {
        super(c);
        this.setTipo(TipoPoi.EVENTO);
    }

    public PoiEvento(String titolo, String descrizione, Coordinate c) {
        super(c);
        this.setTitolo(titolo);
        this.setDescrizione(descrizione);
        this.setTipo(TipoPoi.EVENTO);
    }

    public PoiEvento() {

    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String toString(){
        return "ID POI: " + this.getIdPoi() +
                "; Tipo POI: " + this.getTipo() +
                "; Nome POI: " + this.getTitolo() +
                "; Descrizione POI: " + this.getDescrizione() +
                "; Data Inizio POI: " + this.getPeriodo().getDataInizio() +
                "; Data Fine POI: " + this.getPeriodo().getDataFine()+
                "; Contenuti POI: " + this.getContenutiValidati();
    }
}
