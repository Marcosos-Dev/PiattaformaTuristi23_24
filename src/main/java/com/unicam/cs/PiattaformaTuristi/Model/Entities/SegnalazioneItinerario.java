package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class SegnalazioneItinerario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Segnalazione segnalazione;

    @ManyToOne
    private ItinerarioGenerico itinerarioGenerico;

    // Constructors, getters, and setters
}
