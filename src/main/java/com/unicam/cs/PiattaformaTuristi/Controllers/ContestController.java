package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.ContenutoContest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;

import java.util.List;
import java.util.stream.Stream;

public class ContestController {
    private Comune comune;

    public ContestController(Comune comune){
        this.comune = comune;
    }

    public void creaContest(Contest contest){
        if(contest.getTitolo()==null || contest.getTitolo().isEmpty())
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        contest.setIdContest(comune.getLastContestId()); //TODO rimuovere con aggiunto di database
        this.comune.inserisciContestAperto(contest);
    }

    //Metodo di utilità per far selezionare all'utente il contest
    //Per semplificare l'utilizzo non è stato usato nella vista
    public List<Contest> getTuttiContestPartecipabili(UtenteAutenticato utente){
        return Stream.concat(
                this.comune.getContestAperti().stream().filter(c -> c.getInvitati().contains(utente)).toList().stream(),
                this.comune.getContestAperti().stream().filter(
                        c -> c.getContenutiCaricati().stream().noneMatch(contenuto -> contenuto.getUtente().equals(utente)
                        )).toList().stream()).toList();
    }

    public Contest getContest(Contest contest){
        return comune.getContestAperti().stream().filter(c -> c.equals(contest)).findAny().orElse(null);
    }

    public void partecipaContest(Contest contest, Contenuto contenuto, UtenteAutenticato utente){
        contest.addContenuto(new ContenutoContest(contenuto,utente));
    }
}
