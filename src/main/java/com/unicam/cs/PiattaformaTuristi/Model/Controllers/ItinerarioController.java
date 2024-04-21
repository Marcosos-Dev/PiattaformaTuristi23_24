package com.unicam.cs.PiattaformaTuristi.Model.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.ItinerarioFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

import java.util.Arrays;
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
        itinerarioDaInserire.setTitolo(itinerario.getTitolo());
        itinerarioDaInserire.setDescrizione(itinerario.getDescrizione());
        if(pois.size()<2)
            throw new IllegalArgumentException("Devono essere presenti almeno 2 punti");
        itinerarioDaInserire.setPoi(pois);
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
        itinerarioDaInserire.setTitolo(itinerario.getTitolo());
        itinerarioDaInserire.setDescrizione(itinerario.getDescrizione());
        if(pois.size()<2)
            throw new IllegalArgumentException("Devono essere presenti almeno 2 punti");
        itinerarioDaInserire.setPoi(pois);
        if(itinerarioDaInserire instanceof ItinerarioEvento itinerarioE)
            itinerarioE.setPeriodo(periodo);
        if(itinerarioDaInserire instanceof PercorsoEvento percorsoE)
            percorsoE.setPeriodo(periodo);
        comune.inserisciItinerarioDaValidare(itinerarioDaInserire);
    }

    public ItinerarioGenerico ottieniItinerario(ItinerarioGenerico itinerario){
        return this.comune.getItinerariValidati().stream().filter(p -> p.equals(itinerario)).findFirst().orElse(null);
    }

    public List<ItinerarioGenerico> ottieniListaItinerariValidati(){
        return this.comune.getItinerariValidati();
    }

    public List<ItinerarioGenerico> ottieniListaItinerariDaValidare(){
        return this.comune.getItinerariDaValidare();
    }
}
