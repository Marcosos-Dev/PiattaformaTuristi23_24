package com.unicam.cs.PiattaformaTuristi.Model.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;

import java.util.Arrays;
import java.util.List;

public class PoiController {
    public void creaPoiDaValidare(){
        //TODO controllare che non sia nullo il titolo
    }

    public void creaPoiValidato(){
        //TODO controllare che non sia nullo il titolo
    }

    public List<TipoPoi> ottieniTipi(){
        return Arrays.stream(TipoPoi.values()).toList();
    }

    //Aggiungi contenuto al POI
}
