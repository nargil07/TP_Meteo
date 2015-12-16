package fr.iut_valence.tp_meteo.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antony on 04/12/2015.
 */
public class MeteoOpenHelper extends SQLiteOpenHelper implements SQLiteConstante{
    private static final String CREATE_TABLE_STATION = "CREATE TABLE IF NOT EXISTS " + TABLE_STATION + " ('" + TABLE_STATION_CHAMP_ID +
            "' TEXT NOT NULL PRIMARY KEY, '" +
            TABLE_STATION_CHAMP_LIBELLE + "' TEXT NOT NULL, '" +
            TABLE_STATION_CHAMP_DATE + "' INTEGER, " +
            TABLE_STATION_CHAMP_FAVORIS + " INTEGER);";
    private static final String CREATE_TABLE_MESURES = "CREATE TABLE IF NOT EXISTS " + TABLE_MESURES + " ('" + TABLE_MESURES_STATION +
            "' TEXT NOT NULL, '" +
            TABLE_MESURES_QUAND + "' INTEGER, '" +
            TABLE_MESURES_TEMP1 + "' REAL, '" +
            TABLE_MESURES_TEMP2 + "' REAL, '" +
            TABLE_MESURES_PRESSURE + "' INTEGER, '" +
            TABLE_MESURES_LUX + "' REAL, '" +
            TABLE_MESURES_HYGRO + "' REAL, '" +
            TABLE_MESURES_WINDDIR + "' REAL, '" +
            TABLE_MESURES_WINDSPEED + "' REAL);";

    public MeteoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATION);
        db.execSQL(CREATE_TABLE_MESURES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESURES);
        db.execSQL(CREATE_TABLE_STATION);
        db.execSQL(CREATE_TABLE_MESURES);
    }
}
