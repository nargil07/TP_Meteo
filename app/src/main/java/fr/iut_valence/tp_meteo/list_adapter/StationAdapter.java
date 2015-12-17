package fr.iut_valence.tp_meteo.list_adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antony.tp_meteo.R;

import fr.iut_valence.tp_meteo.ListMesureActivity;
import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.enumerator.EnumFavorisStation;
import fr.iut_valence.tp_meteo.metier.MetierMesure;
import fr.iut_valence.tp_meteo.metier.MetierStation;
import fr.iut_valence.tp_meteo.service.ServiceStation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antony on 19/11/2015.
 */
public class StationAdapter extends BaseAdapter{
    private ArrayList<Station> listStation;
    private LayoutInflater layoutInflater;
    private Context context;
    private MetierStation metierStation;
    public StationAdapter(ArrayList<Station> listStation, Context context, MetierStation metierStation) {
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

        StationViewHolder holder;
        final Station station = listStation.get(position);
        final ServiceStation serviceStation = new ServiceStation(context, station);
        if(convertView == null){
            convertView =layoutInflater.inflate(R.layout.elem_station_favoris, null);
            holder = new StationViewHolder();
            holder.tvIdentifiant = (TextView) convertView.findViewById(R.id.textView_identifiant);
            holder.tvLibelle = (TextView) convertView.findViewById(R.id.textView_libelle);
            holder.ivFavoris = (ImageView) convertView.findViewById(R.id.imageViewfavoris);
            holder.lnClick = (LinearLayout) convertView.findViewById(R.id.linearClick);
            holder.lvMesures = (ListView) convertView.findViewById(R.id.listViewMesu);
            convertView.setTag(holder);
        }else{
            holder = (StationViewHolder) convertView.getTag();
        }

        holder.tvIdentifiant.setText(station.getIdentifiant());
        holder.tvLibelle.setText(station.getLibelle());
        holder.lnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ListMesureActivity.class);
                i.putExtra("station", station);
                context.startActivity(i);
            }
        });
        holder.lvMesures.setAdapter(new MesureAdapter(context, new ArrayList<Mesure>(serviceStation.getLast())));
        if(station.getFavoris() == EnumFavorisStation.FAVORIS.ordinal()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.ivFavoris.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_black_24dp));
            }else{
                holder.ivFavoris.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
            }
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.ivFavoris.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_border_black_24dp));
            }else{
                holder.ivFavoris.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            }
        }

        holder.ivFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view = (ImageView) v;
                if (station.getFavoris() == EnumFavorisStation.NO_FAVORIS.ordinal()) {
                    metierStation.addToFavoris(station);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_black_24dp));
                    } else {
                        view.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
                    }
                } else {
                    metierStation.removeToFavoris(station);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_border_black_24dp));
                    } else {
                        view.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                    }
                }
            }
        });
        return convertView;
    }
}
