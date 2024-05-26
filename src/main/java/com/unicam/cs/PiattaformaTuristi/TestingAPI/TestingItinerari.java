package com.unicam.cs.PiattaformaTuristi.TestingAPI;

import com.unicam.cs.PiattaformaTuristi.Model.*;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaContributore;
import com.unicam.cs.PiattaformaTuristi.Views.InterfacciaCuratore;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestingItinerari {
    private Comune c;
    private GestoreUtenti gestore;


    public TestingItinerari() {
        setup();

        System.out.println("Test con itinerari da validare");
        testInserimentoItinerariNonValidati();
        System.out.println("\nTest con itinerari validati");
        testInserimentoItinerariValidati();
        System.out.println("\nTest di validazione degli itinerari");
        testValidaItinerari();
        System.out.println("\nTest di visualizzazione degli itinerari");
        testVisualizzaItinerario();
    }

    private void setup(){
        c = new Comune("Ancona");
        gestore = new GestoreUtenti();
        UtenteAutenticato tempContributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,tempContributor,gestore);
        Poi primo = new Poi("primo","",new Coordinate(13.21,43.48));
        Poi secondo = new Poi("secondo","",new Coordinate(13.21,43.48));
        PoiEvento terzo = new PoiEvento("terzo","",new Coordinate(13.21,43.48));
        i.aggiungiPoi(primo,null,null);
        i.aggiungiPoi(secondo,null,null);
        i.aggiungiPoi(terzo,null,new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));
    }

    public void testVisualizzaItinerario(){
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);
        System.out.println(i.visualizzaItinerario(1));
        System.out.println(i.visualizzaItinerario(5));
        System.out.println( i.visualizzaItinerario(6));
        System.out.println( i.visualizzaItinerario(7));
    }

    public void testInserimentoItinerariNonValidati() {
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);
        //List<PoiGenerico> pois = i.getPoiValidati();
        List<PoiGenerico> pois = new ArrayList<>();
        pois.add(i.visualizzaPoi(1));
        pois.add(i.visualizzaPoi(2));
        pois.add(i.visualizzaPoi(3));
        Itinerario itinerario1 = new Itinerario("Primo","primo it");
        ItinerarioEvento itinerario2 = new ItinerarioEvento("Secondo","secondo it");
        Percorso itinerario3 = new Percorso("Terzo","terzo it");
        PercorsoEvento itinerario4 = new PercorsoEvento("Quarto","quarto it");

        i.aggiungiItinerario(itinerario1,pois,null);
        i.aggiungiItinerario(itinerario2,pois,new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));
        i.aggiungiItinerario(itinerario3,pois,null);
        i.aggiungiItinerario(itinerario4,pois,new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));

        System.out.println("-----------Lista itinerari da validare-----------");
        c.stampaItinerariDaValidare();
        System.out.println("-----------Lista itinerari validati-----------");
        c.stampaItinerariValidati();
    }

    public void testInserimentoItinerariValidati() {
        UtenteAutenticato contributor = new UtenteAutenticato(RuoloUtente.CONTRIBUTORE_AUTORIZZATO);
        InterfacciaContributore i = new InterfacciaContributore(c,contributor,gestore);
        //List<PoiGenerico> pois = i.getPoiValidati();
        List<PoiGenerico> pois = new ArrayList<>();
        pois.add(i.visualizzaPoi(1));
        pois.add(i.visualizzaPoi(2));
        pois.add(i.visualizzaPoi(3));
        Itinerario itinerario1 = new Itinerario("Quinto","quinto it");
        ItinerarioEvento itinerario2 = new ItinerarioEvento("Sesto","sesto it");
        Percorso itinerario3 = new Percorso("Settimo","settimo it");
        PercorsoEvento itinerario4 = new PercorsoEvento("Ottavo","ottavo it");

        i.aggiungiItinerario(itinerario1,pois,null);
        i.aggiungiItinerario(itinerario2,pois,new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));
        i.aggiungiItinerario(itinerario3,pois,null);
        i.aggiungiItinerario(itinerario4,pois,new Periodo(
                LocalDateTime.of(2025,
                        Month.JULY, 29, 19, 30, 40),
                LocalDateTime.of(2030,
                        Month.JULY, 29, 19, 30, 40)
        ));

        System.out.println("-----------Lista itinerari da validare-----------");
        c.stampaItinerariDaValidare();
        System.out.println("-----------Lista itinerari validati-----------");
        c.stampaItinerariValidati();
    }


    public void testValidaItinerari() {
        UtenteAutenticato curatore = new UtenteAutenticato(RuoloUtente.CURATORE);
        InterfacciaCuratore iC = new InterfacciaCuratore(c);

        iC.validaElemento("Itinerario",null,c.getItinerariDaValidare().get(0),0,true);
        System.out.println("-----------Lista itinerari da validare-----------");
        c.stampaItinerariDaValidare();
        System.out.println("-----------Lista itinerari validati-----------");
        c.stampaItinerariValidati();
    }
}
