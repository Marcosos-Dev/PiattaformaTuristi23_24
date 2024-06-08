package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Controllers.UtentiController;
import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Segnalazione;

public class InterfacciaCuratore {
    private Comune comune;
    private ItinerarioController itinerarioController;
    private PoiController poiController;
    private UtentiController utentiController;

    public InterfacciaCuratore(Comune comune, GestoreUtenti gestore){
        this.comune = comune;
        this.poiController = new PoiController(comune);
        this.itinerarioController = new ItinerarioController(comune);
        this.utentiController = new UtentiController(gestore);
    }

    public void autenticazione(String username, String password){
        //if(!this.utentiController.autenticaUtente(username,password))
        //    throw new IllegalArgumentException("Credenziali errate");
    }

    public void validaElemento(String elemento, PoiGenerico poi, ItinerarioGenerico itinerario, int idContenuto, boolean esito){
        switch(elemento){
            case "Poi":{
                //richiede lista poi
                //richiedi esito
                poiController.validaPoi(poi,esito);
                break;
            }

            case "Itinerario":{
                //richiede lista itinerari
                //richiedi esito
                itinerarioController.validaItinerario(itinerario,esito);
                break;
            }
            case "Contenuto":{
                //poiController
                // -getPoiConContenutiDaValidare
                // -getContenutiDaValidarePoi
                //richiedi esito
                Contenuto contenuto = poiController.getContenuto(poi,idContenuto);
                poiController.validaContenuto(poi,contenuto,esito);
                break;
            }
            default :
                throw new IllegalArgumentException("Tipo non valido");
        }
    }

    public void gestisciSegnalazione(String elemento, Segnalazione segnalazione, boolean esito){
        switch(elemento){
            case "Poi":{
                poiController.gestisciSegnalazione(esito,segnalazione);
                break;
            }

            case "Itinerario":{
                itinerarioController.gestisciSegnalazione(esito,segnalazione);
                break;
            }
            default :
                throw new IllegalArgumentException("Tipo non valido");
        }
    }
}
