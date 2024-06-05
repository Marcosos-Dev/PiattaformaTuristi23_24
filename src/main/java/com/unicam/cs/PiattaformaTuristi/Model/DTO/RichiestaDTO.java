package com.unicam.cs.PiattaformaTuristi.Model.DTO;

import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;

public class RichiestaDTO {
    private int idUtente;
    private RuoloUtente ruoloRichiesto;

    public RichiestaDTO() {}

    public RichiestaDTO(int idUtente, RuoloUtente ruoloRichiesto) {
        this.idUtente = idUtente;
        this.ruoloRichiesto = ruoloRichiesto;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public RuoloUtente getRuoloRichiesto() {
        return ruoloRichiesto;
    }
}
