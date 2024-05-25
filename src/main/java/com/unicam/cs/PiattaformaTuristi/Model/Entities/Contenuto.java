package com.unicam.cs.PiattaformaTuristi.Model.Entities;

import java.io.File;

public class Contenuto {
    private int idContenuto;
    private String descrizione;
    private File file;

    public Contenuto(File file, String descrizione){
        this.file = file;
        this.descrizione = descrizione;
    }

    public Contenuto(){ }

    public void setIdContenuto(int idContenuto) { this.idContenuto = idContenuto; }

    public void setFile(File file) { this.file = file; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public int getIdContenuto() { return idContenuto; }

    public File getFile() { return file; }

    public String getDescrizione() { return descrizione; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenuto)) return false;
        Contenuto c = (Contenuto) o;
        return c.getIdContenuto() == this.getIdContenuto();
    }

    public String toString(){
        return "ID contenuto: " + this.getIdContenuto() +
                "; Descrizione contenuto: " + this.getDescrizione();
    }
}
