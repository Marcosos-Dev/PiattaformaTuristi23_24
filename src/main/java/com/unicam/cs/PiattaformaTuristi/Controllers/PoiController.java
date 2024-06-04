package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.PoiDTO;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Segnalazione;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PoiController {
    //TODO Rimuovere
    private Comune comune;

    @Autowired
    ComuneRepository comuneRepository;

    public PoiController(){}

    public PoiController(Comune comune){
        this.comune = comune;
    }

    public void creaPoiDaValidare(PoiFactory factory, PoiDTO poi){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        if(poi.getContenuto()!=null)
            poiDaInserire.inserisciContenutoDaValidare(poi.getContenuto());
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(poi.getPeriodo());
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.inserisciPoiDaValidare(poiDaInserire);
        this.comuneRepository.save(c);
    }

    public void creaPoiValidato(PoiFactory factory, PoiDTO poi){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        if(poi.getContenuto()!=null)
            poiDaInserire.inserisciContenutoValidato(poi.getContenuto());
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(poi.getPeriodo());
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.inserisciPoiValidato(poiDaInserire);
        this.comuneRepository.save(c);
    }

    public boolean validaEstensioneFile(String nome){
        String extension = nome.substring(nome.lastIndexOf('.') + 1);
        return extension.equals("pdf") || extension.equals("png") || extension.equals("jpg");
    }

    public void validaPoi(PoiGenerico poi, boolean esito){
        if(esito) this.comune.inserisciPoiValidato(poi);
        this.comune.rimuoviPoiDaValidare(poi);
    }

    public List<PoiGenerico> getPoiConContenutiDaValidare(){ return this.comune.getPoiValidati().stream().filter(p -> !p.getContenutiDaValidare().isEmpty()).toList(); }

    public List<Contenuto> getContenutiDaValidarePoi(PoiGenerico poi){ return poi.getContenutiDaValidare(); }

    public Contenuto getContenuto(PoiGenerico poi, int idContenuto){ return getContenutiDaValidarePoi(poi).stream().filter(c -> c.getIdContenuto() == idContenuto).findFirst().orElse(null); }

    public void validaContenuto(PoiGenerico poi, Contenuto c, boolean esito){
        if(esito) poi.inserisciContenutoValidato(c);
        poi.rimuoviContenutoDaValidare(c);
    }

    public void creaSegnalazione(String descrizione, PoiGenerico poi){
        Segnalazione segnalazione = new Segnalazione(descrizione);
        segnalazione.setIdSegnalazione(this.comune.getUltimoIdSegnalazione());
        this.comune.inserisciSegnalazionePoi(segnalazione,poi);
    }

    public PoiGenerico selezionaPoi(int idPoi){ return this.comune.getPoi(idPoi); }

    public Segnalazione selezionaSegnalazionePoi(int idSegnalazione){ return this.comune.getSegnalazionePoi(idSegnalazione); }

    public void gestisciSegnalazione(boolean esito, Segnalazione segnalazione){
        int idPoi = this.comune.getPoiSegnalato(segnalazione);
        if(esito) {
            this.comune.rimuoviPoi(idPoi);
            this.comune.rimuoviSegnalazioniPoi(idPoi);
        } else { this.comune.rimuoviSegnalazione(segnalazione); }
    }

    public void rimuoviPoi(int idPoi){ this.comune.rimuoviPoi(idPoi); }

    public List<PoiGenerico> getPoiValidati(){
        return this.comuneRepository.findById("Camerino").get().getPoiValidati();
    }

    public List<PoiEvento> getPoiEventoValidati(){ return this.comune.getPoiValidati().stream().filter(p -> p.getTipo() == TipoPoi.EVENTO).map(p -> (PoiEvento) p).toList(); }

    public List<PoiGenerico> getPoiDaValidare(){ return this.comune.getPoiDaValidare(); }
}
