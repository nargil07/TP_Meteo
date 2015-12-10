package fr.iut_valence.tp_meteo.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antony on 04/12/2015.
 */
public class MeteoOpenHelper extends SQLiteOpenHelper implements SQLiteConstante{
    private static final String CREATE_TABLE_STATION = "CREATE TABLE  " + TABLE_STATION + " ('" + TABLE_STATION_CHAMP_ID +
            "' TEXT NOT NULL PRIMARY KEY, '" +
            TABLE_STATION_CHAMP_LIBELLE + "' TEXT NOT NULL, '" +
            TABLE_STATION_CHAMP_DATE + "' REAL, " +
            TABLE_STATION_CHAMP_FAVORIS + " INTEGER);";

    public MeteoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATION);
        db.execSQL(CREATE_TABLE_STATION);
    }
}
