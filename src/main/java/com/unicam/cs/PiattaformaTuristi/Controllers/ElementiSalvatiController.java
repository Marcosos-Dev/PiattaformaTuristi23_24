package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreElementiSalvati;

import java.util.List;

public class ElementiSalvatiController {
    private GestoreElementiSalvati elementi;

    public ElementiSalvatiController(GestoreElementiSalvati elementi){
        this.elementi = elementi;
    }

    public void salvaPoi(UtenteAutenticato utente, PoiGenerico poi){
        PoiPreferito preferito = new PoiPreferito(poi, utente);
        preferito.setIdElemento(this.elementi.getUltimoIdPreferito());
        this.elementi.aggiungiPoiPreferito(preferito);
    }

    public void salvaItinerario(UtenteAutenticato utente, ItinerarioGenerico itinerario){
        ItinerarioPreferito preferito = new ItinerarioPreferito(itinerario, utente);
        preferito.setIdElemento(this.elementi.getUltimoIdPreferito());
        this.elementi.aggiungiItinerarioPreferito(preferito);
    }

    public void eliminaPoi(PoiPreferito preferito) { this.elementi.eliminaPoiPreferito(preferito); }

    public void eliminaItinerari(ItinerarioPreferito itinerario) { this.elementi.eliminaItinerarioPreferito(itinerario); }

    public List<PoiPreferito> getPoiPreferiti(){ return this.elementi.getPoiPreferiti(); }

    public List<ItinerarioPreferito> getItinerariPreferiti() { return this.elementi.getItinerariPreferiti(); }

    public List<PoiGenerico> selezionaPoiSalvato(int idUtente){ return this.elementi.getPoiPreferitiUtente(idUtente); }

    public List<ItinerarioGenerico> selezionaItinerarioSalvato(int idUtente){ return this.elementi.getItinerariPreferitiUtente(idUtente); }
}
