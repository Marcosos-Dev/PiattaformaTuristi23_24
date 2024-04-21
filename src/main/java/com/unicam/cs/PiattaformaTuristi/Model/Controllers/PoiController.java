package com.unicam.cs.PiattaformaTuristi.Model.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;

public class PoiController {

    private Comune comune;

    public PoiController(Comune comune){
        this.comune = comune;
    }

    public void creaPoiDaValidare(PoiFactory factory, PoiGenerico poi, Periodo periodo){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        if(poi.getTitolo()==null || poi.getTitolo().isEmpty())
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(periodo);
        if(comune.poiDuplicato(poiDaInserire))
            throw new IllegalArgumentException("Punto già presente");
        comune.inserisciPoiDaValidare(poiDaInserire);
    }

    public void creaPoiValidato(PoiFactory factory, PoiGenerico poi, Periodo periodo){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        if(poi.getTitolo().isEmpty() || poi.getTitolo()==null)
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(periodo);
        if(comune.poiDuplicato(poiDaInserire))
            throw new IllegalArgumentException("Punto già presente");
        comune.inserisciPoiValidato(poiDaInserire);
    }

    //Aggiungi contenuto al POI
}
