package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Model.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.*;

import java.util.List;

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
        poiController.creaPoiValidato(factory,poi,periodo);
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
        itinerarioController.creaItinerarioDaValidare(factory,itinerario,listaPoi,periodo);
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
        itinerarioController.creaItinerarioValidato(factory,itinerario,listaPoi,periodo);
    }

    public PoiGenerico ottieniPoi(PoiGenerico poi){
        return comune.ottieniPoi(poi);
    }


    public List<PoiGenerico> ottieniPoiDaValidare(){
        return comune.getPoiDaValidare();
    }

    public List<PoiGenerico> ottieniPoiValidati(){
        return comune.getPoiValidati();
    }

    public List<ItinerarioGenerico> ottieniItinerariDaValidare(){
        return comune.getItinerariDaValidare();
    }

    public List<ItinerarioGenerico> ottieniItinerariValidati(){
        return comune.getItinerariValidati();
    }

}
