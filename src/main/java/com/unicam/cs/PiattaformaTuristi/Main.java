package com.unicam.cs.PiattaformaTuristi;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Coordinate;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import com.unicam.cs.PiattaformaTuristi.Model.Periodo;
import com.unicam.cs.PiattaformaTuristi.Model.ProgramExecutor;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Poi p1 = new Poi(new Coordinate(10,10));
        Poi p2 = new Poi(new Coordinate(50,30));
        PoiEvento p3 = new PoiEvento(new Coordinate(-25,-25));
        PoiEvento p4 = new PoiEvento(new Coordinate(140,-82));
        Itinerario i1 = new Itinerario();
        ItinerarioEvento i2 = new ItinerarioEvento();
        Percorso i3 = new Percorso();
        PercorsoEvento i4 = new PercorsoEvento();


        ProgramExecutor pExec = new ProgramExecutor(new Comune());


        p1.setTitolo("primo");
        p2.setTitolo("secondo");
        p3.setTitolo("terzo");
        p4.setTitolo("quarto");

        i1.setTitolo("Itinerario 1");
        i2.setTitolo("Itinerario 2");
        i3.setTitolo("Itinerario 3");
        i4.setTitolo("Itinerario 4");

        Periodo periodo1 = new Periodo(
                LocalDateTime.of(
                        2025,
                        Month.JANUARY,
                        23,
                        10,
                        42,
                        0),
                LocalDateTime.of(2026, Month.APRIL,10,20,30,0));
        Periodo periodo2 = new Periodo(
                LocalDateTime.of(
                        2025,
                        Month.JANUARY,
                        23,
                        10,
                        42,
                        0),
                LocalDateTime.of(
                        2027,
                        Month.JANUARY,
                        23,
                        10,
                        42,
                        0));

        //p3.setPeriodo(periodo1);
        //p4.setPeriodo(periodo2);



        //pExec.aggiungiPoiDaValidare(p1,null);
        pExec.aggiungiPoiValidato(p1,null);
        pExec.aggiungiPoiValidato(p2,null);
        //pExec.aggiungiPoiDaValidare(p2,null); ERRORE POI Duplicato presente tra i validati
        //pExec.aggiungiPoiDaValidare(p3,periodo1);
        pExec.aggiungiPoiValidato(p3,periodo1);
        pExec.aggiungiPoiValidato(p4,periodo2);

        List<PoiGenerico> list1 = new ArrayList<>();
        list1.add(pExec.ottieniPoi(p1));
        list1.add(pExec.ottieniPoi(p2));

        List<PoiGenerico> list2 = new ArrayList<>();
        list2.add(pExec.ottieniPoi(p3));
        list2.add(pExec.ottieniPoi(p4));

        List<PoiGenerico> list3 = new ArrayList<>();
        list3.add(pExec.ottieniPoi(p2));
        list3.add(pExec.ottieniPoi(p3));

        List<PoiGenerico> list4 = new ArrayList<>();
        list4.add(pExec.ottieniPoi(p4));
        list4.add(pExec.ottieniPoi(p1));

        pExec.aggiungiItinerarioDaValidare(i1, list1,null);
        pExec.aggiungiItinerarioValidato(i2, list2,periodo1);
        pExec.aggiungiItinerarioValidato(i3, list3,null);
        pExec.aggiungiItinerarioDaValidare(i4, list4,periodo2);

        List<PoiGenerico> pois = new ArrayList<>();
        pois.addAll(pExec.ottieniPoiDaValidare());
        pois.addAll(pExec.ottieniPoiValidati());

        List<ItinerarioGenerico> itinerari = new ArrayList<>();
        itinerari.addAll(pExec.ottieniItinerariDaValidare());
        itinerari.addAll(pExec.ottieniItinerariValidati());

        stampaPOI(pois);
        System.out.println("------------------------------------------------------------------------------------------------");
        stampaItinerari(itinerari);

    }

    private static void stampaPOI(List<PoiGenerico> pois){
        for(PoiGenerico p : pois){
            if(p instanceof PoiEvento pe){
                System.out.println(
                        "Tipo POI: " + pe.getTipo() +
                                "; Nome POI: " + pe.getTitolo() +
                                "; Descrizione POI: " + pe.getDescrizione() +
                                "; Data Inizio POI: " + pe.getPeriodo().getDataInizio() +
                                "; Data Fine POI: " + pe.getPeriodo().getDataFine()
                );
            } else {
                System.out.println(
                        "Tipo POI: " + p.getTipo() +
                                "; Nome POI: " + p.getTitolo() +
                                "; Descrizione POI: " + p.getDescrizione()

                );
            }
        }
    }

    private static void stampaItinerari(List<ItinerarioGenerico> itinerari){
        for(ItinerarioGenerico i : itinerari){
            if(i instanceof ItinerarioEvento iE){
                System.out.println(
                        "Tipo itinerario: " + iE.getTipo() +
                                "; Nome Itinerario: " + iE.getTitolo() +
                                "; Descrizione Itinerario: " + iE.getDescrizione() +
                                "; POI: "
                );
                stampaPOI(iE.getPoi());
            }

            if(i instanceof Percorso p){
                System.out.println(
                        "Tipo itinerario: " + p.getTipo() +
                                "; Nome Itinerario: " + p.getTitolo() +
                                "; Descrizione Itinerario: " + p.getDescrizione() +
                                "; POI: "
                );
                stampaPOI(p.getPoi());
            }

            if(i instanceof PercorsoEvento pE){
                System.out.println(
                        "Tipo itinerario: " + pE.getTipo() +
                                "; Nome POI: " + pE.getTitolo() +
                                "; Descrizione POI: " + pE.getDescrizione() +
                                "; POI: "
                );
                stampaPOI(pE.getPoi());
            }

            if(i instanceof Itinerario){
                System.out.println(
                        "Tipo itinerario: " + i.getTipo() +
                                "; Nome Itinerario: " + i.getTitolo() +
                                "; Descrizione Itinerario: " + i.getDescrizione() +
                                "; POI: "
                );
                stampaPOI(i.getPoi());
            }


        }
    }
}