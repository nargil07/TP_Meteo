package fr.iut_valence.tp_meteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.antony.tp_meteo.R;

import java.util.ArrayList;

import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.list_adapter.MesureAdapter;
import fr.iut_valence.tp_meteo.service.ServiceStation;

public class ListMesureActivity extends AppCompatActivity {

    ListView listViewMesures;
    private ServiceStation serviceStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mesure);
        listViewMesures = (ListView) findViewById(R.id.listViewMesures);
        Station station = (Station) getIntent().getSerializableExtra("station");
        serviceStation = new ServiceStation(this, station);
        listViewMesures.setAdapter(new MesureAdapter(this, new ArrayList<Mesure>(serviceStation.getMesures())));
    }
}
