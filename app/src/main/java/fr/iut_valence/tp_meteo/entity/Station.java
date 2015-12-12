package fr.iut_valence.tp_meteo.entity;

import java.io.Serializable;

/**
 *
 * Cette classe représente une station.
 * @author Antony
 * @version 1.0.4
 */
public class Station implements Serializable {
    /**
     * L'identifiant de la station.
     */
    String identifiant;
    /**
     * Un libelle de la station
     */
    String libelle;
    /**
     * Cette variable represente la date de la derniere récupération des mesures liée à la station.
     */
    long date;
    /**
     * Cette variable indique si la station est dans les favoris
     */
    int favoris;

    /**
     * Instanciation d'une station vide
     */
    public Station() {
    }

    /**
     * Instanciation d'une station.
     * @param libelle le libelle de la station
     * @param identifiant l'identifiant de la station
     * @param date la date de la derniere mise a jour
     * @param favoris Si la station est dans les favoris
     */
    public Station(String libelle, String identifiant, long date, int favoris) {
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
