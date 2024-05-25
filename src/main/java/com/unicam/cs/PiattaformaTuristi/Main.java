package com.unicam.cs.PiattaformaTuristi;

import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingContest;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingGestionRuoli;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingItinerari;
import com.unicam.cs.PiattaformaTuristi.TestingAPI.TestingPOI;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        TestingPOI t1 = new TestingPOI();
        TestingItinerari t2 = new TestingItinerari();
        TestingContest t3 = new TestingContest();
        TestingGestionRuoli t4 = new TestingGestionRuoli();
    }
}