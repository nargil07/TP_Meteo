package fr.iut_valence.tp_meteo.metier;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.List;

import fr.iut_valence.tp_meteo.SQLite.DAO.StationDAO;
import fr.iut_valence.tp_meteo.entity.Station;

/**
 * Created by Antony on 09/12/2015.
 */
public class MetierStation {
    private final StationDAO stationDAO;
    private final SharedPreferences sharedPreferences;

    public MetierStation(Context context) {
        stationDAO = new StationDAO(context);
        sharedPreferences = context.getSharedPreferences("STATION_PREFERENCE", Context.MODE_PRIVATE);
    }

    public List<Station> getAll() {
        long date = sharedPreferences.getLong("last_update_stations", 0);
        if (date < Calendar.getInstance().getTimeInMillis() - (24 * 60 * 60 * 1000)) {
            sharedPreferences.edit().putLong("last_update_stations", Calendar.getInstance().getTimeInMillis());
            //TODO mettre a jour la base de données sqlite
        } else {
            //TODO recuperer directement de sqlite
        }

        return stationDAO.getAll();

    }
}
