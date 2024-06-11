package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.DTO.PoiDTO;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;
import com.unicam.cs.PiattaformaTuristi.Repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PoiController {
    @Autowired
    ComuneRepository comuneRepository;

    public PoiController(){}

    public void creaPoiDaValidare(PoiFactory factory, PoiDTO poi){
        PoiGenerico poiDaInserire = factory.creaPoi(poi.getCoord());
        poiDaInserire.setTitolo(poi.getTitolo());
        poiDaInserire.setDescrizione(poi.getDescrizione());
        if(poi.getContenuto()!=null)
            poiDaInserire.aggiungiContenutoValidato(poi.getContenuto());
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
            poiDaInserire.aggiungiContenutoValidato(poi.getContenuto());
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
        Comune c = this.comuneRepository.findById("Camerino").get();
        if(esito) c.inserisciPoiValidato(poi);
        c.rimuoviPoiDaValidare(poi);
        this.comuneRepository.save(c);
    }

    public List<PoiGenerico> getPoiConContenutiDaValidare(){ return this.comuneRepository.findById("Camerino").get().getPoiValidati().stream().filter(p -> !p.getContenutiDaValidare().isEmpty()).toList(); }

    public List<Contenuto> getContenutiDaValidarePoi(PoiGenerico poi){ return poi.getContenutiDaValidare(); }

    public Contenuto getContenuto(PoiGenerico poi, int idContenuto){ return getContenutiDaValidarePoi(poi).stream().filter(c -> c.getIdContenuto() == idContenuto).findFirst().orElse(null); }

    public void validaContenuto(PoiGenerico poi, Contenuto contenuto, boolean esito){
        Comune c = this.comuneRepository.findById("Camerino").get();
        if(esito) poi.aggiungiContenutoValidato(contenuto);
        poi.rimuoviContenutoDaValidare(contenuto);
        this.comuneRepository.save(c);
    }

    public void creaSegnalazione(String descrizione, PoiGenerico poi){
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.inserisciSegnalazionePoi(new Segnalazione(descrizione),poi);
        this.comuneRepository.save(c);
    }

    public PoiGenerico getPoi(int idPoi){ return this.comuneRepository.findById("Camerino").get().getPoi(idPoi); }

    public SegnalazionePoi getSegnalazionePoi(int idSegnalazione){ return this.comuneRepository.findById("Camerino").get().getSegnalazionePoi(idSegnalazione); }

    public void gestisciSegnalazione(SegnalazionePoi segnalazione, boolean esito){
        Comune comune = this.comuneRepository.findById("Camerino").get();
        if(esito) {
            comune.rimuoviPoi(segnalazione.getPoiGenerico().getIdPoi());
            comune.rimuoviSegnalazioniPoi(segnalazione.getPoiGenerico());
        } else {
            comune.getSegnalazioniPoi().remove(segnalazione);
        }
        this.comuneRepository.save(comune);
    }

    public void rimuoviPoi(int idPoi){ this.comuneRepository.findById("Camerino").get().rimuoviPoi(idPoi); }

    public List<PoiGenerico> getPoiValidati(){ return this.comuneRepository.findById("Camerino").get().getPoiValidati(); }

    public List<PoiEvento> getPoiEventoValidati(){ return this.comuneRepository.findById("Camerino").get().getPoiValidati().stream().filter(p -> p.getTipo() == TipoPoi.EVENTO).map(p -> (PoiEvento) p).toList(); }

    public List<PoiGenerico> getPoiDaValidare(){ return this.comuneRepository.findById("Camerino").get().getPoiDaValidare(); }

    public void caricaContenutoDaValidare(Contenuto contenuto, int idPoi){
        validaEstensioneFile(contenuto.getFile().getName());
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.getPoi(idPoi).aggiungiContenutoDaValidare(contenuto);
        this.comuneRepository.save(c);
    }

    public void caricaContenutoValidato(Contenuto contenuto, int idPoi){
        validaEstensioneFile(contenuto.getFile().getName());
        Comune c = this.comuneRepository.findById("Camerino").get();
        c.getPoi(idPoi).aggiungiContenutoValidato(contenuto);
        this.comuneRepository.save(c);
    }
}
