package com.unicam.cs.PiattaformaTuristi.TestingAPI;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaContributore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaGestore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaTuristaAutenticato;

public class TestingGestionRuoli {
    private Comune c;
    private GestoreUtenti gestore;

    public TestingGestionRuoli() {
        c = new Comune("Ancona");
        gestore = new GestoreUtenti();

        System.out.println("Richiesta cambio ruolo");
        testCambioRuolo();
        System.out.println("Accettazione/rifiuto richiesta cambio ruolo");
        testAccettaCambioRuolo();
        System.out.println("Gestione ruoli utenti");
        testGestisciRuoli();
    }

    public void testCambioRuolo(){
        UtenteAutenticato turistaAuth = new UtenteAutenticato(RuoloUtente.TURISTA_AUTENTICATO);
        turistaAuth.setIdUtente(1);
        InterfacciaTuristaAutenticato i = new InterfacciaTuristaAutenticato(c,turistaAuth,gestore);
        i.richiestaCambioRuolo(RuoloUtente.CONTRIBUTORE);

        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE);
        contributor.setIdUtente(2);
        InterfacciaContributore i2 = new InterfacciaContributore(c,contributor,gestore);
        i2.richiestaCambioRuolo(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);

        gestore.aggiungiUtente(turistaAuth);
        gestore.aggiungiUtente(contributor);
    }

    public void testAccettaCambioRuolo(){
        UtenteAutenticato gestore_piattaforma = new UtenteAutenticato(RuoloUtente.GESTORE_PIATTAFORMA);
        InterfacciaGestore i = new InterfacciaGestore(gestore_piattaforma, gestore);
        i.gestisciRichiestaRuolo(gestore.getRichiesteCambioRuolo().get(0),false);
        i.gestisciRichiestaRuolo(gestore.getRichiesteCambioRuolo().get(0),true);
        if(gestore.getUtenti().get(1).getRuolo().equals(RuoloUtente.CONTRIBUTORE_AUTORIZZATO) &&
                gestore.getUtenti().get(0).getRuolo().equals(RuoloUtente.TURISTA_AUTENTICATO    ))
            System.out.println("OK");
    }

    public void testGestisciRuoli(){
        UtenteAutenticato gestore_piattaforma = new UtenteAutenticato(RuoloUtente.GESTORE_PIATTAFORMA);
        InterfacciaGestore i = new InterfacciaGestore(gestore_piattaforma, gestore);
        i.gestisciRuolo(gestore.getUtenti().get(0).getIdUtente(),RuoloUtente.CONTRIBUTORE);
        if(gestore.getUtenti().get(1).getRuolo().equals(RuoloUtente.CONTRIBUTORE_AUTORIZZATO) &&
                gestore.getUtenti().get(0).getRuolo().equals(RuoloUtente.CONTRIBUTORE))
            System.out.println("OK");
    }
}
