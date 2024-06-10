package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.*;

@Entity
public class SegnalazionePoi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "segnalazionePoi_generator")
    private int idSegnalazionePoi;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SEGNALAZIONE")
    private Segnalazione segnalazione;

    @ManyToOne
    @JoinColumn(name = "ID_POI")
    private PoiGenerico poiGenerico;

    public SegnalazionePoi() {

    }

    public SegnalazionePoi(Segnalazione segnalazione, PoiGenerico poi) {
        this.segnalazione = segnalazione;
        this.poiGenerico = poi;
    }

    public int getIdSegnalazionePoi() { return idSegnalazionePoi; }

    public Segnalazione getSegnalazione() { return segnalazione; }

    public PoiGenerico getPoiGenerico() { return poiGenerico; }
}
