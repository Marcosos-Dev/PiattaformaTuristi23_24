package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.TipoPoi;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PoiGenerico  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poi_generator")
    private int idPoi;
    private String titolo;
    private String descrizione;
    @Embedded
    private Coordinate coord;
    private TipoPoi tipo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenuto> contenutiDaValidare;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenuto> contenutiValidati;

    public PoiGenerico(Coordinate c){
        if(c == null) throw new NullPointerException("Coordinate null");
        this.coord = c;
        this.contenutiValidati = new ArrayList<>();
        this.contenutiDaValidare = new ArrayList<>();
    }

    public PoiGenerico() {

    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setTipo(TipoPoi tipo) {
        this.tipo = tipo;
    }

    public void setIdPoi(int idPoi) { this.idPoi = idPoi; }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public void aggiungiContenutoValidato(Contenuto contenuto) {
        this.contenutiValidati.add(contenuto);
    }

    public void aggiungiContenutoDaValidare(Contenuto contenuto) { this.contenutiDaValidare.add(contenuto); }

    public void rimuoviContenutoValidato(Contenuto contenuto) {
        this.contenutiDaValidare.remove(contenuto);
    }

    public void rimuoviContenutoDaValidare(Contenuto contenuto) { this.contenutiDaValidare.remove(contenuto); }

    public int getIdPoi() { return idPoi; }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public TipoPoi getTipo() {
        return tipo;
    }

    public List<Contenuto> getContenutiDaValidare() { return contenutiDaValidare; }

    public List<Contenuto> getContenutiValidati() { return contenutiValidati; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoiGenerico)) return false;
        PoiGenerico poi = (PoiGenerico) o;
        return poi.getIdPoi() == this.getIdPoi();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idPoi);
    }

}
