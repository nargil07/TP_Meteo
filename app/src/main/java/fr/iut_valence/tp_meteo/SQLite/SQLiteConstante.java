package fr.iut_valence.tp_meteo.SQLite;

/**
 * Created by antony on 04/12/2015.
 */
public interface SQLiteConstante {
    String DB_NAME = "tp_meteo";
    int DB_VERSION = 6;

    //Table station
    String TABLE_STATION = "station";
    String TABLE_STATION_CHAMP_ID = "identifiant";
    String TABLE_STATION_CHAMP_LIBELLE = "libelle";
    String TABLE_STATION_CHAMP_DATE = "date";
    String TABLE_STATION_CHAMP_FAVORIS = "favoris";

    //table mesures
    String TABLE_MESURES = "mesures";
    String TABLE_MESURES_STATION = "station";
    String TABLE_MESURES_QUAND = "quand";
    String TABLE_MESURES_TEMP1 = "temp1";
    String TABLE_MESURES_TEMP2 = "temp2";
    String TABLE_MESURES_PRESSURE = "pressure";
    String TABLE_MESURES_LUX = "lux";
    String TABLE_MESURES_HYGRO = "hygro";
    String TABLE_MESURES_WINDSPEED = "windSpeed";
    String TABLE_MESURES_WINDDIR = "windir";
    String[] TABLE_MESURES_ALL_CHAMP = {
            TABLE_MESURES_STATION,
            TABLE_MESURES_QUAND,
            TABLE_MESURES_TEMP1,
            TABLE_MESURES_TEMP2,
            TABLE_MESURES_PRESSURE,
            TABLE_MESURES_LUX,
            TABLE_MESURES_HYGRO,
            TABLE_MESURES_WINDSPEED,
            TABLE_MESURES_WINDDIR
    };
}
