package com.unicam.cs.PiattaformaTuristi.Model.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;

import java.util.Arrays;
import java.util.List;

public class ItinerarioController {
    public void creaItinerarioDaValidare(){
        //TODO controllare che non sia nullo il titolo
    }

    public void creaItinerarioValidato(){
        //TODO controllare che non sia nullo il titolo
    }

    public List<TipoItinerario> ottieniTipi(){
        return Arrays.stream(TipoItinerario.values()).toList();
    }
}
