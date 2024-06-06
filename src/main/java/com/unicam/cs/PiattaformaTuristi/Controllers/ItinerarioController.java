package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.ItinerarioDTO;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.ItinerarioFactory;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItinerarioController {
    private Comune comune;
    @Autowired
    ComuneRepository comuneRepository;

    public ItinerarioController() {}

    public ItinerarioController(Comune comune){
        this.comune = comune;
    }

    public void creaItinerarioDaValidare(ItinerarioFactory factory, ItinerarioDTO itinerario){
        Comune c = this.comuneRepository.findById("Camerino").get();
        ItinerarioGenerico itinerarioDaInserire = factory.creaItinerario();
        itinerarioDaInserire.setTitolo(itinerario.getTitolo());
        itinerarioDaInserire.setDescrizione(itinerario.getDescrizione());
        itinerarioDaInserire.setPoi(itinerario.getPoi().stream().map(p -> c.getPoi(p)).toList());
        if(itinerarioDaInserire instanceof ItinerarioEvento itinerarioE)
            itinerarioE.setPeriodo(itinerario.getPeriodo());
        if(itinerarioDaInserire instanceof PercorsoEvento percorsoE)
            percorsoE.setPeriodo(itinerario.getPeriodo());

        c.inserisciItinerarioDaValidare(itinerarioDaInserire);
        this.comuneRepository.save(c);
    }

    public void creaItinerarioValidato(ItinerarioFactory factory, ItinerarioDTO itinerario){
        Comune c = this.comuneRepository.findById("Camerino").get();
        ItinerarioGenerico itinerarioDaInserire = factory.creaItinerario();
        itinerarioDaInserire.setTitolo(itinerario.getTitolo());
        itinerarioDaInserire.setDescrizione(itinerario.getDescrizione());
        itinerarioDaInserire.setPoi(itinerario.getPoi().stream().map(p -> c.getPoi(p)).toList());
        if(itinerarioDaInserire instanceof ItinerarioEvento itinerarioE)
            itinerarioE.setPeriodo(itinerario.getPeriodo());
        if(itinerarioDaInserire instanceof PercorsoEvento percorsoE)
            percorsoE.setPeriodo(itinerario.getPeriodo());

        c.inserisciItinerarioValidato(itinerarioDaInserire);
        this.comuneRepository.save(c);
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

    public ItinerarioGenerico selezionaItinerario(int idItinerario){ return this.comuneRepository.findById("Camerino").get().getItinerario(idItinerario); }

    public List<ItinerarioGenerico> getItinerariValidati(){ return this.comuneRepository.findById("Camerino").get().getItinerariValidati(); }

    public List<ItinerarioEvento> getItinerariEventoValidati(){ return this.comuneRepository.findById("Camerino").get().getItinerariValidati().stream().filter(i -> i.getTipo() == TipoItinerario.ITINERARIO_EVENTO).map(i -> (ItinerarioEvento) i).toList(); }

    public List<ItinerarioGenerico> getItinerarioDaValidare(){ return this.comuneRepository.findById("Camerino").get().getItinerariDaValidare(); }

    public List<PercorsoEvento> getPercorsiEventoValidati(){ return this.comuneRepository.findById("Camerino").get().getItinerariValidati().stream().filter(i -> i.getTipo() == TipoItinerario.PERCORSO_EVENTO).map(i -> (PercorsoEvento) i).toList(); }
}
