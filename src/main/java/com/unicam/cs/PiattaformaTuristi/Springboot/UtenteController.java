package com.unicam.cs.PiattaformaTuristi.Springboot;

import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.RichiestaDTO;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.UtenteAutenticatoDto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Richiesta;
import com.unicam.cs.PiattaformaTuristi.Repositories.RichiesteRepository;
import com.unicam.cs.PiattaformaTuristi.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

//http://localhost:8080/swagger-ui/index.html#/
//http://localhost:8080/h2-console/
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtenteController {
    @Autowired
    public UtenteRepository utentiRepository;

    @Autowired
    public RichiesteRepository richiesteRepository;

    @Autowired
    private UtentiController utentiController;

    @PostMapping("/registrazione")
    public ResponseEntity<Object> registrationUser(@RequestBody UtenteAutenticatoDto utente) {
        /*
        * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>("Utente già autenticato o ruolo non disponibile alla registrazione", HttpStatus.BAD_REQUEST);
        }
        * */
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()_-])(?=\\S+$).{8,}$");
        if(utente.getUsername() == null || utente.getUsername().isEmpty())
            return new ResponseEntity<>("Username non valido (vuoto)", HttpStatus.BAD_REQUEST);
        if(this.utentiController.getUtenteTramiteUsername(utente.getUsername())!=null)
            return new ResponseEntity<>("Username non valido, esiste già un utente con lo stesso username", HttpStatus.BAD_REQUEST);
        if(utente.getPassword() == null || utente.getPassword().isEmpty() || !pattern.matcher(utente.getPassword()).matches())
            return new ResponseEntity<>("Password non valida", HttpStatus.BAD_REQUEST);
        this.utentiController.registraUtente(utente.getUsername(), utente.getPassword());
        return new ResponseEntity<>("Utente registrato con successo", HttpStatus.OK);
    }

    @PostMapping("/animatore/richiediRuolo")
    public ResponseEntity<Object> richiediRuolo(@RequestBody RichiestaDTO richiesta){
        if(utentiRepository.findById(richiesta.getIdUtente()).isEmpty())
            return new ResponseEntity<>("Utente non presente", HttpStatus.BAD_REQUEST);
        this.utentiController.aggiungiRichiestaRuolo(new Richiesta(richiesta.getIdUtente(), richiesta.getRuoloRichiesto()));
        return new ResponseEntity<>("Richiesta creata con successo", HttpStatus.OK);
    }

    @PostMapping("/gestore/gesticiRuolo")
    public ResponseEntity<Object> gestisciRuolo(@RequestParam("id") Integer IdRichiesta,@RequestParam("esito") Boolean esito){
        if(richiesteRepository.findById(IdRichiesta).isEmpty())
            return new ResponseEntity<>("Richiesta non presente", HttpStatus.BAD_REQUEST);
        Richiesta richiesta = richiesteRepository.findById(IdRichiesta).get();
        this.utentiController.gestisciRichiestaRuolo(richiesta,esito);
        return new ResponseEntity<>("Richiesta gestita con successo", HttpStatus.OK);
    }

    @GetMapping("/gestore/visualizzaUtenti")
    public ResponseEntity<Object> visualizzaUtentiAutenticati() {
        return new ResponseEntity<>(this.utentiController.getUtenti(), HttpStatus.OK);
    }

}
