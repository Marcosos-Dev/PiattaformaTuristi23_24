package com.unicam.cs.PiattaformaTuristi.Model.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;

import java.util.List;

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

    public PoiGenerico ottieniPoi(PoiGenerico poi){
        return this.comune.getPoiValidati().stream().filter(p -> p.equals(poi)).findFirst().orElse(null);
    }

    public List<PoiGenerico> ottieniListaPoiValidati(){
        return this.comune.getPoiValidati();
    }

    public List<PoiGenerico> ottieniListaPoiDaValidare(){
        return this.comune.getPoiDaValidare();
    }

    //Aggiungi contenuto al POI
}
