package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;

public class InterfacciaAnimatore {
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private UtentiController gestoreUtenti;
    private Comune comune;

    public InterfacciaAnimatore(Comune comune){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
    }

    public void creaContest(){

    }

    public void selezionaVincitoreContest(){

    }

    public void invitaUtente(){

    }
}
