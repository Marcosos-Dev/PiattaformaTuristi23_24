package com.unicam.cs.PiattaformaTuristi.Views;

import com.unicam.cs.PiattaformaTuristi.Controllers.ItinerarioController;
import com.unicam.cs.PiattaformaTuristi.Controllers.PoiController;

public class InterfacciaCuratore {
    private ItinerarioController itinerarioController;
    private PoiController poiController;

    //TODO realizzare
    public void validaElemento(String elemento){
        switch(elemento){
            case "Poi":
                break;
            case "Itinerario":
                break;
            case "Contenuto":
                break;
            default :
                throw new IllegalArgumentException("Tipo non valido");
        }
    }
}
