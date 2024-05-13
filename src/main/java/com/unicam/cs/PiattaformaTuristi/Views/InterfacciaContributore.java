package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.*;

import java.util.List;

public class InterfacciaContributore {
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private ContestController contestController;
    private UtentiController utentiController;
    private Comune comune;
    private UtenteAutenticato utente;

    public InterfacciaContributore(Comune comune, UtenteAutenticato utente, GestoreUtenti gestore){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
        this.contestController = new ContestController(this.comune);
        this.utentiController = new UtentiController(gestore);
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

    public void aggiungiItinerario(ItinerarioGenerico itinerario, List<PoiGenerico> listaPoi, Periodo periodo){
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
        if(this.utente.getRuolo() == RuoloUtente.CONTRIBUTORE_AUTORIZZATO)
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

    public void richiestaCambioRuolo(RuoloUtente nuovoRuolo){
        //Ottieni possibili ruoli -> utentiController.getPossibiliRuoli(this.utente.getRuolo());
        this.utentiController.richiediRuolo(this.utente.getIdUtente(),nuovoRuolo);
    }



    //TODO rimuovere post vis
    public List<PoiGenerico> getPoiValidati(){
        return this.poiController.getPoiValidati();
    }

    public List<ItinerarioGenerico> getItinerariValidati(){
        return this.itinerarioController.getItinerarioValidato();
    }

}
