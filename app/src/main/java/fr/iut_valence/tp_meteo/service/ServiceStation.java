package fr.iut_valence.tp_meteo.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.metier.MetierMesure;

/**
 * Created by antony on 17/12/2015.
 */
public class ServiceStation {

    private final Station station;
    private final MetierMesure metierMesure;

    public ServiceStation(Context context, Station station) {
        this.station = station;
        this.metierMesure = new MetierMesure(context);
    }

    public List<Mesure> getLast(){
        return metierMesure.getLast(this.station);
    }

    public List<Mesure> getMesures(){
        return metierMesure.getAll(this.station);
    }

}
