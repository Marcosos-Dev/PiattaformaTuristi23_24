package com.unicam.cs.PiattaformaTuristi.Springboot;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.ContestDTO;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.ItinerarioDTO;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.PoiDTO;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.*;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import com.unicam.cs.PiattaformaTuristi.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComuneController {
    @Autowired
    PoiController poiController;
    @Autowired
    ItinerarioController itinerarioController;
    @Autowired
    ContestController contestController;
    @Autowired
    ComuneRepository comuneRepository;
    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping(value = {"turista_autenticato/visualizzaItinerario", "/contributore/visualizzaItinerario","contributore_autorizzato/visualizzaItinerario"})
    public ResponseEntity<Object> GetItinerario(@RequestParam("idItinerario") int idItinerario) {
        ItinerarioGenerico itinerario = this.itinerarioController.selezionaItinerario(idItinerario);
        if(itinerario == null)
            return new ResponseEntity<>("Itinerario non trovato", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itinerario, HttpStatus.OK);
    }
    @GetMapping(value = {"turista_autenticato/visualizzaContestChiuso", "/contributore/visualizzaContestChiuso","contributore_autorizzato/visualizzaContestChiuso"})
    public ResponseEntity<Object> GetContest(@RequestParam("idContest") int idContest) {
        Contest contest = this.contestController.selezionaContest(idContest);
        if(contest == null)
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contest, HttpStatus.OK);
    }
    @GetMapping(value = {"turista_autenticato/visualizzaPoi", "/contributore/visualizzaPoi","contributore_autorizzato/visualizzaPoi"})
    public ResponseEntity<Object> GetPoi(@RequestParam("idPoi") int idPoi) {
        PoiGenerico poi = this.poiController.selezionaPoi(idPoi);
        if(poi == null)
            return new ResponseEntity<>("Poi non trovato", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(poi, HttpStatus.OK);
    }

    @GetMapping(value = {"turista_autenticato/visualizzaTuttiPoi", "/contributore/visualizzaTuttiPoi","contributore_autorizzato/visualizzaTuttiPoi"})
    public ResponseEntity<Object> GetTuttiPoi() {
        return new ResponseEntity<>(poiController.getPoiValidati(), HttpStatus.OK);
    }

    @GetMapping(value = {"turista_autenticato/visualizzaTuttiItinerari", "/contributore/visualizzaTuttiItinerari","contributore_autorizzato/visualizzaTuttiItinerari"})
    public ResponseEntity<Object> GetTuttiItinerari() {
        return new ResponseEntity<>(itinerarioController.getItinerariValidati(), HttpStatus.OK);
    }

    @GetMapping(value = {"turista_autenticato/visualizzaContestAperti", "/contributore/visualizzaContestAperti","contributore_autorizzato/visualizzaContestAperti"})
    public ResponseEntity<Object> GetTuttiContestAperti() {
        return new ResponseEntity<>(contestController.getContestAperti(), HttpStatus.OK);
    }

    @GetMapping(value = {"turista_autenticato/visualizzaContestChiusi", "/contributore/visualizzaContestChiusi","contributore_autorizzato/visualizzaContestChiusi"})
    public ResponseEntity<Object> GetTuttiContestChiusi() {
        return new ResponseEntity<>(contestController.getContestChiusi(), HttpStatus.OK);
    }

    @PostMapping("contributore/inserisciPoiDaValidare")
    public ResponseEntity<Object> creaPoiDaValidare(@RequestPart("poiDto") PoiDTO poi, @RequestPart("Contenuto") MultipartFile fileContenuto, @RequestParam("descr") String descrContenuto) {
        Comune c = this.comuneRepository.findById("Camerino").get();
        if(poi.getTitolo().isEmpty() || poi.getTitolo()==null)
            return new ResponseEntity<>("Titolo vuoto o nullo", HttpStatus.BAD_REQUEST);
        if(fileContenuto!=null && !fileContenuto.isEmpty()){
            if(!this.poiController.validaEstensioneFile(fileContenuto.getOriginalFilename()))
                return new ResponseEntity<>("Estensione non valida", HttpStatus.BAD_REQUEST);
            try{
                poi.setContenuto(new Contenuto(new File(fileContenuto.getOriginalFilename()), descrContenuto)); //TODO FIX URL
            } catch(Exception e) {
                return new ResponseEntity<>("File non ottenuto", HttpStatus.BAD_REQUEST);
            }
        }
        if(!c.internoAlComune(poi.getCoord()) || c.poiDuplicato(poi.getCoord()))
            return new ResponseEntity<>("Punto già presente o fuori dal comune", HttpStatus.BAD_REQUEST);
        PoiFactory factory;
        switch (poi.getTipo()){
            case POI -> factory = new PoiCreator();
            case EVENTO -> factory = new PoiEventoCreator();
            default -> {
                return new ResponseEntity<>("Tipo non valido", HttpStatus.BAD_REQUEST);
            }
        }
        this.poiController.creaPoiDaValidare(factory,poi);
        return new ResponseEntity<>("Poi da validare creato con successo", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciPoiValidato")
    public ResponseEntity<Object> creaPoiValidato(@RequestPart("poiDto") PoiDTO poi, @RequestPart("Contenuto") MultipartFile fileContenuto, @RequestParam("descr") String descrContenuto) {
        Comune c = this.comuneRepository.findById("Camerino").get();
        if(poi.getTitolo().isEmpty() && poi.getTitolo()==null)
            return new ResponseEntity<>("Titolo vuoto o nullo", HttpStatus.BAD_REQUEST);
        if(fileContenuto!=null && !fileContenuto.isEmpty()){
            if(!this.poiController.validaEstensioneFile(fileContenuto.getOriginalFilename()))
                return new ResponseEntity<>("Estensione non valida", HttpStatus.BAD_REQUEST);
            try{
                poi.setContenuto(new Contenuto(new File(fileContenuto.getOriginalFilename()), descrContenuto)); //TODO FIX URL
            } catch(Exception e) {
                return new ResponseEntity<>("File non ottenuto", HttpStatus.BAD_REQUEST);
            }
        }
        if(!c.internoAlComune(poi.getCoord()) || c.poiDuplicato(poi.getCoord()))
          return new ResponseEntity<>("Punto già presente o fuori dal comune", HttpStatus.BAD_REQUEST);
        PoiFactory factory;
        switch (poi.getTipo()){
            case POI -> factory = new PoiCreator();
            case EVENTO -> factory = new PoiEventoCreator();
            default -> {
                return new ResponseEntity<>("Tipo non valido", HttpStatus.BAD_REQUEST);
            }
        }
        this.poiController.creaPoiValidato(factory,poi);
        return new ResponseEntity<>("Poi validato creato con successo", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciItinerarioValidato")
    public ResponseEntity<Object> creaItinerarioValidato(@RequestPart("itinerarioDto") ItinerarioDTO itinerario) {
        if(itinerario.getTitolo()==null || itinerario.getTitolo().isEmpty())
            return new ResponseEntity<>("Titolo vuoto o nullo", HttpStatus.BAD_REQUEST);
        if(itinerario.getPoi().size()<2)
            return new ResponseEntity<>("L'itinerario deve essere composto da almeno 2 POI", HttpStatus.BAD_REQUEST);
        ItinerarioFactory factory;
        switch (itinerario.getTipo()){
            case ITINERARIO -> factory = new ItinerarioCreator();
            case ITINERARIO_EVENTO -> factory = new ItinerarioEventoCreator();
            case PERCORSO -> factory = new PercorsoCreator();
            case PERCORSO_EVENTO -> factory = new PercorsoEventoCreator();
            default -> {
                return new ResponseEntity<>("Tipo non valido", HttpStatus.BAD_REQUEST);
            }
        }
        this.itinerarioController.creaItinerarioValidato(factory,itinerario);
        return new ResponseEntity<>("Itinerario validato creato con successo", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciItinerarioDaValidare")
    public ResponseEntity<Object> creaItinerarioDaValidare(@RequestPart("itinerarioDto") ItinerarioDTO itinerario) {
        if(itinerario.getTitolo()==null || itinerario.getTitolo().isEmpty())
            return new ResponseEntity<>("Titolo vuoto o nullo", HttpStatus.BAD_REQUEST);
        if(itinerario.getPoi().size()<2)
            return new ResponseEntity<>("L'itinerario deve essere composto da almeno 2 POI", HttpStatus.BAD_REQUEST);
        ItinerarioFactory factory;
        switch (itinerario.getTipo()){
            case ITINERARIO -> factory = new ItinerarioCreator();
            case ITINERARIO_EVENTO -> factory = new ItinerarioEventoCreator();
            case PERCORSO -> factory = new PercorsoCreator();
            case PERCORSO_EVENTO -> factory = new PercorsoEventoCreator();
            default -> {
                return new ResponseEntity<>("Tipo non valido", HttpStatus.BAD_REQUEST);
            }
        }
        this.itinerarioController.creaItinerarioDaValidare(factory,itinerario);
        return new ResponseEntity<>("Itinerario da validare creato con successo", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciContenutoValidatoPoi")
    public ResponseEntity<Object> inserisciContenutoValidatoPoi(@RequestParam("idPOI") Integer idPoi, @RequestPart("file") MultipartFile fileContenuto, @RequestParam("descr") String descrContenuto) {
        if(this.comuneRepository.findById("Camerino").get().getPoi(idPoi)==null)
            return new ResponseEntity<>("Poi non trovato", HttpStatus.NOT_FOUND);
        if(!this.poiController.validaEstensioneFile(fileContenuto.getOriginalFilename()))
            return new ResponseEntity<>("Estensione non valida", HttpStatus.BAD_REQUEST);

        Contenuto contenuto = new Contenuto(new File(fileContenuto.getOriginalFilename()),descrContenuto);
        this.poiController.caricaContenutoValidato(contenuto,idPoi);
        return new ResponseEntity<>("Contenuto validato caricato con successo", HttpStatus.OK);
    }

    @PostMapping("contributore/inserisciContenutoDaValidarePoi")
    public ResponseEntity<Object> inserisciContenutoDaValidarePoi(@RequestParam("idPOI") Integer idPoi, @RequestPart("file") MultipartFile fileContenuto, @RequestParam("descr") String descrContenuto) {
        if(this.comuneRepository.findById("Camerino").get().getPoi(idPoi)==null)
            return new ResponseEntity<>("Poi non trovato", HttpStatus.NOT_FOUND);
        if(!this.poiController.validaEstensioneFile(fileContenuto.getOriginalFilename()))
            return new ResponseEntity<>("Estensione non valida", HttpStatus.BAD_REQUEST);

        Contenuto contenuto = new Contenuto(new File(fileContenuto.getOriginalFilename()),descrContenuto);
        this.poiController.caricaContenutoDaValidare(contenuto,idPoi);
        return new ResponseEntity<>("Contenuto da validare caricato con successo", HttpStatus.OK);
    }

    @PostMapping("animatore/creaContest")
    public ResponseEntity<Object> creaContest(@RequestPart("ContestDTO") ContestDTO contest) {
        if(contest.getTitolo()==null || contest.getTitolo().isEmpty())
            return new ResponseEntity<>("Titolo vuoto o nullo", HttpStatus.BAD_REQUEST);
        this.contestController.creaContest(
                new Contest(contest.getTitolo(),contest.getDescrizione(),contest.getPrivato()),
                utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        return new ResponseEntity<>("Contest creato con successo", HttpStatus.OK);
    }

    @PostMapping("animatore/invitaUtente")
    public ResponseEntity<Object> invitaUtenteContest(@RequestParam("id") Integer IdContest, @RequestParam("Utenti") List<Integer> IdUtenti) {
        Contest contestUtente = this.contestController.getContestUtente
                (utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()))
                .stream().findFirst().filter(c -> c.getPrivato() && c.getIdContest()==IdContest).orElse(null);
        if(contestUtente==null)
            return new ResponseEntity<>("Nessun contest trovato con l'ID fornito", HttpStatus.NOT_FOUND);
        List<UtenteAutenticato> utentiInvitabili = this.contestController.getUtentiInvitabili(contestUtente);
        if(utentiInvitabili.size()<IdUtenti.size())
            return new ResponseEntity<>("Assicurarsi che tutti gli utenti da invitare esistano", HttpStatus.BAD_REQUEST);
        if(utentiInvitabili.stream().anyMatch(u -> IdUtenti.stream().noneMatch(i -> i == u.getIdUtente())))
            return new ResponseEntity<>("Assicurarsi che tutti gli utenti da invitare non siano già stati invitati", HttpStatus.BAD_REQUEST);
        List<UtenteAutenticato> utentiDaInvitare = new ArrayList<>();
        this.utenteRepository.findAllById(IdUtenti).iterator().forEachRemaining(utentiDaInvitare::add);
        this.contestController.invitaUtenti(contestUtente, utentiDaInvitare);
        return new ResponseEntity<>("Utenti invitati con successo", HttpStatus.OK);
    }

    @PostMapping(value = {"/contributore/caricaContenutoContest","contributore_autorizzato/caricaContenutoContest"})
    public ResponseEntity<Object> caricaContenutoContest(@RequestParam("id") Integer IdContest, @RequestPart("file") MultipartFile fileContenuto, @RequestParam("descr") String descrContenuto) {
        UtenteAutenticato utente = utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        Contest contestUtente =  this.contestController.getTuttiContestPartecipabili(utente)
                .stream().findFirst().filter(c -> c.getIdContest()==IdContest).orElse(null);
        if(contestUtente==null)
            return new ResponseEntity<>("Nessun contest trovato con l'ID fornito", HttpStatus.NOT_FOUND);
        Contenuto contenuto;
        try{
            contenuto = new Contenuto(new File(fileContenuto.getOriginalFilename()), descrContenuto);
        } catch(Exception e) {
            return new ResponseEntity<>("File non ottenuto", HttpStatus.BAD_REQUEST);
        }

        this.contestController.partecipaContest(contestUtente, new ContenutoContest(contenuto,utente));
        return new ResponseEntity<>("Contenuto caricato con successo", HttpStatus.OK);
    }

    @PostMapping("animatore/selezionaVincitoreContest")
    public ResponseEntity<Object> selezionaVincitoreContest(@RequestParam("id") Integer IdContest, @RequestParam("utente") Integer IdUtente) {
        Contest contestUtente = this.contestController.getContestUtente
                        (utenteRepository.GetUtenteDaUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()))
                .stream().findFirst().filter(c -> c.getIdContest()==IdContest).orElse(null);
        if(contestUtente==null)
            return new ResponseEntity<>("Nessun contest trovato con l'ID fornito", HttpStatus.NOT_FOUND);
        if(contestUtente.getPrivato() && contestUtente.getInvitati().stream().noneMatch(u -> u.getIdUtente()==IdUtente))
            return new ResponseEntity<>("L'utente fornito non è stato invitato al contest", HttpStatus.BAD_REQUEST);
        ContenutoContest vincitore = contestUtente.getContenutiCaricati().stream().findFirst().filter(c -> c.getUtente().getIdUtente()==IdUtente).orElse(null);
        if(vincitore==null)
            return new ResponseEntity<>("L'utente fornito non ha partecipato al contest", HttpStatus.BAD_REQUEST);
        this.contestController.setVincitoreContest(contestUtente, vincitore);
        return new ResponseEntity<>("Vincitore selezionato con successo", HttpStatus.OK);
    }
    @PostMapping("curatore/validaElemento")
    public ResponseEntity<Object> validaElemento(@RequestParam("id") Integer idElemento, @RequestParam("tipo") String tipoElemento, @RequestParam("esito") boolean esito){
        switch(tipoElemento.toUpperCase()){
            case "POI":{
                PoiGenerico poi = this.poiController.getPoiDaValidare().stream().findFirst().filter(p->p.getIdPoi()==idElemento).orElse(null);
                if(poi == null)
                    return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
                this.poiController.validaPoi(poi,esito);
                break;
            }

            case "ITINERARIO":{
                ItinerarioGenerico itinerario = this.itinerarioController.getItinerarioDaValidare().stream().findFirst().filter(i->i.getIdItinerario()==idElemento).orElse(null);
                if(itinerario == null)
                    return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
                this.itinerarioController.validaItinerario(itinerario,esito);
                break;
            }
            case "CONTENUTO":{
                PoiGenerico poiDelContenuto = this.poiController.getPoiConContenutiDaValidare().stream().findFirst().filter(
                        p -> this.poiController.getContenuto(p,idElemento) != null
                ).orElse(null);
                if(poiDelContenuto == null)
                    return new ResponseEntity<>(tipoElemento+" non trovato", HttpStatus.NOT_FOUND);
                this.poiController.validaContenuto(poiDelContenuto,this.poiController.getContenuto(poiDelContenuto,idElemento),esito);
                break;
            }
            default :
                return new ResponseEntity<>(tipoElemento+" non esiste", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tipoElemento+" "+(esito ? "validato":"eliminato")+" con successo", HttpStatus.OK);
    }
}
