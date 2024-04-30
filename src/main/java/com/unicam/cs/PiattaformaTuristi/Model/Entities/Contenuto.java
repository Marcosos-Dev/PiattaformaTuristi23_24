package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import java.io.File;

public class Contenuto {
    private int idContenuto;
    private String descrizione;
    private File file;

    public int getIdContenuto() {
        return idContenuto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenuto)) return false;
        Contenuto c = (Contenuto) o;
        return c.getIdContenuto() == this.getIdContenuto();
    }
}
