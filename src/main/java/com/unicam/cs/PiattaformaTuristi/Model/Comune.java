package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

import java.util.ArrayList;
import java.util.List;

public class Comune {
    private String nome;
    List<PoiGenerico> poiValidati;

    List<PoiGenerico> poiDaValidare;

    List<ItinerarioGenerico> itinerariValidati;

    List<ItinerarioGenerico> itinerariDaValidare;

    public Comune(){
        this.poiValidati = new ArrayList<>();
        this.poiDaValidare = new ArrayList<>();
        this.itinerariValidati = new ArrayList<>();
        this.itinerariDaValidare = new ArrayList<>();
    }
    public void inserisciPoiDaValidare(PoiGenerico poi){
        this.poiDaValidare.add(poi);
    }

    public void inserisciPoiValidato(PoiGenerico poi){
        this.poiValidati.add(poi);
    }

    public void inserisciItinerarioDaValidare(ItinerarioGenerico itinerario){
        this.itinerariDaValidare.add(itinerario);
    }

    public void inserisciItinerarioValidato(ItinerarioGenerico itinerario){
        this.itinerariValidati.add(itinerario);
    }

    public List<PoiGenerico> getPoiValidati() {
        return poiValidati;
    }

    public List<PoiGenerico> getPoiDaValidare() {
        return poiDaValidare;
    }

    public List<ItinerarioGenerico> getItinerariValidati() {
        return itinerariValidati;
    }

    public List<ItinerarioGenerico> getItinerariDaValidare(){
        return itinerariDaValidare;
    }

    public PoiGenerico ottieniPoi(PoiGenerico poi){
        return this.poiValidati.stream().filter(p -> p.equals(poi)).findFirst().orElse(null);
    }

    public boolean internoAlComune(Coordinate coord) {

        return true;
    }

    public boolean poiDuplicato(PoiGenerico poi) {
        for(PoiGenerico p : poiValidati){
            if(poi.equals(p))
                return true;
        }
        return false;
    }
}
