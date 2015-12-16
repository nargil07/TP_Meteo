package fr.iut_valence.tp_meteo.entity;

import java.io.Serializable;

/**
 * Created by antony on 27/11/2015.
 */
public class Mesure implements Serializable{

    private String station;
    private long quand;
    private float temp1;
    private float temp2;
    private int pressure;
    private float lux;
    private float hygro;
    private float windDir;
    private float windSpeed;

    public Mesure() {
    }

    public Mesure(String station, long quand, float temp1, float temp2, int pressure, float lux, float hygro, float windDir, float windSpeed) {
        this.station = station;
        this.quand = quand;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.pressure = pressure;
        this.lux = lux;
        this.hygro = hygro;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public long getQuand() {
        return quand;
    }

    public void setQuand(long quand) {
        this.quand = quand;
    }

    public float getTemp1() {
        return temp1;
    }

    public void setTemp1(float temp1) {
        this.temp1 = temp1;
    }

    public float getTemp2() {
        return temp2;
    }

    public void setTemp2(float temp2) {
        this.temp2 = temp2;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getLux() {
        return lux;
    }

    public void setLux(float lux) {
        this.lux = lux;
    }

    public float getHygro() {
        return hygro;
    }

    public void setHygro(float hygro) {
        this.hygro = hygro;
    }

    public float getWindDir() {
        return windDir;
    }

    public void setWindDir(float windDir) {
        this.windDir = windDir;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }
}
