package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.ItinerarioFactory;

import java.util.List;

public class ItinerarioController {
    private Comune comune;

    public ItinerarioController(Comune comune){
        this.comune = comune;
    }

    public void creaItinerarioDaValidare(ItinerarioFactory factory, ItinerarioGenerico itinerario, List<PoiGenerico> pois, Periodo periodo){
        ItinerarioGenerico itinerarioDaInserire = factory.creaItinerario();
        if(itinerario.getTitolo()==null || itinerario.getTitolo().isEmpty())
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        if(pois.size()<2)
            throw new IllegalArgumentException("Devono essere presenti almeno 2 punti");
        itinerarioDaInserire.setTitolo(itinerario.getTitolo());
        itinerarioDaInserire.setDescrizione(itinerario.getDescrizione());
        itinerarioDaInserire.setPoi(pois);
        itinerarioDaInserire.setIdItinerario(comune.getLastIdItinerario());
        if(itinerarioDaInserire instanceof ItinerarioEvento itinerarioE)
            itinerarioE.setPeriodo(periodo);
        if(itinerarioDaInserire instanceof PercorsoEvento percorsoE)
            percorsoE.setPeriodo(periodo);
        comune.inserisciItinerarioDaValidare(itinerarioDaInserire);
    }

    public void creaItinerarioValidato(ItinerarioFactory factory, ItinerarioGenerico itinerario, List<PoiGenerico> pois, Periodo periodo){
        ItinerarioGenerico itinerarioDaInserire = factory.creaItinerario();
        if(itinerario.getTitolo()==null || itinerario.getTitolo().isEmpty())
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        if(pois.size()<2)
            throw new IllegalArgumentException("Devono essere presenti almeno 2 punti");
        itinerarioDaInserire.setTitolo(itinerario.getTitolo());
        itinerarioDaInserire.setDescrizione(itinerario.getDescrizione());
        itinerarioDaInserire.setPoi(pois);
        itinerarioDaInserire.setIdItinerario(comune.getLastIdItinerario());
        if(itinerarioDaInserire instanceof ItinerarioEvento itinerarioE)
            itinerarioE.setPeriodo(periodo);
        if(itinerarioDaInserire instanceof PercorsoEvento percorsoE)
            percorsoE.setPeriodo(periodo);
        comune.inserisciItinerarioValidato(itinerarioDaInserire);
    }

    public void validaItinerario(ItinerarioGenerico itinerario, boolean esito){
        if(esito) this.comune.inserisciItinerarioValidato(itinerario);
        this.comune.rimuoviItinerarioDaValidare(itinerario);
    }

    public void creaSegnalazione(String descrizione, ItinerarioGenerico itinerario){
        Segnalazione segnalazione = new Segnalazione(descrizione);
        segnalazione.setIdSegnalazione(this.comune.getUltimoIdSegnalazione());
        this.comune.inserisciSegnalazioneItinerari(segnalazione,itinerario);
    }

    public void gestisciSegnalazione(boolean esito, Segnalazione segnalazione){
        int idItinerario = this.comune.getItinerarioSegnalato(segnalazione);
        if(esito) {
            this.comune.rimuoviItinerario(idItinerario);
            this.comune.rimuoviSegnalazioniItinerario(idItinerario); //Rimuove tutte le segnalazioni che hanno in oggetto l'itinerario
        } else  { this.comune.rimuoviSegnalazione(segnalazione); }
    }

    public void rimuoviItinerario(int idItinerario){ this.comune.rimuoviItinerario(idItinerario); }

    public ItinerarioGenerico selezionaItinerario(int idItinerario){ return this.comune.getItinerario(idItinerario); }

    public List<ItinerarioGenerico> getItinerariValidati(){ return this.comune.getItinerariValidati(); }

    public List<ItinerarioEvento> getItinerariEventoValidati(){ return this.comune.getItinerariValidati().stream().filter(i -> i.getTipo() == TipoItinerario.ITINERARIO_EVENTO).map(i -> (ItinerarioEvento) i).toList(); }

    public List<ItinerarioGenerico> getItinerarioDaValidare(){ return this.comune.getItinerariDaValidare(); }

    public List<PercorsoEvento> getPercorsiEventoValidati(){ return this.comune.getItinerariValidati().stream().filter(i -> i.getTipo() == TipoItinerario.PERCORSO_EVENTO).map(i -> (PercorsoEvento) i).toList(); }
}
