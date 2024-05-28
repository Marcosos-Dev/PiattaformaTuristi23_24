package com.unicam.cs.PiattaformaTuristi;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.EventChecker.EventChecker;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingContest;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingGestionRuoli;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingItinerari;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingPOI;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Comune c = new Comune("Ancona");
        TestingPOI t1 = new TestingPOI(c);
        TestingItinerari t2 = new TestingItinerari(c);
        TestingContest t3 = new TestingContest(c);
        TestingGestionRuoli t4 = new TestingGestionRuoli(c);
        EventChecker tempo = new EventChecker(c);
    }
}