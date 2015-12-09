package fr.iut_valence.tp_meteo.SQLite.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.iut_valence.tp_meteo.SQLite.MeteoOpenHelper;
import fr.iut_valence.tp_meteo.SQLite.SQLiteConstante;

import org.json.JSONObject;

/**
 * Created by antony on 04/12/2015.
 */
public abstract class AbstractDAO<T> implements SQLiteConstante{
    protected SQLiteDatabase bdd;
    private MeteoOpenHelper maBase;

    public AbstractDAO(Context context) {
        maBase = new MeteoOpenHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void open(){
        bdd = maBase.getWritableDatabase();
    }

    public void close(){ bdd.close(); }

    public abstract T getById(String id);

    public abstract void insertByJson(JSONObject jsonObject);

    public abstract void add(T entity);


}
