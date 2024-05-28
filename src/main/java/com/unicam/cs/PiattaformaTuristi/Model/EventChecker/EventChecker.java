package com.unicam.cs.PiattaformaTuristi.Model.EventChecker;

import com.unicam.cs.PiattaformaTuristi.Controllers.TempoController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;


public class EventChecker {
    private TempoController tempoController;

    public EventChecker(Comune c){
        this.tempoController = new TempoController(c);
    }

    public void EliminaElementiScaduti(){
        this.tempoController.EliminaElementiScaduti();
    }
}
