package com.unicam.cs.PiattaformaTuristi.TestingAPI;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Model.GestoreUtenti;
import com.unicam.cs.PiattaformaTuristi.Model.RuoloUtente;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaContributore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaGestore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaTurista;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaTuristaAutenticato;

public class TestingGestionRuoli {
    private Comune c;
    private GestoreUtenti gestore;
    private UtenteAutenticato turistaAuth = new UtenteAutenticato(RuoloUtente.TURISTA_AUTENTICATO);
    private UtenteAutenticato turistaAuth2 = new UtenteAutenticato(RuoloUtente.TURISTA_AUTENTICATO);

    public TestingGestionRuoli(Comune c) {
        this.c = c;
        gestore = new GestoreUtenti();

        System.out.println("Registrazione");
        testRegistrazione();
        System.out.println("Autenticazione");
        testAutenticazione();
        System.out.println("Richiesta cambio ruolo");
        testCambioRuolo();
        System.out.println("Accettazione/rifiuto richiesta cambio ruolo");
        testAccettaCambioRuolo();
        System.out.println("Gestione ruoli utenti");
        testGestisciRuoli();
    }

    public void testRegistrazione(){
        InterfacciaTurista i = new InterfacciaTurista(c,gestore);
        i.registraUtente("TestWebUtente1","EsameInformatica123!");
        i.registraUtente("TestWebUtente2","EsameInformatica321!");
        if(this.gestore.getUtenteTramiteUsername("TestWebUtente1")!=null &&
                this.gestore.getUtenteTramiteUsername("TestWebUtente2")!=null)
            System.out.println("OK");
    }

    public void testAutenticazione(){
        InterfacciaTuristaAutenticato i = new InterfacciaTuristaAutenticato(c,turistaAuth,gestore);
        try {
            i.autenticazione("TestWebUtente1","EsameInformatica123!");
            System.out.println("OK");
            i.autenticazione("TestWebUtente1","EsameInformatica321!");
        } catch(Exception e) {
            System.out.println("Autenticazione fallita per credenziali errate");
        }
        InterfacciaTuristaAutenticato i2 = new InterfacciaTuristaAutenticato(c,turistaAuth2,gestore);
        try {
            i2.autenticazione("TestWebUtente2","EsameInformatica321!");
            System.out.println("OK");
            i2.autenticazione("TestWebUtente2","EsameInformatica123!");
        } catch(Exception e) {
            System.out.println("Autenticazione fallita per credenziali errate");
        }
    }

    public void testCambioRuolo(){
        InterfacciaTuristaAutenticato i = new InterfacciaTuristaAutenticato(c,this.gestore.getUtenteTramiteUsername("TestWebUtente1"),gestore);
        i.richiestaCambioRuolo(RuoloUtente.CONTRIBUTORE);

        InterfacciaTuristaAutenticato i2 = new InterfacciaTuristaAutenticato(c,this.gestore.getUtenteTramiteUsername("TestWebUtente2"),gestore);
        i2.richiestaCambioRuolo(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);

        if(this.gestore.getRichiesteCambioRuolo().size()==2)
            System.out.println("OK");
    }

    public void testAccettaCambioRuolo(){
        UtenteAutenticato gestore_piattaforma = new UtenteAutenticato(RuoloUtente.GESTORE_PIATTAFORMA);
        InterfacciaGestore i = new InterfacciaGestore(gestore_piattaforma, gestore);
        i.gestisciRichiestaRuolo(gestore.getRichiesteCambioRuolo().get(0),false);
        i.gestisciRichiestaRuolo(gestore.getRichiesteCambioRuolo().get(0),true);
        if(this.gestore.getUtenteTramiteUsername("TestWebUtente1").getRuolo()==RuoloUtente.TURISTA_AUTENTICATO &&
                this.gestore.getUtenteTramiteUsername("TestWebUtente2").getRuolo()==RuoloUtente.CONTRIBUTORE_AUTORIZZATO)
            System.out.println("OK");
    }

    public void testGestisciRuoli(){
        UtenteAutenticato gestore_piattaforma = new UtenteAutenticato(RuoloUtente.GESTORE_PIATTAFORMA);
        InterfacciaGestore i = new InterfacciaGestore(gestore_piattaforma, gestore);
        i.gestisciRuolo(gestore.getUtenti().get(0).getIdUtente(),RuoloUtente.CONTRIBUTORE);
        if(this.gestore.getUtenteTramiteUsername("TestWebUtente1").getRuolo()==RuoloUtente.CONTRIBUTORE &&
                this.gestore.getUtenteTramiteUsername("TestWebUtente2").getRuolo()==RuoloUtente.CONTRIBUTORE_AUTORIZZATO)
            System.out.println("OK");
    }
}
