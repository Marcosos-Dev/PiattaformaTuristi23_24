package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.Richiesta;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Repositories.RichiesteRepository;
import com.unicam.cs.PiattaformaTuristi.Repositories.UtenteRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GestoreUtenti {
    @Autowired
    public UtenteRepository utentiRepository;

    @Autowired
    public RichiesteRepository richiesteRepository;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UtenteAutenticato> utenti;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Richiesta> richiesteCambioRuolo;

    public GestoreUtenti(){
        this.utenti = new ArrayList<>();
        this.richiesteCambioRuolo = new ArrayList<>();
    }

    public boolean autenticaUtente(String username, String password){ return this.utenti.stream().anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password)); }

    public void aggiungiUtente(UtenteAutenticato utente){
        this.utenti.add(utente);
        this.utentiRepository.save(utente);
    }

    public void aggiungiRichiestaRuolo(Richiesta richiesta){
        this.richiesteCambioRuolo.add(richiesta);
        this.richiesteRepository.save(richiesta);
    }

    public void rimuoviRichiestaRuolo(Richiesta richiesta) {
        this.richiesteCambioRuolo.remove(richiesta);
        this.richiesteRepository.delete(richiesta);
    }

    public void modificaRuolo(int idUtente, RuoloUtente ruolo){
        this.getUtente(idUtente).setRuolo(ruolo);
        this.utentiRepository.save(getUtente(idUtente));
    }

    public List<Richiesta> getRichiesteCambioRuolo() { return richiesteCambioRuolo; }

    public List<UtenteAutenticato> getUtenti() { return utenti; }

    public List<UtenteAutenticato> getTuttiContributori(){
        return this.utenti.stream().filter(u -> u.getRuolo() == RuoloUtente.CONTRIBUTORE || u.getRuolo() == RuoloUtente.CONTRIBUTORE_AUTORIZZATO).toList();
    }

    public UtenteAutenticato getUtente(int idUtente){
        return getUtenti().stream().filter(u -> u.getIdUtente()==idUtente).findFirst().orElse(null);
    }

    public int getUltimoIdRichiesta(){
        return this.getRichiesteCambioRuolo().isEmpty() ?
                1 : this.getRichiesteCambioRuolo().getLast().getIdRichiesta()+1;
    }

    public UtenteAutenticato getUtenteTramiteUsername(String username){
        return getUtenti().stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    public int getUltimoIdUtente(){
        return this.getUtenti().isEmpty() ?
                1 : this.getUtenti().getLast().getIdUtente()+1;
    }
}
