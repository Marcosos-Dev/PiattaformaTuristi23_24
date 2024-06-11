package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import jakarta.persistence.*;

@Entity
public class Richiesta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "richiesta_generator")
    private int idRichiesta;
    @Column(name = "idUtente", nullable = false)
    private int idUtente;
    private RuoloUtente ruoloRichiesto;

    public Richiesta(){}

    public Richiesta(int idUtente, RuoloUtente ruoloRichiesto){
        this.idUtente = idUtente;
        this.ruoloRichiesto = ruoloRichiesto;
    }

    public void setIdRichiesta(int idRichiesta) { this.idRichiesta = idRichiesta; }

    public int getIdUtente() { return idUtente; }

    public int getIdRichiesta() { return idRichiesta; }

    public RuoloUtente getRuoloRichiesto() { return ruoloRichiesto; }
}
