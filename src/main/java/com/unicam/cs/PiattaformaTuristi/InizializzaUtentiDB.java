package com.unicam.cs.PiattaformaTuristi;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import com.unicam.cs.PiattaformaTuristi.Repositories.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InizializzaUtentiDB {

    @Bean
    CommandLineRunner initDatabase(UtenteRepository repository, GestoreUtenti utentiAutenticatiManager) {
        return args -> {
            UtenteAutenticato u1 = new UtenteAutenticato("turista_auth@gmail.it", "turista_auth");
            UtenteAutenticato u2 = new UtenteAutenticato("contributore@gmail.it", "contributore");
            UtenteAutenticato u3 = new UtenteAutenticato("contributore_auth@gmail.it", "contributore_auth");
            UtenteAutenticato u4 = new UtenteAutenticato("animatore@gmail.it", "animatore");
            UtenteAutenticato u5 = new UtenteAutenticato("curatore@gmail.it", "curatore");
            UtenteAutenticato u6 = new UtenteAutenticato("gestore@gmail.it", "gestore");
            u1.setRuolo(RuoloUtente.TURISTA_AUTENTICATO);
            u2.setRuolo(RuoloUtente.CONTRIBUTORE);
            u3.setRuolo(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
            u4.setRuolo(RuoloUtente.ANIMATORE);
            u5.setRuolo(RuoloUtente.CURATORE);
            u6.setRuolo(RuoloUtente.GESTORE_PIATTAFORMA);
            repository.save(u1);
            repository.save(u2);
            repository.save(u3);
            repository.save(u4);
            repository.save(u5);
            repository.save(u6);
            utentiAutenticatiManager.aggiungiUtente(u1);
            utentiAutenticatiManager.aggiungiUtente(u2);
            utentiAutenticatiManager.aggiungiUtente(u3);
            utentiAutenticatiManager.aggiungiUtente(u4);
            utentiAutenticatiManager.aggiungiUtente(u5);
            utentiAutenticatiManager.aggiungiUtente(u6);
            //comuneRepository.save(new Comune("Camerino",new Coordinates(43.1351,13.0683), u5));
        };
    }
}
