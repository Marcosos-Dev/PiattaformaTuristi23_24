package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TempoController {
    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private ItinerarioController itinerarioController;
    @Autowired
    private PoiController poiController;
    @Autowired
    private PreferitoController preferitoController;

    public TempoController(){

    }

    public TempoController(Comune comune){

    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void EliminaElementiScaduti(){
        this.poiController.getPoiEventoValidati().stream().filter(p -> LocalDateTime.now().isAfter(p.getPeriodo().getDataFine())).
                forEach(p -> {
                    this.poiController.rimuoviPoi(p.getIdPoi());
                    this.preferitoController.rimuoviPreferitiConPoi(p);
                });
        this.itinerarioController.getItinerariEventoValidati().stream().filter(i -> LocalDateTime.now().isAfter(i.getPeriodo().getDataFine())).
                forEach(i -> {
                    this.itinerarioController.rimuoviItinerario(i.getIdItinerario());
                    this.preferitoController.rimuoviPreferitiConItinerario(i);
                });
        this.itinerarioController.getPercorsiEventoValidati().stream().filter(i -> LocalDateTime.now().isAfter(i.getPeriodo().getDataFine())).
                forEach(i -> {
                    this.itinerarioController.rimuoviItinerario(i.getIdItinerario());
                    this.preferitoController.rimuoviPreferitiConItinerario(i);
                });
        comuneRepository.save(this.comuneRepository.findById("Camerino").get());
    }


}
