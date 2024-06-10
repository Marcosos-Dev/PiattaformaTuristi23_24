package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.*;

@Entity
public class SegnalazioneItinerario{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "segnalazioneItinerario_generator")
    private int idSegnalazioneItinerario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SEGNALAZIONE")
    private Segnalazione segnalazione;

    @ManyToOne
    @JoinColumn(name = "ID_ITINERARIO")
    private ItinerarioGenerico itinerarioGenerico;

    public SegnalazioneItinerario() {

    }

    public SegnalazioneItinerario(Segnalazione segnalazione, ItinerarioGenerico itinerario) {
        this.segnalazione = segnalazione;
        this.itinerarioGenerico = itinerario;
    }

    public int getIdSegnalazioneItinerario() { return idSegnalazioneItinerario; }

    public Segnalazione getSegnalazione() { return segnalazione; }

    public ItinerarioGenerico getItinerarioGenerico() { return itinerarioGenerico; }
}
