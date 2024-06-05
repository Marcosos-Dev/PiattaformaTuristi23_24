package com.unicam.cs.PiattaformaTuristi.Model.DTO;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

import java.util.List;

public class ItinerarioDTO {

    private String titolo;
    private String descrizione;
    private Periodo periodo;
    private List<Integer> poi;
    private TipoItinerario tipo;

    public ItinerarioDTO(String titolo, String descrizione, Periodo periodo, List<Integer> poi, TipoItinerario tipo){
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.periodo = periodo;
        this.poi = poi;
        this.tipo = tipo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public List<Integer> getPoi() {
        return poi;
    }

    public TipoItinerario getTipo() {
        return tipo;
    }
}
