package com.unicam.cs.PiattaformaTuristi.Model.Entities;

public class ItinerarioPreferito {
    private int idElemento;
    private ItinerarioGenerico itinerario;
    private UtenteAutenticato utente;

    public ItinerarioPreferito(ItinerarioGenerico itinerario, UtenteAutenticato utente){
        this.itinerario = itinerario;
        this.utente = utente;
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
