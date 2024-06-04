package com.unicam.cs.PiattaformaTuristi.TestingAPI;
/*
import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Contenuto;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.Poi;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiEvento;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaContributore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaCuratore;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

public class TestingPOI {
    private Comune c;
    private GestoreUtenti gestore;

    public TestingPOI(Comune c) throws URISyntaxException {
        this.c = c;

        gestore = new GestoreUtenti();

        System.out.println("Test con poi da validare");
        testInserimentoPoiDaValidare();
        System.out.println("\nTest con poi validati");
        testInserimentoPoiValidati();
        System.out.println("\nTest di validazione dei poi");
        testValidaPoi();
        System.out.println("\nTest di caricamento di un contenuto da validare");
        testCaricaContenutoDaValidare();
        System.out.println("\nTest di caricamento di un contenuto da validare");
        testCaricaContenutoValidato();
        System.out.println("\nTest di validazione del contenuto del poi");
        testValidaContenuto();
        System.out.println("\nTest di visualizzazione dei poi");
        testVisualizzaPoi();
    }

    public void testVisualizzaPoi(){
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);
        System.out.println(i.visualizzaPoi(2));
        System.out.println(i.visualizzaPoi(4));
        System.out.println( i.visualizzaPoi(5));
        System.out.println(i.visualizzaPoi(6));
    }


    public void testInserimentoPoiDaValidare() throws URISyntaxException {
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);

        Poi primo = new Poi("primo","",new Coordinate(13.21,43.48));
        Poi secondo = new Poi("secondo","",new Coordinate(13.21,43.48));
        PoiEvento terzo = new PoiEvento("terzo","",new Coordinate(13.21,43.48));

        File file = Paths.get(Objects.requireNonNull(getClass().getResource("/correct.pdf")).toURI()).toFile();
        i.aggiungiPoi(primo,new Contenuto(file,"test"),null);
        i.aggiungiPoi(secondo,new Contenuto(file,"test"),null);
        i.aggiungiPoi(terzo,new Contenuto(file,"test"),new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));

        System.out.println("-----------Lista poi da validare-----------");
        c.stampaPOIDaValidare();
        System.out.println("-----------Lista poi validati-----------");
        c.stampaPOIValidati();
    }

    public void testInserimentoPoiValidati() throws URISyntaxException {
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);

        Poi quarto = new Poi("quarto","",new Coordinate(13.21,43.48));
        Poi quinto = new Poi("quinto","",new Coordinate(13.21,43.48));
        PoiEvento sesto = new PoiEvento("sesto","",new Coordinate(13.21,43.48));

        File file = Paths.get(Objects.requireNonNull(getClass().getResource("/correct.pdf")).toURI()).toFile();
        i.aggiungiPoi(quarto,new Contenuto(file,"test"),null);
        i.aggiungiPoi(quinto,new Contenuto(file,"test"),null);
        i.aggiungiPoi(sesto,new Contenuto(file,"test"),new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));

        System.out.println("-----------Lista poi da validare-----------");
        c.stampaPOIDaValidare();
        System.out.println("-----------Lista poi validati-----------");
        c.stampaPOIValidati();
    }

    public void testCaricaContenutoDaValidare() throws URISyntaxException {
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);

        File file = Paths.get(Objects.requireNonNull(getClass().getResource("/correct.pdf")).toURI()).toFile();
        i.caricaContenuto(new Contenuto(file,"contenuto da validare 1"),1);

        System.out.println("-----------Lista contenuti caricati validati-----------");
        for(Contenuto cont : c.getPoiValidati().get(3).getContenutiValidati())
            System.out.println(cont);
        System.out.println("-----------Lista contenuti caricati da validare-----------");
        for(Contenuto cont : c.getPoiValidati().get(3).getContenutiDaValidare())
            System.out.println(cont);
    }

    public void testCaricaContenutoValidato() throws URISyntaxException {
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);

        File file = Paths.get(Objects.requireNonNull(getClass().getResource("/correct.pdf")).toURI()).toFile();
        i.caricaContenuto(new Contenuto(file,"contenuto validato 1"),1);

        System.out.println("-----------Lista contenuti caricati validati-----------");
        for(Contenuto cont : c.getPoiValidati().get(3).getContenutiValidati())
            System.out.println(cont);
        System.out.println("-----------Lista contenuti caricati da validare-----------");
        for(Contenuto cont : c.getPoiValidati().get(3).getContenutiDaValidare())
            System.out.println(cont);
    }

    public void testValidaPoi(){
        UtenteAutenticato curatore = new UtenteAutenticato(RuoloUtente.CURATORE);
        InterfacciaCuratore iC = new InterfacciaCuratore(c,gestore);

        iC.validaElemento("Poi",c.getPoiDaValidare().get(0),null,0,true);
        System.out.println("-----------Lista poi da validare-----------");
        c.stampaPOIDaValidare();
        System.out.println("-----------Lista poi validati-----------");
        c.stampaPOIValidati();
    }

    public void testValidaContenuto(){
        UtenteAutenticato curatore = new UtenteAutenticato(RuoloUtente.CURATORE);
        InterfacciaCuratore iC = new InterfacciaCuratore(c,gestore);

        iC.validaElemento("Contenuto",c.getPoiValidati().get(3),null,1,true);
        System.out.println("-----------Lista contenuti caricati validati-----------");
        for(Contenuto cont : c.getPoiValidati().get(3).getContenutiValidati())
            System.out.println(cont);
        System.out.println("-----------Lista contenuti caricati da validare-----------");
        for(Contenuto cont : c.getPoiValidati().get(3).getContenutiDaValidare())
            System.out.println(cont);
    }
}
*/