package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

import java.util.ArrayList;
import java.util.List;

public class Comune {
    private String nome;

    private List<PoiGenerico> poiValidati;

    private List<PoiGenerico> poiDaValidare;

    private List<ItinerarioGenerico> itinerariValidati;

    private List<ItinerarioGenerico> itinerariDaValidare;

    private List<Contest> contestAperti;

    private List<Contest> contestChiusi;

    public Comune(){
        this.poiValidati = new ArrayList<>();
        this.poiDaValidare = new ArrayList<>();
        this.itinerariValidati = new ArrayList<>();
        this.itinerariDaValidare = new ArrayList<>();
        this.contestAperti = new ArrayList<>();
        this.contestChiusi = new ArrayList<>();
    }

    public void inserisciContestAperto(Contest contest){ this.contestAperti.add(contest); }

    public void inserisciContestChiuso(Contest contest){ this.contestAperti.add(contest); }

    public void inserisciPoiDaValidare(PoiGenerico poi){ this.poiDaValidare.add(poi); }

    public void inserisciPoiValidato(PoiGenerico poi){
        this.poiValidati.add(poi);
    }

    public void inserisciItinerarioDaValidare(ItinerarioGenerico itinerario){ this.itinerariDaValidare.add(itinerario); }

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

    public List<Contest> getContestAperti() { return contestAperti; }

    public List<Contest> getContestChiusi() { return contestChiusi; }

    //Metodo di utilità per ottenere l'ultimo id, l'id verrà assegnato al poi solo se validato
    public int getLastPoiId(){
        return this.getPoiValidati().isEmpty() ?
                1 : this.getPoiValidati().getLast().getIdPoi()+1;
    }

    public int getLastContestId(){
        return this.getContestAperti().isEmpty() ?
                1 : this.getContestAperti().getLast().getIdContest()+1;
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
