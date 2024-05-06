package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public void chiudiContest(Contest c){
        this.contestChiusi.add(c);
        this.contestAperti.remove(c);
    }

    public void inserisciContestAperto(Contest contest){ this.contestAperti.add(contest); }

    public void inserisciPoiDaValidare(PoiGenerico poi){ this.poiDaValidare.add(poi); }

    public void removePoiDaValidare(PoiGenerico poi){ this.poiDaValidare.remove(poi); }

    public void inserisciPoiValidato(PoiGenerico poi){
        this.poiValidati.add(poi);
    }

    public void inserisciItinerarioDaValidare(ItinerarioGenerico itinerario){ this.itinerariDaValidare.add(itinerario); }

    public void removeItinerarioDaValidare(ItinerarioGenerico itinerario){ this.itinerariDaValidare.remove(itinerario); }

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

    public List<Contenuto> getContenutiValidati() { return this.getPoiValidati().stream().filter(c -> !c.getContenutiValidati().isEmpty()).flatMap(p -> p.getContenutiValidati().stream()).toList(); }

    public List<Contenuto> getContenutiDaValidare() { return this.getPoiValidati().stream().filter(c -> !c.getContenutiDaValidare().isEmpty()).flatMap(p -> p.getContenutiDaValidare().stream()).toList(); }

    public int getLastIdItinerario(){
        return Math.max(
                this.getItinerariDaValidare().isEmpty() ?
                        1 : this.getItinerariDaValidare().getLast().getIdItinerario()+1,
                this.getItinerariValidati().isEmpty() ?
                        1 : this.getItinerariValidati().getLast().getIdItinerario()+1
        );
    }

    public int getUltimoIdPoi(){
        return Math.max(
                this.getPoiValidati().isEmpty() ?
                    1 : this.getPoiValidati().getLast().getIdPoi()+1,
                this.getPoiDaValidare().isEmpty() ?
                        1 : this.getPoiDaValidare().getLast().getIdPoi()+1
        );
    }

    public int getUltimoIdContest(){
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

    public void stampaPOIValidati(){
        for(PoiGenerico p : getPoiValidati()){
            System.out.println(p);
        }
    }

    public void stampaPOIDaValidare(){
        for(PoiGenerico p : getPoiDaValidare()){
            System.out.println(p);
        }
    }

    public void stampaItinerariValidati(){
        for(ItinerarioGenerico i : getItinerariValidati()){
            System.out.println(i);
            for(PoiGenerico p : i.getPoi())
                System.out.println(p);
        }
    }

    public void stampaItinerariDaValidare(){
        for(ItinerarioGenerico i : getItinerariDaValidare()){
            System.out.println(i);
            for(PoiGenerico p : i.getPoi())
                System.out.println(p);
        }
    }
}
