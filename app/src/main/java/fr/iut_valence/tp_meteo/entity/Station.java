package fr.iut_valence.tp_meteo.entity;

import java.io.Serializable;

/**
 * Created by antony on 19/11/2015.
 */
public class Station implements Serializable {
    String identifiant;
    String libelle;
    long date;
    int favoris;

    public Station() {
    }

    public Station(String libelle, String identifiant, long date, short favoris) {
        this.libelle = libelle;
        this.identifiant = identifiant;
        this.date = date;
        this.favoris = favoris;
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

    public int getFavoris() { return favoris; }

    public void setFavoris(int favoris) {
        this.favoris = favoris;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
