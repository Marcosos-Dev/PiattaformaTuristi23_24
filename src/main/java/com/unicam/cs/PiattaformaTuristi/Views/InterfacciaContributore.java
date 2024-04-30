package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.*;

import java.util.List;
import java.util.stream.Stream;

public class InterfacciaContributore {
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private Comune comune;
    private UtenteAutenticato utente;

    public InterfacciaContributore(Comune comune, UtenteAutenticato utente){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
        this.utente = utente;
    }

    public void aggiungiPoi(PoiGenerico poi, Periodo periodo) {
        PoiFactory factory;
        switch (poi.getTipo()){
            case TipoPoi.POI -> factory = new PoiCreator();
            case TipoPoi.EVENTO -> factory = new PoiEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        if(utente.getRuolo()==RuoliUtenti.CONTRIBUTORE_AUTORIZZATO)
            this.poiController.creaPoiValidato(factory,poi,null,periodo);
        else
            this.poiController.creaPoiDaValidare(factory,poi,null,periodo);
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
        if(utente.getRuolo()==RuoliUtenti.CONTRIBUTORE_AUTORIZZATO)
            this.itinerarioController.creaItinerarioValidato(factory,itinerario,listaPoi,periodo);
        else
            this.itinerarioController.creaItinerarioDaValidare(factory,itinerario,listaPoi,periodo);
    }

    //TODO realizzare
    public void partecipaContest(){

    }

    public void caricaContenuto(Contenuto c, int idPoi){
        if(utente.getRuolo()==RuoliUtenti.CONTRIBUTORE_AUTORIZZATO)
            this.poiController.caricaContenutoValidato(c,idPoi);
        else
            this.poiController.caricaContenutoDaValidare(c,idPoi);
    }

    public PoiGenerico getPoi(int idPoi){ return this.poiController.getPoi(idPoi); }

    public List<PoiGenerico> getPoiDaValidare(){
        return this.poiController.getPoiDaValidare();
    }

    public List<PoiGenerico> getPoiValidati(){
        return this.poiController.getPoiValidati();
    }

    public List<ItinerarioGenerico> getItinerariDaValidare(){
        return this.itinerarioController.getItinerarioDaValidare();
    }

    public List<ItinerarioGenerico> getItinerariValidati(){
        return this.itinerarioController.getItinerarioValidato();
    }

    public void stampaPOI(){
        for(PoiGenerico p : Stream.concat(getPoiValidati().stream(), getPoiDaValidare().stream()).toList()){
            System.out.println(p);
        }
    }

    public void stampaItinerari(){
        for(ItinerarioGenerico i : Stream.concat(getItinerariValidati().stream(), getItinerariDaValidare().stream()).toList()){
            System.out.println(i);
            for(PoiGenerico p : i.getPoi())
                System.out.println(p);
        }
    }
}
