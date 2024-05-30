package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioPreferito;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiPreferito;

import java.util.ArrayList;
import java.util.List;

public class GestoreElementiSalvati {
    private List<PoiPreferito> poiPreferiti;
    private List<ItinerarioPreferito> itinerariPreferiti;

    public GestoreElementiSalvati(){
        this.poiPreferiti = new ArrayList<>();
        this.itinerariPreferiti = new ArrayList<>();
    }

    public List<PoiPreferito> getPoiPreferiti() { return poiPreferiti; }

    public List<ItinerarioPreferito> getItinerariPreferiti() { return itinerariPreferiti; }

    public List<PoiGenerico> getPoiPreferitiUtente(int idUtente) { return poiPreferiti.stream().filter(p -> p.getUtente().getIdUtente()==idUtente).map(p -> p.getPoi()).toList(); }

    public List<ItinerarioGenerico> getItinerariPreferitiUtente(int idUtente) { return itinerariPreferiti.stream().filter(i -> i.getUtente().getIdUtente()==idUtente).map(i -> i.getItinerario()).toList(); }

    public void aggiungiPoiPreferito(PoiPreferito poi){ this.poiPreferiti.add(poi); }

    public void aggiungiItinerarioPreferito(ItinerarioPreferito itinerario){ this.itinerariPreferiti.add(itinerario); }

    public void eliminaPoiPreferito(PoiPreferito poi){ this.poiPreferiti.remove(poi); }

    public void eliminaItinerarioPreferito(ItinerarioPreferito itinerario){ this.itinerariPreferiti.remove(itinerario); }

    public int getUltimoIdPreferito(){
        return Math.max(
                this.poiPreferiti.isEmpty() ?
                        1 : this.poiPreferiti.getLast().getIdElemento()+1,
                this.itinerariPreferiti.isEmpty() ?
                        1 : this.itinerariPreferiti.getLast().getIdElemento()+1
        );
    }
}
