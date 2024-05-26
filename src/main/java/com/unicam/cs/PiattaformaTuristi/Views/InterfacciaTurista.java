package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ContestController;
import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contest;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public class InterfacciaTurista {

    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private ContestController contestController;
    private Comune comune;

    public InterfacciaTurista(Comune comune){
        this.comune = comune;
        this.poiController = new PoiController(this.comune);
        this.itinerarioController = new ItinerarioController(this.comune);
        this.contestController = new ContestController(this.comune);
    }

    public PoiGenerico visualizzaPoi(int idPoi){
        //il poi sarebbe da selezionare tra tutti i validati -> poiController.getPoiValidati()
        //System.out.println(poiController.getPoi(idPoi));
        return poiController.selezionaPoi(idPoi);
    }

    public ItinerarioGenerico visualizzaItinerario(int idItinerario){
        //l'itinerario sarebbe da selezionare tra tutti i validati -> itinerarioController.getItinerariValidati()
        //System.out.println(itinerarioController.getItinerario(idItinerario));
        return itinerarioController.selezionaItinerario(idItinerario);
    }

    public Contest visualizzaContest(int idContest){
        //il contest sarebbe da selezionare tra tutti quelli chiusi -> contestController.getContestChiusi()
        //System.out.println(contestController.getContest(idContest));
        return contestController.selezionaContest(idContest);
    }

    public void segnalaPoi(String descrizione, PoiGenerico poi){ this.poiController.creaSegnalazione(descrizione,poi); }

    public void segnalaItinerario(String descrizione, ItinerarioGenerico itinerario){ this.itinerarioController.creaSegnalazione(descrizione,itinerario); }
}
