package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;

import java.time.LocalDateTime;

public class TempoController {
    private ItinerarioController itinerarioController;
    private PoiController poiController;

    public TempoController(Comune comune){
        this.poiController = new PoiController(comune);
        this.itinerarioController = new ItinerarioController(comune);
    }

    public void EliminaElementiScaduti(){
        this.poiController.getPoiEventoValidati().stream().filter(p -> LocalDateTime.now().isAfter(p.getPeriodo().getDataFine())).
                forEach(p -> this.poiController.rimuoviPoi(p.getIdPoi()));
        this.itinerarioController.getItinerariEventoValidati().stream().filter(i -> LocalDateTime.now().isAfter(i.getPeriodo().getDataFine())).
                forEach(i -> this.itinerarioController.rimuoviItinerario(i.getIdItinerario()));
        this.itinerarioController.getPercorsiEventoValidati().stream().filter(i -> LocalDateTime.now().isAfter(i.getPeriodo().getDataFine())).
                forEach(i -> this.itinerarioController.rimuoviItinerario(i.getIdItinerario()));
    }


}
