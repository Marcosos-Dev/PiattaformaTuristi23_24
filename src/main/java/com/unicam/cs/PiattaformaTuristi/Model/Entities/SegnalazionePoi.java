package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class SegnalazionePoi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSegnalazionePoi;

    @ManyToOne
    private Segnalazione segnalazione;

    @ManyToOne
    private PoiGenerico poiGenerico;

    // Constructors, getters, and setters
}
