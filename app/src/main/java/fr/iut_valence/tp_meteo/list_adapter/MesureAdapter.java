package fr.iut_valence.tp_meteo.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.antony.tp_meteo.R;
import fr.iut_valence.tp_meteo.entity.Mesure;

import java.util.ArrayList;

/**
 * Created by antony on 27/11/2015.
 */
public class MesureAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Mesure> mesureArrayList;

    public MesureAdapter(Context context, ArrayList<Mesure> mesureArrayList) {
        this.mesureArrayList = mesureArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mesureArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mesureArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MesureViewHolder mesureViewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.elem_mesure, null);
            mesureViewHolder = new MesureViewHolder();
            mesureViewHolder.tvTemp1 = (TextView) convertView.findViewById(R.id.textViewTemp1);
            mesureViewHolder.tvTemp2 = (TextView) convertView.findViewById(R.id.textViewTemp2);
            mesureViewHolder.tvPressure = (TextView) convertView.findViewById(R.id.textViewPressure);
            mesureViewHolder.tvLux = (TextView) convertView.findViewById(R.id.textViewLux);
            mesureViewHolder.tvHydro = (TextView) convertView.findViewById(R.id.textViewHydro);
            mesureViewHolder.tvWindDir = (TextView) convertView.findViewById(R.id.textViewWindDir);
            mesureViewHolder.tvWindSpeed = (TextView) convertView.findViewById(R.id.textViewWindSpeed);
            convertView.setTag(mesureViewHolder);
        }else{
            mesureViewHolder =(MesureViewHolder) convertView.getTag();
        }
        Mesure mesure = mesureArrayList.get(position);
        mesureViewHolder.tvTemp1.setText(String.valueOf(mesure.getTemp1()));
        mesureViewHolder.tvTemp2.setText(String.valueOf(mesure.getTemp2()));
        mesureViewHolder.tvPressure.setText(String.valueOf(mesure.getPressure()));
        mesureViewHolder.tvLux.setText(String.valueOf(mesure.getLux()));
        mesureViewHolder.tvHydro.setText(String.valueOf(mesure.getHygro()));
        mesureViewHolder.tvWindDir.setText(String.valueOf(mesure.getWindDir()));
        mesureViewHolder.tvWindSpeed.setText(String.valueOf(mesure.getWindSpeed()));
        return convertView;
    }
}
