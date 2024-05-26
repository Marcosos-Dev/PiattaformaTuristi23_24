package com.unicam.cs.PiattaformaTuristi.Controllers;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import com.unicam.cs.PiattaformaTuristi.Model.Factories.PoiFactory;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.Segnalazione;

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
        poiDaInserire.setIdPoi(comune.getUltimoIdPoi());
        if(con != null){
            validaEstensioneFile(con);
            poiDaInserire.inserisciContenutoValidato(con);
        }
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
        poiDaInserire.setIdPoi(comune.getUltimoIdPoi());
        if(con != null){
            validaEstensioneFile(con);
            poiDaInserire.inserisciContenutoValidato(con);
        }
        if(poiDaInserire instanceof PoiEvento poiE)
            poiE.setPeriodo(periodo);
        comune.inserisciPoiValidato(poiDaInserire);
    }

    public void caricaContenutoDaValidare(Contenuto contenuto, int idPoi){
        validaEstensioneFile(contenuto);
        if(this.selezionaPoi(idPoi)==null)
            throw new IllegalArgumentException("Nessun poi trovato");
        contenuto.setIdContenuto(comune.getLastIdContenuto());
        this.selezionaPoi(idPoi).inserisciContenutoDaValidare(contenuto);
    }

    public void caricaContenutoValidato(Contenuto contenuto, int idPoi){
        validaEstensioneFile(contenuto);
        if(this.selezionaPoi(idPoi)==null)
            throw new IllegalArgumentException("Nessun poi trovato");
        contenuto.setIdContenuto(comune.getLastIdContenuto());
        this.selezionaPoi(idPoi).inserisciContenutoValidato(contenuto);
    }

    private void validaEstensioneFile(Contenuto c){
        String nome = c.getFile().getName();
        String extension = nome.substring(nome.lastIndexOf('.') + 1);
        if(!(extension.equals("pdf")||extension.equals("png")||extension.equals("jpg")))
            throw new IllegalArgumentException("Estensione non valida");
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

    public List<PoiGenerico> getPoiValidati(){ return this.comune.getPoiValidati(); }

    public List<PoiGenerico> getPoiDaValidare(){ return this.comune.getPoiDaValidare(); }
}
