package fr.iut_valence.tp_meteo.metier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import fr.iut_valence.tp_meteo.SQLite.DAO.StationDAO;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.enumerator.EnumFavorisStation;

/**
 * Created by Antony on 09/12/2015.
 */
public class MetierStation {
    private final StationDAO stationDAO;
    private final SharedPreferences sharedPreferences;

    public MetierStation(Context context) {
        stationDAO = new StationDAO(context);
        sharedPreferences = context.getSharedPreferences("STATION_PREFERENCE", 0);
    }

    public List<Station> getAll() {
        long date = sharedPreferences.getLong("last_update_stations", 0);
        Map<String, ?> m = sharedPreferences.getAll();

        if (date < Calendar.getInstance().getTimeInMillis() - (24 * 60 * 60 * 1000)) {
            String url_select = "http://intranet.iut-valence.fr/~marcanto/TP-Meteo/";
            URL url = null;
            JSONArray jsonArray = null;
            try {
                url = new URL(url_select);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                String userPassword = "marcanto:Foumaka07";
                String encoded = Base64.encodeToString(userPassword.getBytes(), Base64.DEFAULT);
                conn.setRequestProperty("Authorization", "basic " + encoded);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept-Charset", "utf-8");

                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.connect();

                InputStream is = conn.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputString = null;
                while ((inputString = streamReader.readLine()) != null){
                    responseStrBuilder.append(inputString);
                }
                jsonArray = new JSONArray(responseStrBuilder.toString());
                for(int i = 0; i < jsonArray.length(); ++i){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    Station station = stationDAO.get(id);
                    if(station == null){
                        station = new Station();
                        station.setIdentifiant(id);
                        station.setLibelle(jsonObject.getString("libellé"));
                        stationDAO.add(station);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("last_update_stations", Calendar.getInstance().getTimeInMillis());
            editor.commit();
            m = sharedPreferences.getAll();
        }
        return stationDAO.getAll();
    }

    public List<Station> getAllFavoris(){
        return stationDAO.getByIdFavoris(EnumFavorisStation.FAVORIS.ordinal());
    }

    public void addStation(String identifiant, String libelle){
        Station station = new Station();
        station.setLibelle(libelle);
        station.setIdentifiant(identifiant);
        station.setDate(0);
        station.setFavoris(0);
        stationDAO.add(station);
    }

    public void addToFavoris(Station station){
        station.setFavoris(EnumFavorisStation.FAVORIS.ordinal());
        stationDAO.update(station);
    }

    public void removeToFavoris(Station station){
        station.setFavoris(EnumFavorisStation.NO_FAVORIS.ordinal());
        stationDAO.update(station);
    }
}
