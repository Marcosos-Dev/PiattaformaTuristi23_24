package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreElementiSalvati;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ElementiSalvatiController {
    private GestoreElementiSalvati elementi;

    public ElementiSalvatiController(GestoreElementiSalvati elementi){
        this.elementi = elementi;
    }

    public void salvaPoi(UtenteAutenticato utente, PoiGenerico poi){
        PoiPreferito preferito = new PoiPreferito(poi, utente);
        this.elementi.aggiungiPoiPreferito(preferito);
    }

    public void salvaItinerario(UtenteAutenticato utente, ItinerarioGenerico itinerario){
        ItinerarioPreferito preferito = new ItinerarioPreferito(itinerario, utente);
        this.elementi.aggiungiItinerarioPreferito(preferito);
    }

    public void rimuoviPoiPreferito(UtenteAutenticato utente, PoiPreferito poi) { this.elementi.eliminaPoiPreferito(poi); }

    public void rimuoviItinerarioPreferito(UtenteAutenticato utente, ItinerarioPreferito itinerario) { this.elementi.eliminaItinerarioPreferito(itinerario); }

    public void gestisciSegnalazionePoiPreferiti(PoiGenerico poi, boolean esito){
        if(esito) { this.elementi.eliminaPoiDaiPreferiti(poi); }
    }

    public void gestisciSegnalazioneItinerariPreferiti(ItinerarioGenerico itinerario, boolean esito){
        if(esito) { this.elementi.eliminaItinerarioDaiPreferiti(itinerario); }
    }

    public List<PoiPreferito> getPoiPreferiti(int idUtente){ return this.elementi.getPoiPreferiti(idUtente); }

    public List<ItinerarioPreferito> getItinerariPreferiti(int idUtente) { return this.elementi.getItinerariPreferiti(idUtente); }
}
