package fr.iut_valence.tp_meteo.metier;

import android.content.Context;

import org.json.JSONArray;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

import fr.iut_valence.tp_meteo.SQLite.DAO.MesureDAO;
import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;

/**
 * Created by Antony on 13/12/2015.
 */
public class MetierMesure {
    private final MesureDAO mesureDAO;

    public MetierMesure(Context context) {
        this.mesureDAO = new MesureDAO(context);
    }

    public void addMesure(String station, long quand, float temp1, float temp2, int pressure, float lux, float hygro, float windDir, float windSpeed){
        mesureDAO.add(new Mesure(station, quand, temp1, temp2, pressure, lux, hygro, windDir, windSpeed));
    }

    public List<Mesure> getLast(Station station){
        if (station.getDate() < Calendar.getInstance().getTimeInMillis() - (24 * 60 * 60 * 1000)){
            String url_select = "http://intranet.iut-valence.fr/~marcanto/TP-Meteo/index.php?";
            URL url = null;
            JSONArray jsonArray = null;
        }
        return mesureDAO.getLast(station.getIdentifiant());
    }

    public List<Mesure> getAll(Station station){
        return mesureDAO.getByIdStation(station.getIdentifiant(), -1);
    }
}
