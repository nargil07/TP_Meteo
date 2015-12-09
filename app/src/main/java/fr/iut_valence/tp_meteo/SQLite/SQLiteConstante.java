package fr.iut_valence.tp_meteo.SQLite;

/**
 * Created by antony on 04/12/2015.
 */
public interface SQLiteConstante {
    String DB_NAME = "tp_meteo";
    int DB_VERSION = 1;
    String TABLE_STATION = "station";
    String TABLE_STATION_CHAMP_ID = "identifiant";
    String TABLE_STATION_CHAMP_LIBELLE = "libelle";
    String TABLE_STATION_CHAMP_DATE = "date";
}
