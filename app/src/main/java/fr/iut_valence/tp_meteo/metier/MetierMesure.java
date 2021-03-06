package fr.iut_valence.tp_meteo.metier;

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
import java.util.Calendar;
import java.util.List;

import fr.iut_valence.tp_meteo.SQLite.DAO.MesureDAO;
import fr.iut_valence.tp_meteo.SQLite.DAO.StationDAO;
import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;

/**
 * Created by Antony on 13/12/2015.
 */
public class MetierMesure {
    private final MesureDAO mesureDAO;
    private final StationDAO stationDAO;

    public MetierMesure(Context context) {
        this.mesureDAO = new MesureDAO(context);
        this.stationDAO = new StationDAO(context);
    }

    public void addMesure(String station, long quand, float temp1, float temp2, int pressure, float lux, float hygro, float windDir, float windSpeed){
        mesureDAO.add(new Mesure(station, quand, temp1, temp2, pressure, lux, hygro, windDir, windSpeed));
    }

    public List<Mesure> getLast(Station station){
        if (station.getDate() < Calendar.getInstance().getTimeInMillis() - (24 * 60 * 60 * 1000)){
            String url_select = "http://intranet.iut-valence.fr/~marcanto/TP-Meteo/index.php?station=" + station.getIdentifiant() + "&action=last";
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
                    Mesure mesure = new Mesure();
                    mesure.setStation(station.getIdentifiant());
                    mesure.setQuand(Long.getLong(jsonObject.getString("quand"), 0));
                    mesure.setTemp1(jsonObject.getString("temp1") != "null" ? Float.parseFloat(jsonObject.getString("temp1")) : 0);
                    mesure.setTemp2(jsonObject.getString("temp2") != "null" ? Float.parseFloat(jsonObject.getString("temp2")) : 0);
                    mesure.setPressure(jsonObject.getInt("pressure"));
                    mesure.setLux(jsonObject.getString("lux") != "null" ? Float.parseFloat(jsonObject.getString("lux")) : 0);
                    mesure.setHygro(jsonObject.getString("hygro") != "null" ? Float.parseFloat(jsonObject.getString("hygro")) : 0);
                    mesure.setWindSpeed(jsonObject.getString("windSpeed") != "null" ? Float.parseFloat(jsonObject.getString("windSpeed")) : 0);
                    mesure.setWindDir(jsonObject.getString("windDir") != "null" ? Float.parseFloat(jsonObject.getString("windDir")) : 0);
                    this.mesureDAO.add(mesure);
                }
                station.setDate(Calendar.getInstance().getTimeInMillis());
                this.stationDAO.update(station);

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
        return mesureDAO.getLast(station.getIdentifiant());
    }

    public List<Mesure> getAll(Station station){
        return mesureDAO.getByIdStation(station.getIdentifiant(), -1);
    }
}
