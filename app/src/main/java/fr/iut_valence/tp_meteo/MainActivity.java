package fr.iut_valence.tp_meteo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.antony.tp_meteo.R;

import java.util.ArrayList;

import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.list_adapter.StationAdapter;
import fr.iut_valence.tp_meteo.list_adapter.StationFavorisAdapter;
import fr.iut_valence.tp_meteo.metier.MetierMesure;
import fr.iut_valence.tp_meteo.metier.MetierStation;

public class MainActivity extends AppCompatActivity {

    ListView listview_favoris;
    Button button_list_station;
    MetierStation metierStation;
    MetierMesure metierMesure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.favoris);
        metierStation = new MetierStation(this);
        listview_favoris = (ListView) findViewById(R.id.listview_favoris);
        listview_favoris.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ListMesureActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("station", (Station)parent.getItemAtPosition(position));
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        listview_favoris.setAdapter(new StationFavorisAdapter(new ArrayList<Station>(metierStation.getAllFavoris()), this, metierStation));
        button_list_station = (Button) findViewById(R.id.button_listStation);
        button_list_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inent = new Intent(getApplicationContext(), ListStationActivity.class);
                startActivity(inent);
            }
        });
    }
}
