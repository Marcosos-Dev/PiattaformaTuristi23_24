package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.TipoItinerario;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ItinerarioGenerico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itinerario_generator")
    private int idItinerario;
    private String titolo;
    private String descrizione;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "itinerario_poi",
            joinColumns = @JoinColumn(name = "id_itinerario"),
            inverseJoinColumns = @JoinColumn(name = "id_poi")
    )
    private List<PoiGenerico>  poi;

    private TipoItinerario tipo;

    public ItinerarioGenerico(){

    }

    public void setIdItinerario(int idItinerario) { this.idItinerario = idItinerario; }

    public void setPoi(List<PoiGenerico> poi) {
        this.poi = poi;
    }

    public void aggiungiPoi(PoiGenerico poi) {
        this.poi.add(poi);
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setTipo(TipoItinerario tipo) {
        this.tipo = tipo;
    }

    public List<PoiGenerico> getPoi() {
        return poi;
    }

    public TipoItinerario getTipo() {
        return tipo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdItinerario() { return idItinerario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItinerarioGenerico)) return false;
        ItinerarioGenerico itinerario = (ItinerarioGenerico) o;
        return itinerario.getIdItinerario() == this.getIdItinerario();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idItinerario);
    }
}
