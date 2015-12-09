package fr.iut_valence.tp_meteo.metier;

import java.io.Serializable;

/**
 * Created by antony on 19/11/2015.
 */
public class Station implements Serializable {
    String identifiant;
    String libelle;

    public Station() {
    }

    public Station(String libelle, String identifiant) {
        this.libelle = libelle;
        this.identifiant = identifiant;
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
