package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;

public class InterfacciaAnimatore {
    private ContestController contestController;
    private UtentiController utentiController;
    private Comune comune;

    public InterfacciaAnimatore(Comune comune){
        this.comune = comune;
        this.contestController = new ContestController(this.comune);
        this.utentiController = new UtentiController();
    }

    public void creaContest(Contest contest){
        this.contestController.creaContest(contest);
    }

    public void selezionaVincitoreContest(){

    }

    public void invitaUtente(){

    }
}
