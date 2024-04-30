package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
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

    public void creaPoiDaValidare(PoiFactory factory, PoiGenerico poi, Contenuto con, Periodo periodo){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        if(poi.getTitolo()==null || poi.getTitolo().isEmpty())
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        if(comune.poiDuplicato(poiDaInserire))
            throw new IllegalArgumentException("Punto già presente");
        if(!comune.internoAlComune(poi.getCoord()))
            throw new IllegalArgumentException("Punto fuori dal comune");
        //Punto con informazioni valide
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        if(con != null)
            poiDaInserire.addContenutiDaValidare(con);
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(periodo);
        comune.inserisciPoiDaValidare(poiDaInserire);
    }

    public void creaPoiValidato(PoiFactory factory, PoiGenerico poi, Contenuto con, Periodo periodo){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        if(poi.getTitolo().isEmpty() || poi.getTitolo()==null)
            throw new IllegalArgumentException("Titolo vuoto o nullo");
        if(comune.poiDuplicato(poiDaInserire))
            throw new IllegalArgumentException("Punto già presente");
        if(!comune.internoAlComune(poi.getCoord()))
            throw new IllegalArgumentException("Punto fuori dal comune");
        //Punto con informazioni valide
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        poiDaInserire.setIdPoi(comune.getLastPoiId());
        if(con != null)
            poiDaInserire.addContenutiValidati(con);
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(periodo);
        comune.inserisciPoiValidato(poiDaInserire);
    }

    public void caricaContenutoDaValidare(Contenuto c, int idPoi){
        String nome = c.getFile().getName();
        String extension = nome.substring(nome.lastIndexOf('.') + 1);
        if(!(extension.equals("pdf")||extension.equals("png")||extension.equals("jpg")))
            throw new IllegalArgumentException("Estensione non valida");
        if(this.getPoi(idPoi)==null)
            throw new IllegalArgumentException("Nessun poi trovato");
        this.getPoi(idPoi).addContenutiDaValidare(c);
    }

    public void caricaContenutoValidato(Contenuto c, int idPoi){
        String nome = c.getFile().getName();
        String extension = nome.substring(nome.lastIndexOf('.') + 1);
        if(!(extension.equals("pdf")||extension.equals("png")||extension.equals("jpg")))
            throw new IllegalArgumentException("Estensione non valida");
        if(this.getPoi(idPoi)==null)
            throw new IllegalArgumentException("Nessun poi trovato");
        this.getPoi(idPoi).addContenutiValidati(c);
    }

    public PoiGenerico getPoi(int idPoi){ return this.comune.getPoiValidati().stream().filter(p -> p.getIdPoi() == idPoi).findFirst().orElse(null); }

    public List<PoiGenerico> getPoiValidati(){
        return this.comune.getPoiValidati();
    }

    public List<PoiGenerico> getPoiDaValidare(){ return this.comune.getPoiDaValidare(); }
}
