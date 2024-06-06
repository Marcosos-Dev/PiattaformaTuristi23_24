package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.*;

@Entity
public class PoiPreferito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poipreferito_generator")
    private int idElemento;
    @ManyToOne
    @JoinColumn(name = "ID_POI")
    private PoiGenerico poi;
    @ManyToOne
    @JoinColumn(name = "ID_UTENTE")
    private UtenteAutenticato utente;

    public PoiPreferito(PoiGenerico poi, UtenteAutenticato utente){
        this.poi = poi;
        this.utente = utente;
    }
    public PoiPreferito(){}

    public void setIdElemento(int idElemento) { this.idElemento = idElemento; }

    public void setUtente(UtenteAutenticato utente) { this.utente = utente; }

    public void setPoi(PoiGenerico poi) { this.poi = poi; }

    public int getIdElemento() { return idElemento; }

    public UtenteAutenticato getUtente() { return utente; }

    public PoiGenerico getPoi() { return poi; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoiPreferito)) return false;
        PoiPreferito poi = (PoiPreferito) o;
        return poi.getIdElemento() == this.getIdElemento();
    }
}
