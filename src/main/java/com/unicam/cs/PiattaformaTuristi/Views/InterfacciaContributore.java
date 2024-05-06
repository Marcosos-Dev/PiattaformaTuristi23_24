package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.*;

import java.util.List;
import java.util.stream.Stream;

public class InterfacciaContributore {
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private ContestController contestController;
    private Comune comune;
    private UtenteAutenticato utente;

    public InterfacciaContributore(Comune comune, UtenteAutenticato utente){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
        this.contestController = new ContestController(this.comune);
        this.utente = utente;
    }

    public void aggiungiPoi(PoiGenerico poi, Contenuto con, Periodo periodo) {
        PoiFactory factory;
        switch (poi.getTipo()){
            case POI -> factory = new PoiCreator();
            case EVENTO -> factory = new PoiEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        if(this.utente.getRuolo()== RuoloUtente.CONTRIBUTORE_AUTORIZZATO)
            this.poiController.creaPoiValidato(factory,poi,con,periodo);
        else
            this.poiController.creaPoiDaValidare(factory,poi,con,periodo);
    }

    public void aggiungiItinerarioDaValidare(ItinerarioGenerico itinerario, List<PoiGenerico> listaPoi, Periodo periodo){
        ItinerarioFactory factory;
        switch (itinerario.getTipo()){
            case ITINERARIO -> factory = new ItinerarioCreator();
            case ITINERARIO_EVENTO -> factory = new ItinerarioEventoCreator();
            case PERCORSO -> factory = new PercorsoCreator();
            case PERCORSO_EVENTO -> factory = new PercorsoEventoCreator();
            default -> {
                throw new IllegalArgumentException("Tipo non valido");
            }
        }
        if(this.utente.getRuolo()== RuoloUtente.CONTRIBUTORE_AUTORIZZATO)
            this.itinerarioController.creaItinerarioValidato(factory,itinerario,listaPoi,periodo);
        else
            this.itinerarioController.creaItinerarioDaValidare(factory,itinerario,listaPoi,periodo);
    }

    public void partecipaContest(Contest contest,Contenuto contenuto){
        this.contestController.partecipaContest(contest,contenuto,this.utente);
    }

    public void caricaContenuto(Contenuto c, int idPoi){
        if(utente.getRuolo()== RuoloUtente.CONTRIBUTORE_AUTORIZZATO)
            this.poiController.caricaContenutoValidato(c,idPoi);
        else
            this.poiController.caricaContenutoDaValidare(c,idPoi);
    }

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

}
