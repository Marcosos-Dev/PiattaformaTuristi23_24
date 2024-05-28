package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Comune {
    private String nome;
    private List<PoiGenerico> poiValidati;
    private List<PoiGenerico> poiDaValidare;
    private List<ItinerarioGenerico> itinerariValidati;
    private List<ItinerarioGenerico> itinerariDaValidare;
    private List<Contest> contestAperti;
    private List<Contest> contestChiusi;
    private HashMap<Segnalazione,PoiGenerico> segnalazioniPoi;
    private HashMap<Segnalazione,ItinerarioGenerico> segnalazioniItinerari;


    public Comune(String nome){
        this.nome = nome;
        this.poiValidati = new ArrayList<>();
        this.poiDaValidare = new ArrayList<>();
        this.itinerariValidati = new ArrayList<>();
        this.itinerariDaValidare = new ArrayList<>();
        this.contestAperti = new ArrayList<>();
        this.contestChiusi = new ArrayList<>();
        this.segnalazioniPoi = new HashMap<>();
        this.segnalazioniItinerari = new HashMap<>();
    }

    public void chiudiContest(Contest c){
        this.contestChiusi.add(c);
        this.contestAperti.remove(c);
    }

    public void inserisciSegnalazionePoi(Segnalazione segnalazione, PoiGenerico poi) { this.segnalazioniPoi.put(segnalazione,poi); }

    public void inserisciSegnalazioneItinerari(Segnalazione segnalazione, ItinerarioGenerico itinerario) { this.segnalazioniItinerari.put(segnalazione,itinerario); }

    public void inserisciContestAperto(Contest contest){ this.contestAperti.add(contest); }

    public void inserisciPoiDaValidare(PoiGenerico poi){ this.poiDaValidare.add(poi); }

    public void inserisciPoiValidato(PoiGenerico poi){
        this.poiValidati.add(poi);
    }

    public void inserisciItinerarioDaValidare(ItinerarioGenerico itinerario){ this.itinerariDaValidare.add(itinerario); }

    public void inserisciItinerarioValidato(ItinerarioGenerico itinerario){
        this.itinerariValidati.add(itinerario);
    }

    public void rimuoviPoiDaValidare(PoiGenerico poi){ this.poiDaValidare.remove(poi); }

    public void rimuoviItinerarioDaValidare(ItinerarioGenerico itinerario){ this.itinerariDaValidare.remove(itinerario); }

    public void rimuoviItinerario(int idItinerario){ this.itinerariValidati.remove(this.getItinerario(idItinerario)); }

    public void rimuoviSegnalazione(Segnalazione segnalazione) { this.segnalazioniPoi.remove(segnalazione); }

    public void rimuoviPoi(int idPoi){
        PoiGenerico poi = this.getPoi(idPoi);
        this.poiValidati.remove(poi);
        Iterator<ItinerarioGenerico> iterator = itinerariValidati.iterator();
        while (iterator.hasNext()) {
            ItinerarioGenerico itinerario = iterator.next();
            List<PoiGenerico> poiFiltrati = itinerario.getPoi().stream()
                    .filter(p -> p.getIdPoi() != idPoi)
                    .toList();
            itinerario.setPoi(poiFiltrati);
            if (itinerario.getPoi().size() < 2) { iterator.remove(); }
        }
    }

    public void rimuoviSegnalazioniPoi(int idPoi) { this.segnalazioniPoi.entrySet().removeIf(entry -> entry.getValue().getIdPoi() == idPoi); }

    public void rimuoviSegnalazioniItinerario(int idItinerario) { this.segnalazioniItinerari.entrySet().removeIf(entry -> entry.getValue().getIdItinerario() == idItinerario); }

    public PoiGenerico getPoi(int idPoi) { return this.getPoiValidati().stream().filter(p -> p.getIdPoi() == idPoi).findFirst().orElse(null); }

    public ItinerarioGenerico getItinerario(int idItinerario) { return this.getItinerariValidati().stream().filter(i -> i.getIdItinerario() == idItinerario).findFirst().orElse(null); }

    public Contest getContest(int idContest) { return this.getContestChiusi().stream().filter(c -> c.getIdContest()==idContest).findFirst().orElse(null); }

    public Segnalazione getSegnalazionePoi(int idSegnalazione) { return this.getSegnalazioniPoi().stream().filter(s -> s.getIdSegnalazione() == idSegnalazione).findFirst().orElse(null); }

    public int getPoiSegnalato(Segnalazione segnalazione) { return this.segnalazioniPoi.get(segnalazione).getIdPoi(); }

    public int getItinerarioSegnalato(Segnalazione segnalazione) { return this.segnalazioniItinerari.get(segnalazione).getIdItinerario(); }

    public List<PoiGenerico> getPoiValidati() { return poiValidati; }

    public List<PoiGenerico> getPoiDaValidare() { return poiDaValidare; }

    public List<ItinerarioGenerico> getItinerariValidati() { return itinerariValidati; }

    public List<ItinerarioGenerico> getItinerariDaValidare(){ return itinerariDaValidare; }

    public List<Contest> getContestAperti() { return contestAperti; }

    public List<Contest> getContestChiusi() { return contestChiusi; }

    public List<Contenuto> getContenutiValidati() { return this.getPoiValidati().stream().filter(c -> !c.getContenutiValidati().isEmpty()).flatMap(p -> p.getContenutiValidati().stream()).toList(); }

    public List<Contenuto> getContenutiDaValidare() { return this.getPoiValidati().stream().filter(c -> !c.getContenutiDaValidare().isEmpty()).flatMap(p -> p.getContenutiDaValidare().stream()).toList(); }

    public List<Segnalazione> getSegnalazioniPoi() { return this.segnalazioniPoi.keySet().stream().toList(); }

    public List<Segnalazione> getSegnalazioniItinerari() { return this.segnalazioniItinerari.keySet().stream().toList(); }

    public int getLastIdContenuto(){
        return Math.max(
                this.getContenutiDaValidare().isEmpty() ?
                        1 : this.getContenutiDaValidare().getLast().getIdContenuto()+1,
                this.getContenutiValidati().isEmpty() ?
                        1 : this.getContenutiValidati().getLast().getIdContenuto()+1
        );
    }

    public int getLastIdItinerario(){
        return Math.max(
                this.getItinerariDaValidare().isEmpty() ?
                        1 : this.getItinerariDaValidare().getLast().getIdItinerario()+1,
                this.getItinerariValidati().isEmpty() ?
                        1 : this.getItinerariValidati().getLast().getIdItinerario()+1
        );
    }

    public int getUltimoIdPoi(){
        return Math.max(
                this.getPoiValidati().isEmpty() ?
                    1 : this.getPoiValidati().getLast().getIdPoi()+1,
                this.getPoiDaValidare().isEmpty() ?
                        1 : this.getPoiDaValidare().getLast().getIdPoi()+1
        );
    }

    public int getUltimoIdContest(){
        return this.getContestAperti().isEmpty() ?
                1 : this.getContestAperti().getLast().getIdContest()+1;
    }

    public int getUltimoIdSegnalazione(){
        return Math.max(
                this.getSegnalazioniPoi().isEmpty() ?
                        1 : this.getSegnalazioniPoi().getLast().getIdSegnalazione()+1,
                this.getSegnalazioniItinerari().isEmpty() ?
                        1 : this.getSegnalazioniItinerari().getLast().getIdSegnalazione()+1
        );
    }

    public boolean internoAlComune(Coordinate coord) {
        String apiUrl = String.format(Locale.US, "https://nominatim.openstreetmap.org/reverse?format=json&lat=%.6f&lon=%.6f&zoom=10&addressdetails=1",
                coord.getLatitudine(), coord.getLongitudine());

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String jsonResponse = new String(connection.getInputStream().readAllBytes());
                return jsonResponse.contains("\""+this.nome+"\"");
            }
        } catch (IOException ignored) { }

        return false;
    }

    public boolean poiDuplicato(PoiGenerico poi) {
        for(PoiGenerico p : poiValidati){
            if(poi.equals(p))
                return true;
        }
        return false;
    }

    //Metodi usati nei test
    public void stampaPOIValidati(){
        for(PoiGenerico p : getPoiValidati()){
            System.out.println(p);
        }
    }

    public void stampaPOIDaValidare(){
        for(PoiGenerico p : getPoiDaValidare()){
            System.out.println(p);
        }
    }

    public void stampaItinerariValidati(){
        for(ItinerarioGenerico i : getItinerariValidati()){
            System.out.println(i);
        }
    }

    public void stampaItinerariDaValidare(){
        for(ItinerarioGenerico i : getItinerariDaValidare()){
            System.out.println(i);
        }
    }

    public void stampaContestAperti(){
        for(Contest p : getContestAperti()){
            System.out.println(p);
        }
    }

    public void stampaContestChiusi(){
        for(Contest p : getContestChiusi()){
            System.out.println(p);
        }
    }
}
