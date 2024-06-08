package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioPreferito;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiPreferito;
import com.unicam.cs.PiattaformaTuristi.Repositories.ItinerarioPreferitoRepository;
import com.unicam.cs.PiattaformaTuristi.Repositories.PoiPreferitoRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Component
public class GestoreElementiSalvati {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PoiPreferito> poiPreferiti;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItinerarioPreferito> itinerariPreferiti;
    @Autowired
    private PoiPreferitoRepository poiPreferitoRepository;
    @Autowired
    private ItinerarioPreferitoRepository itinerarioPreferitoRepository;

    public GestoreElementiSalvati(){
        this.poiPreferiti = new ArrayList<>();
        this.itinerariPreferiti = new ArrayList<>();
    }

    public List<PoiPreferito> getPoiPreferiti(int idUtente) {
        return this.poiPreferitoRepository.GetPreferitiUtente(idUtente);
    }

    public List<ItinerarioPreferito> getItinerariPreferiti(int idUtente) { return this.itinerarioPreferitoRepository.GetPreferitiUtente(idUtente); }

    public List<PoiGenerico> getPoiPreferitiUtente(int idUtente) { return poiPreferiti.stream().filter(p -> p.getUtente().getIdUtente()==idUtente).map(p -> p.getPoi()).toList(); }

    public List<ItinerarioGenerico> getItinerariPreferitiUtente(int idUtente) { return itinerariPreferiti.stream().filter(i -> i.getUtente().getIdUtente()==idUtente).map(i -> i.getItinerario()).toList(); }

    public void aggiungiPoiPreferito(PoiPreferito poi){
        this.poiPreferiti.add(poi);
        this.poiPreferitoRepository.save(poi);
    }

    public void aggiungiItinerarioPreferito(ItinerarioPreferito itinerario){
        this.itinerariPreferiti.add(itinerario);
        this.itinerarioPreferitoRepository.save(itinerario);
    }

    public void eliminaPoiPreferito(PoiPreferito poi){
        this.poiPreferiti.remove(poi);
        this.poiPreferitoRepository.delete(poi);
    }

    public void eliminaItinerarioPreferito(ItinerarioPreferito itinerario){
        this.itinerariPreferiti.remove(itinerario);
        this.itinerarioPreferitoRepository.delete(itinerario);
    }
}
