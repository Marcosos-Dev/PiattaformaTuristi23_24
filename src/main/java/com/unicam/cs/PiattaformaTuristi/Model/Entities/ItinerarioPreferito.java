package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.*;

@Entity
public class ItinerarioPreferito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itinerariopreferito_generator")
    private int idElemento;
    @ManyToOne
    @JoinColumn(name = "ID_ITINERARIO")
    private ItinerarioGenerico itinerario;
    @ManyToOne
    @JoinColumn(name = "ID_UTENTE")
    private UtenteAutenticato utente;

    public ItinerarioPreferito(ItinerarioGenerico itinerario, UtenteAutenticato utente){
        this.itinerario = itinerario;
        this.utente = utente;
    }

    public ItinerarioPreferito() {

    }

    public void setIdElemento(int idElemento) { this.idElemento = idElemento; }

    public void setUtente(UtenteAutenticato utente) { this.utente = utente; }

    public void setItinerario(ItinerarioGenerico itinerario) { this.itinerario = itinerario; }

    public int getIdElemento() { return idElemento; }

    public UtenteAutenticato getUtente() { return utente; }

    public ItinerarioGenerico getItinerario() { return itinerario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItinerarioPreferito)) return false;
        ItinerarioPreferito itinerario = (ItinerarioPreferito) o;
        return itinerario.getIdElemento() == this.getIdElemento();
    }
}
