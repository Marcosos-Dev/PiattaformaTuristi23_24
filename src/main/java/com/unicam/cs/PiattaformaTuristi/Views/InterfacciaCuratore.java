package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;

public class InterfacciaCuratore {
    private ItinerarioController itinerarioController;
    private PoiController poiController;

    public InterfacciaCuratore(){

    }

    public void validaElemento(String elemento, PoiGenerico poi, ItinerarioGenerico itinerario, int idContenuto){
        switch(elemento){
            case "Poi":{
                //richiede lista poi
                //richiedi esito
                boolean esito = false;
                poiController.validaPoi(poi,esito);
                break;
            }

            case "Itinerario":{
                //richiede lista itinerari
                //richiedi esito
                boolean esito = false;
                itinerarioController.validaItinerario(itinerario,esito);
                break;
            }
            case "Contenuto":{
                //poiController
                // -getPoiConContenutiDaValidare
                // -getContenutiDaValidarePoi
                //richiedi esito
                Contenuto contenuto = poiController.getContenuto(poi,idContenuto);
                boolean esito = false;
                poiController.validaContenuto(poi,contenuto,esito);
                break;
            }
            default :
                throw new IllegalArgumentException("Tipo non valido");
        }
    }
}
