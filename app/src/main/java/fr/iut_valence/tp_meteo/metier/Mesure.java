package fr.iut_valence.tp_meteo.metier;

import java.io.Serializable;

/**
 * Created by antony on 27/11/2015.
 */
public class Mesure implements Serializable{

    private String quand;
    private String temp1;
    private String temp2;
    private String pressure;
    private String lux;
    private String hygro;
    private String windDir;
    private String windSpeed;

    public Mesure() {
    }

    public Mesure(String quand, String temp1, String temp2, String pressure, String lux, String hygro, String windDir, String windSpeed) {
        this.quand = quand;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.pressure = pressure;
        this.lux = lux;
        this.hygro = hygro;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
    }

    public String getQuand() {
        return quand;
    }

    public void setQuand(String quand) {
        this.quand = quand;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getLux() {
        return lux;
    }

    public void setLux(String lux) {
        this.lux = lux;
    }

    public String getHygro() {
        return hygro;
    }

    public void setHygro(String hygro) {
        this.hygro = hygro;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
