package fr.iut_valence.tp_meteo.service;

import android.content.Context;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.iut_valence.tp_meteo.SQLite.DAO.StationDAO;
import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.metier.MetierMesure;
import fr.iut_valence.tp_meteo.metier.MetierStation;

/**
 * Created by antony on 17/12/2015.
 */
public class ServiceStation {

    private final Station station;
    private final MetierMesure metierMesure;
    private final StationDAO stationDAO;

    public ServiceStation(Context context, Station station) {
        this.station = station;
        this.metierMesure = new MetierMesure(context);
        this.stationDAO = new StationDAO(context);
        if(this.station.getDate() <= Calendar.getInstance().getTimeInMillis() - (24 * 60 * 60 * 1000)){
            String url_select = "http://intranet.iut-valence.fr/~marcanto/TP-Meteo/index.php?station=" + station.getLibelle();
            URL url;
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
                JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                this.station.setLibelle(jsonObject.getString("libellé"));
                this.station.setLatitude(jsonObject.getString("latitude"));
                this.station.setLongitude(jsonObject.getString("longitude"));
                this.station.setAltitude(jsonObject.getString("altitude"));
                this.station.setDate(Calendar.getInstance().getTimeInMillis());
                this.stationDAO.update(this.station);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Mesure> getLast(){
        return metierMesure.getLast(this.station);
    }

    public List<Mesure> getMesures(){
        return metierMesure.getAll(this.station);
    }

}
