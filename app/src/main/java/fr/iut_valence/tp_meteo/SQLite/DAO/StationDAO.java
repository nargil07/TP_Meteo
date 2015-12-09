package fr.iut_valence.tp_meteo.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.iut_valence.tp_meteo.metier.Station;

import org.json.JSONObject;

/**
 * Created by Antony on 06/12/2015.
 */
public class StationDAO extends AbstractDAO<Station> {


    public StationDAO(Context context) {
        super(context);
    }

    @Override
    public Station getById(String id) {
        this.open();
        Cursor c = this.bdd.query(TABLE_STATION, new String[]{TABLE_STATION_CHAMP_ID, TABLE_STATION_CHAMP_LIBELLE}, TABLE_STATION_CHAMP_ID, new String[]{id}, null, null, null);
        c.moveToFirst();
        Station station = null;
        while (c.moveToNext()){
            station = new Station(c.getString(0), c.getString(1));
        }
        return station;
    }

    @Override
    public void insertByJson(JSONObject jsonObject) {
    }

    @Override
    public void add(Station entity) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_STATION_CHAMP_ID, entity.getIdentifiant());
        contentValues.put(TABLE_STATION_CHAMP_LIBELLE, entity.getLibelle());
        this.bdd.insert(TABLE_STATION,null, contentValues);
        this.close();
    }
}
