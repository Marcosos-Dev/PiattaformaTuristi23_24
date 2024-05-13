package com.unicam.cs.PiattaformaTuristi.Model;

import java.util.ArrayList;
import java.util.List;

public enum RuoloUtente {
    TURISTA, TURISTA_AUTENTICATO, CONTRIBUTORE, CONTRIBUTORE_AUTORIZZATO, ANIMATORE, CURATORE, GESTORE_PIATTAFORMA;

    public static List<RuoloUtente> getPossibiliRuoliDefault(){
        List<RuoloUtente> ruoli = new ArrayList<>();
        ruoli.add(TURISTA_AUTENTICATO);
        ruoli.add(CONTRIBUTORE);
        ruoli.add(CONTRIBUTORE_AUTORIZZATO);
        ruoli.add(ANIMATORE);
        return ruoli;
    }
}
