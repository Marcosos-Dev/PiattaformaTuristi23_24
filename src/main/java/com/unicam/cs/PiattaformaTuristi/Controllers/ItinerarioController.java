package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.ItinerarioFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;

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
        itinerarioDaInserire.setIdItinerario(this.comune.getLastIdItinerario());
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
        itinerarioDaInserire.setIdItinerario(this.comune.getLastIdItinerario());
        if(itinerarioDaInserire instanceof ItinerarioEvento itinerarioE)
            itinerarioE.setPeriodo(periodo);
        if(itinerarioDaInserire instanceof PercorsoEvento percorsoE)
            percorsoE.setPeriodo(periodo);
        comune.inserisciItinerarioDaValidare(itinerarioDaInserire);
    }

    public void validaItinerario(ItinerarioGenerico itinerario, boolean esito){
        if(esito) this.comune.inserisciItinerarioValidato(itinerario);
        this.comune.removeItinerarioDaValidare(itinerario);
    }

    public ItinerarioGenerico getItinerario(int idItinerario){ return this.comune.getItinerariValidati().stream().filter(p -> p.getIdItinerario() == idItinerario).findFirst().orElse(null); }

    public List<ItinerarioGenerico> getItinerarioValidato(){
        return this.comune.getItinerariValidati();
    }

    public List<ItinerarioGenerico> getItinerarioDaValidare(){
        return this.comune.getItinerariDaValidare();
    }
}
