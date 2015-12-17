package fr.iut_valence.tp_meteo.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.exception.BadJsonExpectException;

/**
 * Created by Antony on 13/12/2015.
 */
public class MesureDAO extends AbstractDAO<Mesure> {

    public MesureDAO(Context context) {
        super(context);
    }

    @Override
    public Mesure get(String id) {
        return null;
    }

    @Override
    public void update(Mesure entity) {

    }

    @Override
    public void insertByJson(JSONArray jsonArray) throws BadJsonExpectException {

    }

    @Override
    public void add(Mesure entity) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_MESURES_STATION, entity.getStation());
        contentValues.put(TABLE_MESURES_HYGRO, entity.getHygro());
        contentValues.put(TABLE_MESURES_LUX, entity.getLux());
        contentValues.put(TABLE_MESURES_PRESSURE, entity.getPressure());
        contentValues.put(TABLE_MESURES_QUAND, entity.getQuand());
        contentValues.put(TABLE_MESURES_TEMP1, entity.getTemp1());
        contentValues.put(TABLE_MESURES_TEMP2, entity.getTemp2());
        contentValues.put(TABLE_MESURES_WINDDIR, entity.getWindDir());
        contentValues.put(TABLE_MESURES_WINDSPEED, entity.getWindSpeed());
        this.bdd.insert(TABLE_MESURES, null, contentValues);
        this.close();
    }

    @Override
    public List<Mesure> getAll() {
        return null;
    }

    public List<Mesure> getLast(String identifiant){
        this.open();
        Cursor c = this.bdd.query(TABLE_MESURES, TABLE_MESURES_ALL_CHAMP, TABLE_MESURES_STATION + " = ?", new String[]{identifiant}, null, null, TABLE_MESURES_QUAND + " DESC", "5");
        List<Mesure> list = new ArrayList<>();
        while(c.moveToNext()){
            Mesure mesure = new Mesure();
            mesure.setStation(c.getString(c.getColumnIndex(TABLE_MESURES_STATION)));
            mesure.setQuand(c.getLong(c.getColumnIndex(TABLE_MESURES_QUAND)));
            mesure.setHygro(c.getFloat(c.getColumnIndex(TABLE_MESURES_HYGRO)));
            mesure.setLux(c.getFloat(c.getColumnIndex(TABLE_MESURES_LUX)));
            mesure.setPressure(c.getInt(c.getColumnIndex(TABLE_MESURES_PRESSURE)));
            mesure.setTemp1(c.getFloat(c.getColumnIndex(TABLE_MESURES_TEMP1)));
            mesure.setTemp2(c.getFloat(c.getColumnIndex(TABLE_MESURES_TEMP2)));
            mesure.setWindDir(c.getFloat(c.getColumnIndex(TABLE_MESURES_WINDDIR)));
            mesure.setWindSpeed(c.getFloat(c.getColumnIndex(TABLE_MESURES_WINDSPEED)));
            list.add(mesure);
        }
        this.close();

        return list;
    }

    public List<Mesure> getByIdStation(String id, int limit){
        String sqlLimit = null;
        if(limit > 0){
            sqlLimit = String.valueOf(limit);
        }
        this.open();
        Cursor c = this.bdd.query(TABLE_MESURES, TABLE_MESURES_ALL_CHAMP, TABLE_MESURES_STATION + " = ?", new String[]{id}, null, null, TABLE_MESURES_QUAND + " DESC", sqlLimit);
        List<Mesure> list = new ArrayList<>();
        while(c.moveToNext()){
            Mesure mesure = new Mesure();
            mesure.setStation(c.getString(c.getColumnIndex(TABLE_MESURES_STATION)));
            mesure.setQuand(c.getLong(c.getColumnIndex(TABLE_MESURES_QUAND)));
            mesure.setHygro(c.getFloat(c.getColumnIndex(TABLE_MESURES_HYGRO)));
            mesure.setLux(c.getFloat(c.getColumnIndex(TABLE_MESURES_LUX)));
            mesure.setPressure(c.getInt(c.getColumnIndex(TABLE_MESURES_PRESSURE)));
            mesure.setTemp1(c.getFloat(c.getColumnIndex(TABLE_MESURES_TEMP1)));
            mesure.setTemp2(c.getFloat(c.getColumnIndex(TABLE_MESURES_TEMP2)));
            mesure.setWindDir(c.getFloat(c.getColumnIndex(TABLE_MESURES_WINDDIR)));
            mesure.setWindSpeed(c.getFloat(c.getColumnIndex(TABLE_MESURES_WINDSPEED)));
            list.add(mesure);
        }
        this.close();

        return list;
    }
}
