package com.unicam.cs.PiattaformaTuristi.Springboot;

import com.unicam.cs.PiattaformaTuristi.Controllers.ElementiSalvatiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.UtenteAutenticatoDto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import com.unicam.cs.PiattaformaTuristi.Repositories.RichiesteRepository;
import com.unicam.cs.PiattaformaTuristi.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class    UtenteController {
    @Autowired
    private UtentiController utentiController;
    @Autowired
    private ElementiSalvatiController elementiSalvatiController;
    @Autowired
    private PoiController poiController;
    @Autowired
    private ItinerarioController itinerarioController;
    @Autowired
    public UtenteRepository utenteRepository;
    @Autowired
    public RichiesteRepository richiesteRepository;

    @PostMapping("/registrazione")
    public ResponseEntity<Object> registrazione(@RequestBody UtenteAutenticatoDto utente) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))) {
            return new ResponseEntity<>("Utente già autenticato", HttpStatus.BAD_REQUEST);
        }
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()_-])(?=\\S+$).{8,}$");
        if(utente.getUsername() == null || utente.getUsername().isEmpty())
            return new ResponseEntity<>("Username non valido (vuoto)", HttpStatus.BAD_REQUEST);
        if(this.utentiController.getUtenteDaUsername(utente.getUsername())!=null)
            return new ResponseEntity<>("Username non valido, esiste già un utente con lo stesso username", HttpStatus.BAD_REQUEST);
        if(utente.getPassword() == null || utente.getPassword().isEmpty() || !pattern.matcher(utente.getPassword()).matches())
            return new ResponseEntity<>("Password non valida", HttpStatus.BAD_REQUEST);
        this.utentiController.registraUtente(utente.getUsername(), utente.getPassword());
        return new ResponseEntity<>("Utente registrato con successo", HttpStatus.OK);
    }

    @PostMapping(value = {"turista_autenticato/richiediRuolo", "/contributore/richiediRuolo","contributore_autorizzato/richiediRuolo","animatore/richiediRuolo"})
    public ResponseEntity<Object> richiediRuolo(@RequestParam("ruolo") RuoloUtente ruoloRichiesto){
        if(!RuoloUtente.getPossibiliRuoliDefault().contains(ruoloRichiesto))
            return new ResponseEntity<>("Il ruolo richiesto non è valido", HttpStatus.BAD_REQUEST);
        this.utentiController.aggiungiRichiestaRuolo(new Richiesta(
                utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getIdUtente(),
                ruoloRichiesto));
        return new ResponseEntity<>("Richiesta creata con successo", HttpStatus.OK);
    }

    @PostMapping("/gestore/gesticiRichiestaRuolo")
    public ResponseEntity<Object> gestisciRichiestaRuolo(@RequestParam("id") Integer IdRichiesta,@RequestParam("esito") Boolean esito){
        if(richiesteRepository.findById(IdRichiesta).isEmpty())
            return new ResponseEntity<>("Richiesta non presente", HttpStatus.BAD_REQUEST);
        Richiesta richiesta = richiesteRepository.findById(IdRichiesta).get();
        this.utentiController.gestisciRichiestaRuolo(richiesta,esito);
        return new ResponseEntity<>("Richiesta gestita con successo", HttpStatus.OK);
    }

    @PostMapping("/gestore/gesticiRuolo")
    public ResponseEntity<Object> gestisciRuolo(@RequestParam("id") Integer IdUtente,@RequestParam("ruolo") RuoloUtente ruolo){
        if(!RuoloUtente.getPossibiliRuoliDefault().contains(ruolo))
            return new ResponseEntity<>("Il ruolo richiesto non è valido", HttpStatus.BAD_REQUEST);
        this.utentiController.gestisciRuolo(IdUtente,ruolo);
        return new ResponseEntity<>("Ruolo modificato con successo", HttpStatus.OK);
    }

    @GetMapping("/gestore/visualizzaUtenti")
    public ResponseEntity<Object> visualizzaUtentiAutenticati() {
        return new ResponseEntity<>(this.utentiController.getUtenti(), HttpStatus.OK);
    }

    @PostMapping("/turista_autenticato/salvaElemento")
    public ResponseEntity<Object> salvaElemento(@RequestParam("id") Integer idElemento,@RequestParam("tipo") String tipoElemento) {
        UtenteAutenticato utente = utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if(tipoElemento.toUpperCase().equals("POI")){
            PoiGenerico poi = this.poiController.getPoi(idElemento);
            if(poi == null)
                return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
            if(this.elementiSalvatiController.getPoiPreferiti(utente.getIdUtente()).stream().anyMatch(p -> p.getPoi().equals(poi)))
                return new ResponseEntity<>(tipoElemento+" già salvato", HttpStatus.BAD_REQUEST);
            this.elementiSalvatiController.salvaPoi(utente, poi);
        }
        else if(tipoElemento.toUpperCase().equals(("ITINERARIO"))){
            ItinerarioGenerico itinerario = this.itinerarioController.getItinerario(idElemento);
            if(itinerario == null)
                return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
            if(this.elementiSalvatiController.getItinerariPreferiti(utente.getIdUtente()).stream().anyMatch(p -> p.getItinerario().equals(itinerario)))
                return new ResponseEntity<>(tipoElemento+" già salvato", HttpStatus.BAD_REQUEST);
            this.elementiSalvatiController.salvaItinerario(utente,itinerario);
        }
        else{
            return new ResponseEntity<>(tipoElemento+" non esiste", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tipoElemento+" salvato con successo",HttpStatus.OK);
    }

    @GetMapping("/turista_autenticato/visualizzaElementiSalvati")
    public ResponseEntity<Object> visualizzaElementiSalvati(@RequestParam("tipo") String tipoElemento) {

        UtenteAutenticato utente = utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if(tipoElemento.toUpperCase().equals("POI")){
            return new ResponseEntity<>(this.elementiSalvatiController.getPoiPreferiti(utente.getIdUtente()).stream().map(x->x.getPoi()),HttpStatus.OK);
        }
        else if(tipoElemento.toUpperCase().equals(("ITINERARIO"))){
            return new ResponseEntity<>(this.elementiSalvatiController.getItinerariPreferiti(utente.getIdUtente()).stream().map(x->x.getItinerario()),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(tipoElemento+" non esiste", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/turista_autenticato/rimuoviElementoSalvato")
    public ResponseEntity<Object> rimuoviElementiSalvati(@RequestParam("id") Integer idElemento,@RequestParam("tipo") String tipoElemento) {
        UtenteAutenticato utente = utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if(tipoElemento.toUpperCase().equals("POI")){
            PoiPreferito poi = this.elementiSalvatiController.getPoiPreferiti(utente.getIdUtente())
                    .stream().findFirst().filter(p -> p.getIdElemento()==idElemento).orElse(null);
            if(poi == null)
                return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
            this.elementiSalvatiController.rimuoviPoiPreferito(utente, poi);
        }
        else if(tipoElemento.toUpperCase().equals(("ITINERARIO"))){
            ItinerarioPreferito itinerario = this.elementiSalvatiController.getItinerariPreferiti(utente.getIdUtente())
                    .stream().findFirst().filter(p -> p.getIdElemento()==idElemento).orElse(null);
            if(itinerario == null)
                return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
            this.elementiSalvatiController.rimuoviItinerarioPreferito(utente,itinerario);
        }
        else{
            return new ResponseEntity<>(tipoElemento+" non esiste", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tipoElemento+" rimosso con successo",HttpStatus.OK);
    }

}
