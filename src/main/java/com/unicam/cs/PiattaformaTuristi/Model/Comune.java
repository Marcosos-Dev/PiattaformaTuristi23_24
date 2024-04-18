package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

import java.util.List;

public class Comune {
    private String nome;
    List<PoiGenerico> poiValidati;

    List<PoiGenerico> poiDaValidare;

    List<PoiGenerico> itinerariValidati;

    List<PoiGenerico> itinerariDaValidare;

    public void creaPoiDaValidare(){
        //TODO controllare che non sia nullo il titolo
    }

    public void creaPoiValidato(){
        //TODO controllare che non sia nullo il titolo
    }

    public void creaItinerarioDaValidare(){
        //TODO controllare che non sia nullo il titolo
    }

    public void creaItinerarioValidato(){
        //TODO controllare che non sia nullo il titolo
    }

    public List<PoiGenerico> getPoiValidati() {
        return poiValidati;
    }

    public List<PoiGenerico> getItinerariValidati() {
        return itinerariValidati;
    }

    public boolean internoAlComune(Coordinate coord) {

        return true;
    }

    public boolean poiDuplicato(PoiGenerico poi) {
        return true;
    }
}
