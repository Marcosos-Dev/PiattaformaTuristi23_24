package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtenteController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;


public class InterfacciaTurista {

    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private ContestController contestController;
    private Comune comune;
    private UtenteController utenteController;

    public InterfacciaTurista(Comune comune, GestoreUtenti gestoreUtenti){
        //this.comune = comune;
        //this.poiController = new PoiController(this.comune);
        //this.itinerarioController = new ItinerarioController(this.comune);
        //this.contestController = new ContestController(this.comune);
        //this.utentiController = new UtentiController(gestoreUtenti);
    }

    public PoiGenerico visualizzaPoi(int idPoi){
        //il poi sarebbe da selezionare tra tutti i validati -> poiController.getPoiValidati()
        //System.out.println(poiController.getPoi(idPoi));
        //return poiController.selezionaPoi(idPoi);
        return null;
    }

    public ItinerarioGenerico visualizzaItinerario(int idItinerario){
        //l'itinerario sarebbe da selezionare tra tutti i validati -> itinerarioController.getItinerariValidati()
        //System.out.println(itinerarioController.getItinerario(idItinerario));
        //return itinerarioController.selezionaItinerario(idItinerario);
        return null;
    }

    public Contest visualizzaContest(int idContest){
        //il contest sarebbe da selezionare tra tutti quelli chiusi -> contestController.getContestChiusi()
        //System.out.println(contestController.getContest(idContest));
        //return contestController.selezionaContest(idContest);
        return null;
    }

    public void registraUtente(String username, String password){
        //this.utentiController.registraUtente(username,password);
    }

    public void segnalaPoi(String descrizione, PoiGenerico poi){ /*this.poiController.creaSegnalazione(descrizione,poi);*/ }

    public void segnalaItinerario(String descrizione, ItinerarioGenerico itinerario){ /*this.itinerarioController.creaSegnalazione(descrizione,itinerario);*/ }
}
