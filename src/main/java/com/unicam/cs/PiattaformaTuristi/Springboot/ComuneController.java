package com.unicam.cs.PiattaformaTuristi.Springboot;

import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.PoiDTO;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiCreator;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiEventoCreator;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComuneController {
    @Autowired
    PoiController poiController;
    @Autowired
    ComuneRepository comuneRepository;

    @GetMapping(value = {"turista_autenticato/visualizzaTuttiPoi", "/contributore/visualizzaTuttiPoi","contributore_autorizzato/visualizzaTuttiPoi"})
    public ResponseEntity<Object> GetTuttiPoi() {
        return new ResponseEntity<>(poiController.getPoiValidati(), HttpStatus.OK);
    }

    @PostMapping("contributore/inserisciPoiDaValidare")
    public ResponseEntity<Object> creaPoiDaValidare(@RequestPart("poiDto") PoiDTO poi,
                                                  @RequestPart("Contenuto") MultipartFile fileContenuto,
                                                  @RequestParam("descr") String descrContenuto) {
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
        return new ResponseEntity<>("Poi inserito con successo", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciPoiValidato")
    public ResponseEntity<Object> creaPoiValidato(@RequestPart("poiDto") PoiDTO poi,
                                                  @RequestPart("Contenuto") MultipartFile fileContenuto,
                                                  @RequestParam("descr") String descrContenuto) {
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
        return new ResponseEntity<>("Poi inserito con successo", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciContenutoValidatoPoi")
    public ResponseEntity<Object> inserisciContenutoValidatoPoi(@RequestParam("idPOI") Integer idPoi, @RequestPart("file") MultipartFile file, @RequestPart("descr") String descr) {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("contributore_autorizzato/inserisciContenutoDaValidarePoi")
    public ResponseEntity<Object> inserisciContenutoDaValidarePoi(@RequestParam("idPOI") Integer idPoi, @RequestPart("file") MultipartFile file, @RequestPart("descr") String descr) {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
