package fr.iut_valence.tp_meteo.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.antony.tp_meteo.R;
import fr.iut_valence.tp_meteo.metier.Station;

import java.util.ArrayList;

/**
 * Created by antony on 19/11/2015.
 */
public class StationAdapter extends BaseAdapter{
    private ArrayList<Station> listStation;
    private LayoutInflater layoutInflater;

    public StationAdapter(ArrayList<Station> listStation, Context context) {
        this.listStation = listStation;
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

        StationViewHolder holder;
        if(convertView == null){
            convertView =layoutInflater.inflate(R.layout.elem_station, null);
            holder = new StationViewHolder();
            holder.tvIdentifiant = (TextView) convertView.findViewById(R.id.textView_identifiant);
            holder.tvLibelle = (TextView) convertView.findViewById(R.id.textView_libelle);
            convertView.setTag(holder);
        }else{
            holder = (StationViewHolder) convertView.getTag();
        }

        holder.tvIdentifiant.setText(listStation.get(position).getIdentifiant());
        holder.tvLibelle.setText(listStation.get(position).getLibelle());

        return convertView;
    }
}
