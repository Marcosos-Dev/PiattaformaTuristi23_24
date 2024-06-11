package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.ItinerarioDTO;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.ItinerarioFactory;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItinerarioController {
    @Autowired
    ComuneRepository comuneRepository;

    public ItinerarioController() {}

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
        Comune c = this.comuneRepository.findById("Camerino").get();
        if(esito) c.inserisciItinerarioValidato(itinerario);
        c.rimuoviItinerarioDaValidare(itinerario);
        this.comuneRepository.save(c);
    }

    public void creaSegnalazione(String descrizione, ItinerarioGenerico itinerario){
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.inserisciSegnalazioneItinerario(new Segnalazione(descrizione),itinerario);
        this.comuneRepository.save(c);
    }

    public void gestisciSegnalazione(SegnalazioneItinerario segnalazione, boolean esito){
        Comune comune = this.comuneRepository.findById("Camerino").get();
        if(esito) {
            comune.rimuoviItinerario(segnalazione.getItinerarioGenerico().getIdItinerario());
            comune.rimuoviSegnalazioniItinerario(segnalazione.getItinerarioGenerico());
        } else {
            comune.rimuoviSegnalazioneItinerario(segnalazione);
        }
        this.comuneRepository.save(comune);
    }

    public SegnalazioneItinerario getSegnalazioneItinerario(int idSegnalazione){ return this.comuneRepository.findById("Camerino").get().getSegnalazioneItinerario(idSegnalazione); }

    public void rimuoviItinerario(int idItinerario){ this.comuneRepository.findById("Camerino").get().rimuoviItinerario(idItinerario); }

    public ItinerarioGenerico getItinerario(int idItinerario){ return this.comuneRepository.findById("Camerino").get().getItinerario(idItinerario); }

    public List<ItinerarioGenerico> getItinerariValidati(){ return this.comuneRepository.findById("Camerino").get().getItinerariValidati(); }

    public List<ItinerarioEvento> getItinerariEventoValidati(){ return this.comuneRepository.findById("Camerino").get().getItinerariValidati().stream().filter(i -> i.getTipo() == TipoItinerario.ITINERARIO_EVENTO).map(i -> (ItinerarioEvento) i).toList(); }

    public List<ItinerarioGenerico> getItinerarioDaValidare(){ return this.comuneRepository.findById("Camerino").get().getItinerariDaValidare(); }

    public List<PercorsoEvento> getPercorsiEventoValidati(){ return this.comuneRepository.findById("Camerino").get().getItinerariValidati().stream().filter(i -> i.getTipo() == TipoItinerario.PERCORSO_EVENTO).map(i -> (PercorsoEvento) i).toList(); }
}
