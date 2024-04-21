package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Model.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.*;

import java.util.List;
import java.util.stream.Stream;

public class ProgramExecutor {

    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private Comune comune;

    public ProgramExecutor(Comune comune){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
    }

    public void aggiungiPoiDaValidare(PoiGenerico poi, Periodo periodo){
        PoiFactory factory;
        switch (poi.getTipo()){
            case TipoPoi.POI -> factory = new PoiCreator();
            case TipoPoi.EVENTO -> factory = new PoiEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        poiController.creaPoiDaValidare(factory,poi,periodo);
    }

    public void aggiungiPoiValidato(PoiGenerico poi, Periodo periodo){
        PoiFactory factory;
        switch (poi.getTipo()){
            case TipoPoi.POI -> factory = new PoiCreator();
            case TipoPoi.EVENTO -> factory = new PoiEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        this.poiController.creaPoiValidato(factory,poi,periodo);
    }

    public void aggiungiItinerarioDaValidare(ItinerarioGenerico itinerario, List<PoiGenerico> listaPoi, Periodo periodo){
        ItinerarioFactory factory;
        switch (itinerario.getTipo()){
            case TipoItinerario.ITINERARIO -> factory = new ItinerarioCreator();
            case TipoItinerario.ITINERARIO_EVENTO -> factory = new ItinerarioEventoCreator();
            case TipoItinerario.PERCORSO -> factory = new PercorsoCreator();
            case TipoItinerario.PERCORSO_EVENTO -> factory = new PercorsoEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        this.itinerarioController.creaItinerarioDaValidare(factory,itinerario,listaPoi,periodo);
    }

    public void aggiungiItinerarioValidato(ItinerarioGenerico itinerario, List<PoiGenerico> listaPoi, Periodo periodo){
        ItinerarioFactory factory;
        switch (itinerario.getTipo()){
            case TipoItinerario.ITINERARIO -> factory = new ItinerarioCreator();
            case TipoItinerario.ITINERARIO_EVENTO -> factory = new ItinerarioEventoCreator();
            case TipoItinerario.PERCORSO -> factory = new PercorsoCreator();
            case TipoItinerario.PERCORSO_EVENTO -> factory = new PercorsoEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        this.itinerarioController.creaItinerarioValidato(factory,itinerario,listaPoi,periodo);
    }

    public PoiGenerico ottieniPoi(PoiGenerico poi){
        return this.poiController.ottieniPoi(poi);
    }


    public List<PoiGenerico> ottieniPoiDaValidare(){
        return this.poiController.ottieniListaPoiDaValidare();
    }

    public List<PoiGenerico> ottieniPoiValidati(){
        return this.poiController.ottieniListaPoiValidati();
    }

    public List<ItinerarioGenerico> ottieniItinerariDaValidare(){
        return this.itinerarioController.ottieniListaItinerariDaValidare();
    }

    public List<ItinerarioGenerico> ottieniItinerariValidati(){
        return this.itinerarioController.ottieniListaItinerariValidati();
    }

    public void stampaPOI(){
        for(PoiGenerico p : Stream.concat(ottieniPoiValidati().stream(), ottieniPoiDaValidare().stream()).toList()){
            System.out.println(p);
        }
    }

    public void stampaItinerari(){
        for(ItinerarioGenerico i : Stream.concat(ottieniItinerariValidati().stream(), ottieniItinerariDaValidare().stream()).toList()){
            System.out.println(i);
            stampaPOI();
        }
    }
}
