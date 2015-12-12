package fr.iut_valence.tp_meteo.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.iut_valence.tp_meteo.entity.Station;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antony on 06/12/2015.
 */
public class StationDAO extends AbstractDAO<Station> {


    public StationDAO(Context context) {
        super(context);
    }

    @Override
    public Station get(String id) {
        this.open();
        Cursor c = this.bdd.query(TABLE_STATION, new String[]{TABLE_STATION_CHAMP_ID, TABLE_STATION_CHAMP_LIBELLE, TABLE_STATION_CHAMP_DATE, TABLE_STATION_CHAMP_FAVORIS}, TABLE_STATION_CHAMP_ID + " = ?", new String[]{id}, null, null, null);

        Station station = null;
        while (c.moveToNext()){
            station = new Station(c.getString(1), c.getString(0), c.getLong(2), c.getShort(3));
        }
        this.close();
        return station;
    }

    @Override
    public void update(Station entity) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_STATION_CHAMP_LIBELLE, entity.getLibelle());
        contentValues.put(TABLE_STATION_CHAMP_DATE, entity.getDate());
        contentValues.put(TABLE_STATION_CHAMP_FAVORIS, entity.getFavoris());
        this.bdd.update(TABLE_STATION, contentValues, TABLE_STATION_CHAMP_ID + " = ?", new String[]{entity.getIdentifiant()});
        this.close();
    }

    @Override
    public void insertByJson(JSONArray jsonArray) {
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

    @Override
    public List<Station> getAll() {
        this.open();
        List<Station> list = new ArrayList<>();
        Cursor c = this.bdd.query(TABLE_STATION, new String[]{TABLE_STATION_CHAMP_ID, TABLE_STATION_CHAMP_LIBELLE, TABLE_STATION_CHAMP_DATE, TABLE_STATION_CHAMP_FAVORIS}, null, null, null, null, null);
        while (c.moveToNext()){
            list.add(new Station(c.getString(1), c.getString(0), c.getLong(2), c.getInt(3)));
        }
        this.close();
        return list;
    }

    public List<Station> getByIdFavoris(int i){
        this.open();
        List<Station> list = new ArrayList<>();
        Cursor c = this.bdd.query(TABLE_STATION, new String[]{TABLE_STATION_CHAMP_ID, TABLE_STATION_CHAMP_LIBELLE, TABLE_STATION_CHAMP_DATE, TABLE_STATION_CHAMP_FAVORIS}, TABLE_STATION_CHAMP_FAVORIS + " = ?", new String[]{String.valueOf(i)}, null, null, null);
        while (c.moveToNext()){
            list.add(new Station(c.getString(1), c.getString(0), c.getLong(2), c.getInt(3)));
        }
        this.close();
        return list;
    }
}
