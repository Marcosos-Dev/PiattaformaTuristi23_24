package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.GestorePreferiti;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PreferitoController {
    private GestorePreferiti elementi;

    public PreferitoController(GestorePreferiti elementi){
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

    public void rimuoviPreferitiConPoi(PoiGenerico poi){
        this.elementi.eliminaPoiDaiPreferiti(poi);
    }

    public void rimuoviPreferitiConItinerario(ItinerarioGenerico itinerario){
        this.elementi.eliminaItinerarioDaiPreferiti(itinerario);
    }

    public List<PoiPreferito> getPoiPreferiti(int idUtente){ return this.elementi.getPoiPreferiti(idUtente); }

    public List<ItinerarioPreferito> getItinerariPreferiti(int idUtente) { return this.elementi.getItinerariPreferiti(idUtente); }
}
