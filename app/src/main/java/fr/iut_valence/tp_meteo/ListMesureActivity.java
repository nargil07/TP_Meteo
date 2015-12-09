package fr.iut_valence.tp_meteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.antony.tp_meteo.R;

import fr.iut_valence.tp_meteo.list_adapter.MesureAdapter;
import fr.iut_valence.tp_meteo.metier.Station;

public class ListMesureActivity extends AppCompatActivity {

    ListView listViewMesures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mesure);
        listViewMesures = (ListView) findViewById(R.id.listViewMesures);
        Station station = (Station) getIntent().getSerializableExtra("station");
    }
}
