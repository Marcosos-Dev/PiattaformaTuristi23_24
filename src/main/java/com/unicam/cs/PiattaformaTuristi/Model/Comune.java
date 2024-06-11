package com.unicam.cs.PiattaformaTuristi.Model;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.*;
import jakarta.persistence.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
@Entity
public class Comune {
    @Id
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PoiGenerico> poiValidati;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PoiGenerico> poiDaValidare;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItinerarioGenerico> itinerariValidati;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItinerarioGenerico> itinerariDaValidare;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contest> contestAperti;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contest> contestChiusi;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SegnalazionePoi> segnalazioniPoi;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SegnalazioneItinerario> segnalazioniItinerari;


    public Comune(String nome){
        this.nome = nome;
        this.poiValidati = new ArrayList<>();
        this.poiDaValidare = new ArrayList<>();
        this.itinerariValidati = new ArrayList<>();
        this.itinerariDaValidare = new ArrayList<>();
        this.contestAperti = new ArrayList<>();
        this.contestChiusi = new ArrayList<>();
        this.segnalazioniPoi = new ArrayList<>();
        this.segnalazioniItinerari = new ArrayList<>();
    }

    public Comune() {

    }

    public void chiudiContest(Contest c){
        this.contestChiusi.add(c);
        this.contestAperti.remove(c);
    }

    public void inserisciSegnalazionePoi(Segnalazione segnalazione, PoiGenerico poi) { this.segnalazioniPoi.add(new SegnalazionePoi(segnalazione,poi)); }

    public void inserisciSegnalazioneItinerario(Segnalazione segnalazione, ItinerarioGenerico itinerario) { this.segnalazioniItinerari.add(new SegnalazioneItinerario(segnalazione,itinerario)); }

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

    public void rimuoviSegnalazioniPoi(PoiGenerico poi) { this.segnalazioniPoi.removeAll(this.segnalazioniPoi.stream().filter(s -> s.getPoiGenerico().equals(poi)).toList()); }

    public void rimuoviSegnalazioniItinerario(ItinerarioGenerico itinerario) { this.segnalazioniItinerari.removeAll(this.segnalazioniItinerari.stream().filter(s -> s.getItinerarioGenerico().equals(itinerario)).toList()); }

    public void rimuoviSegnalazionePoi(SegnalazionePoi segnalazione) { this.segnalazioniPoi.remove(segnalazione); }

    public void rimuoviSegnalazioneItinerario(SegnalazioneItinerario segnalazione) { this.segnalazioniItinerari.remove(segnalazione); }

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

    public PoiGenerico getPoi(int idPoi) { return this.getPoiValidati().stream().filter(p -> p.getIdPoi() == idPoi).findFirst().orElse(null); }

    public ItinerarioGenerico getItinerario(int idItinerario) { return this.getItinerariValidati().stream().filter(i -> i.getIdItinerario() == idItinerario).findFirst().orElse(null); }

    public Contest getContest(int idContest) { return this.getContestChiusi().stream().filter(c -> c.getIdContest()==idContest).findFirst().orElse(null); }

    public SegnalazionePoi getSegnalazionePoi(int idSegnalazione) { return this.getSegnalazioniPoi().stream().filter(s -> s.getIdSegnalazionePoi() == idSegnalazione).findFirst().orElse(null); }

    public SegnalazioneItinerario getSegnalazioneItinerario(int idSegnalazione) { return this.getSegnalazioniItinerari().stream().filter(s -> s.getIdSegnalazioneItinerario() == idSegnalazione).findFirst().orElse(null); }

    public List<PoiGenerico> getPoiValidati() { return poiValidati; }

    public List<PoiGenerico> getPoiDaValidare() { return poiDaValidare; }

    public List<ItinerarioGenerico> getItinerariValidati() { return itinerariValidati; }

    public List<ItinerarioGenerico> getItinerariDaValidare(){ return itinerariDaValidare; }

    public List<Contest> getContestAperti() { return contestAperti; }

    public List<Contest> getContestChiusi() { return contestChiusi; }

    public List<Contenuto> getContenutiValidati() { return this.getPoiValidati().stream().filter(c -> !c.getContenutiValidati().isEmpty()).flatMap(p -> p.getContenutiValidati().stream()).toList(); }

    public List<Contenuto> getContenutiDaValidare() { return this.getPoiValidati().stream().filter(c -> !c.getContenutiDaValidare().isEmpty()).flatMap(p -> p.getContenutiDaValidare().stream()).toList(); }

    public List<SegnalazionePoi> getSegnalazioniPoi() { return this.segnalazioniPoi; }

    public List<SegnalazioneItinerario> getSegnalazioniItinerari() { return this.segnalazioniItinerari; }


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

    public boolean poiDuplicato(Coordinate coordinate) {
        for(PoiGenerico p : poiValidati){
            if(p.getCoord().getLatitudine()==coordinate.getLatitudine() &&
                    p.getCoord().getLongitudine()==coordinate.getLongitudine())
                return true;
        }
        return false;
    }
}
