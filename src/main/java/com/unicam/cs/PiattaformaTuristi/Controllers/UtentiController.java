package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;

import java.util.List;

public class UtentiController {
    private GestoreUtenti gestoreUtenti;

    public UtentiController(){

    }

    public List<UtenteAutenticato> getTuttiContributori(){
        return gestoreUtenti.getTuttiContributori();
    }


}
