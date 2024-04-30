package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import java.util.List;

public class Contest {
    private String titolo;
    private String descrizione;
    private boolean privato; //True se privato
    private List<UtenteAutenticato> invitati;
    private List<Contenuto> contenutiCaricati;
    private Contenuto contenutoVincitore;

}
