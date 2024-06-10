package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
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

    public void rimuoviPoiSalvato(UtenteAutenticato utente, PoiPreferito poi) {
        this.elementi.eliminaPoiPreferito(poi);
    }

    public void rimuoviItinerarioSalvato(UtenteAutenticato utente, ItinerarioPreferito itinerario) {
        this.elementi.eliminaItinerarioPreferito(itinerario);
    }

    public void gestisciSegnalazionePoi(PoiGenerico poi, boolean esito){
        if(esito) { this.elementi.eliminaPoiDaiPreferiti(poi); }
    }

    public void gestisciSegnalazioneItinerario(ItinerarioGenerico itinerario, boolean esito){
        if(esito) { this.elementi.eliminaItinerarioDaiPreferiti(itinerario); }
    }

    public List<PoiPreferito> getPoiPreferiti(int idUtente){ return this.elementi.getPoiPreferiti(idUtente); }

    public List<ItinerarioPreferito> getItinerariPreferiti(int idUtente) { return this.elementi.getItinerariPreferiti(idUtente); }

    //public List<PoiGenerico> selezionaPoiSalvato(int idUtente){ return this.elementi.getPoiPreferitiUtente(idUtente); }

    //public List<ItinerarioGenerico> selezionaItinerarioSalvato(int idUtente){ return this.elementi.getItinerariPreferitiUtente(idUtente); }
}
