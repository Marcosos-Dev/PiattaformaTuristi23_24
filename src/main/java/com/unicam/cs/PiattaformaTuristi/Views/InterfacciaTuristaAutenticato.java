package com.unicam.cs.PiattaformaTuristi.Views;


import com.unicam.cs.PiattaformaTuristi.Controllers.*;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.GestorePreferiti;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

import java.util.List;

public class InterfacciaTuristaAutenticato {
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private ContestController contestController;
    private UtenteController utenteController;
    private Comune comune;
    private UtenteAutenticato utente;
    private PreferitoController preferitoController;


    public InterfacciaTuristaAutenticato(Comune comune, UtenteAutenticato utente, GestoreUtenti gestore, GestorePreferiti elementi){
        //this.comune = comune;
        //this.poiController = new PoiController(this.comune);
        //this.itinerarioController = new ItinerarioController(this.comune);
        //this.contestController = new ContestController(this.comune);
        //this.utentiController = new UtentiController(gestore);
        //this.elementiSalvatiController = new ElementiSalvatiController(elementi);
        //this.utente = utente;
    }

    public void autenticazione(String username, String password){
        //if(!this.utentiController.autenticaUtente(username,password))
            //throw new IllegalArgumentException("Credenziali errate");
    }

    public void richiestaCambioRuolo(RuoloUtente nuovoRuolo){
        //Ottieni possibili ruoli -> utentiController.getPossibiliRuoli(this.utente.getRuolo());
        //this.utentiController.richiediRuolo(this.utente.getIdUtente(),nuovoRuolo);
    }

    public void salvaPoi(int idPoi){
        //this.elementiSalvatiController.salvaPoi(this.utente,this.poiController.selezionaPoi(idPoi));
    }

    public void salvaItinerario(int idItinerario){
        //this.elementiSalvatiController.salvaItinerario(this.utente,this.itinerarioController.selezionaItinerario(idItinerario));
    }

    public void eliminaPoi(PoiPreferito preferito) {
        //this.elementiSalvatiController.rimuoviPoiSalvato(preferito);
    }

    public void eliminaItinerari(ItinerarioPreferito itinerario) {
        //this.elementiSalvatiController.rimuoviItinerarioSalvato(itinerario);
    }

    public List<PoiGenerico> visualizzaPoiPreferito(int idUtente){
        //return this.elementiSalvatiController.selezionaPoiSalvato(idUtente); //Da rivedere, non segue il classico caso d'uso del visualizza
        return null;
    }

    public List<ItinerarioGenerico> visualizzaItinerarioPreferito(int idUtente){
        //return this.elementiSalvatiController.selezionaItinerarioSalvato(idUtente); //Da rivedere, non segue il classico caso d'uso del visualizza
        return null;
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
}

