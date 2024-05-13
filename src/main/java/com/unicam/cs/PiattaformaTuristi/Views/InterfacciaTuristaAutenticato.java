package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

public class InterfacciaTuristaAutenticato {
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private ContestController contestController;
    private UtentiController utentiController;
    private Comune comune;
    private UtenteAutenticato utente;

    public InterfacciaTuristaAutenticato(Comune comune, UtenteAutenticato utente, GestoreUtenti gestore){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
        this.contestController = new ContestController(this.comune);
        this.utentiController = new UtentiController(gestore);
        this.utente = utente;
    }

    public void richiestaCambioRuolo(RuoloUtente nuovoRuolo){
        //Ottieni possibili ruoli -> utentiController.getPossibiliRuoli(this.utente.getRuolo());
        this.utentiController.richiediRuolo(this.utente.getIdUtente(),nuovoRuolo);
    }


}
