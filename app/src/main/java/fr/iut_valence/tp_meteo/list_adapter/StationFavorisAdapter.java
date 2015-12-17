package fr.iut_valence.tp_meteo.list_adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antony.tp_meteo.R;

import java.util.ArrayList;
import java.util.List;

import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.enumerator.EnumFavorisStation;
import fr.iut_valence.tp_meteo.metier.MetierMesure;
import fr.iut_valence.tp_meteo.metier.MetierStation;
import fr.iut_valence.tp_meteo.service.ServiceStation;

/**
 * Created by antony on 19/11/2015.
 */
public class StationFavorisAdapter extends BaseAdapter{

    private ArrayList<Station> listStation;
    private LayoutInflater layoutInflater;
    private Context context;
    private MetierStation metierStation;

    public StationFavorisAdapter(ArrayList<Station> listStation, Context context, MetierStation metierStation) {
        this.listStation = listStation;
        this.context = context;
        this.metierStation = metierStation;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.listStation.size();
    }

    @Override
    public Station getItem(int position) {
        return listStation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StationFavorisViewHolder holder;
        final Station station = listStation.get(position);
        final ServiceStation serviceStation = new ServiceStation(context, station);
        if(convertView == null){
            convertView =layoutInflater.inflate(R.layout.elem_station, null);
            holder = new StationFavorisViewHolder();
            holder.tvIdentifiant = (TextView) convertView.findViewById(R.id.textView_identifiant);
            holder.tvLibelle = (TextView) convertView.findViewById(R.id.textView_libelle);
            holder.lvMesures = (ListView) convertView.findViewById(R.id.listViewMesu);
            convertView.setTag(holder);
        }else{
            holder = (StationFavorisViewHolder) convertView.getTag();
        }

        holder.tvIdentifiant.setText(station.getIdentifiant());
        holder.tvLibelle.setText(station.getLibelle());
        holder.lvMesures.setAdapter(new MesureAdapter(context, new ArrayList<Mesure>(serviceStation.getLast())));
        return convertView;
    }
}
