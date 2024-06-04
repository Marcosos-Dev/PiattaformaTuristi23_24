package com.unicam.cs.PiattaformaTuristi.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinate {
    private double longitudine;
    private double latitudine;

    public Coordinate(double latitudine,double longitudine){
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Coordinate() {

    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate c = (Coordinate) o;
        return c.getLatitudine()== this.getLatitudine() &&
                c.getLongitudine() == this.getLongitudine();
    }
}
