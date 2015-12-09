package fr.iut_valence.tp_meteo.grid_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.antony.tp_meteo.R;
import fr.iut_valence.tp_meteo.list_adapter.StationViewHolder;
import fr.iut_valence.tp_meteo.entity.Station;

import java.util.ArrayList;

/**
 * Created by antony on 19/11/2015.
 */
public class StationGridAdapter extends BaseAdapter{
    private ArrayList<Station> listStation;
    private LayoutInflater layoutInflater;

    public StationGridAdapter(ArrayList<Station> listStation, Context context) {
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
            convertView =layoutInflater.inflate(R.layout.elem_grid_station, null);
            holder = new StationViewHolder();
            holder.tvIdentifiant = (TextView) convertView.findViewById(R.id.textView_grid_id);
            holder.tvLibelle = (TextView) convertView.findViewById(R.id.textView_grid_libelle);
            convertView.setTag(holder);
        }else{
            holder = (StationViewHolder) convertView.getTag();
        }

        holder.tvIdentifiant.setText(listStation.get(position).getIdentifiant());
        holder.tvLibelle.setText(listStation.get(position).getLibelle());

        return convertView;
    }
}
