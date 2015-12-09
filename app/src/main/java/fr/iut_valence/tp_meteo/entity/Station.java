package fr.iut_valence.tp_meteo.entity;

import java.io.Serializable;

/**
 * Created by antony on 19/11/2015.
 */
public class Station implements Serializable {
    String identifiant;
    String libelle;
    long date;

    public Station() {
    }

    public Station(String libelle, String identifiant, long date) {
        this.libelle = libelle;
        this.identifiant = identifiant;
        this.date = date;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
