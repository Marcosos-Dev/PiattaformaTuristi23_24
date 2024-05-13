package com.unicam.cs.PiattaformaTuristi.Model;

public class Richiesta {
    private int idRichiesta;
    private int idUtente;
    private RuoloUtente ruoloRichiesto;

    public Richiesta(int idUtente, RuoloUtente ruoloRichiesto){
        this.idUtente = idUtente;
        this.ruoloRichiesto = ruoloRichiesto;
    }

    public void setIdRichiesta(int idRichiesta) { this.idRichiesta = idRichiesta; }

    public int getIdUtente() { return idUtente; }

    public int getIdRichiesta() { return idRichiesta; }

    public RuoloUtente getRuoloRichiesto() { return ruoloRichiesto; }
}
