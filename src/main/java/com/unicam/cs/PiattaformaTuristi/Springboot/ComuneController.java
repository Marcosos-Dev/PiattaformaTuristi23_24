package com.unicam.cs.PiattaformaTuristi.Springboot;

import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.UtenteAutenticatoDto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

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
}
