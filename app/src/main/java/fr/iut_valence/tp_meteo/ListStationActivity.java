package fr.iut_valence.tp_meteo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.antony.tp_meteo.R;

import fr.iut_valence.tp_meteo.grid_adapter.StationGridAdapter;
import fr.iut_valence.tp_meteo.list_adapter.StationAdapter;
import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.metier.MetierStation;

import java.util.ArrayList;

public class ListStationActivity extends AppCompatActivity {

    ListView listView_station;
    GridView gridView_station;
    ArrayList<Station> list_station;
    private MetierStation metierStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_station);
        metierStation = new MetierStation(this);
        new StationDownloadTask().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.change_view:

                if(listView_station.getVisibility() == View.VISIBLE){
                    item.setIcon(getResources().getDrawable(R.drawable.ic_view_list_white_48dp));
                    listView_station.setVisibility(View.INVISIBLE);
                    gridView_station.setVisibility(View.VISIBLE);
                }else{
                    item.setIcon(getResources().getDrawable(R.drawable.ic_apps_white_48dp));
                    listView_station.setVisibility(View.VISIBLE);
                    gridView_station.setVisibility(View.INVISIBLE);
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private class StationDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            gridView_station = (GridView) findViewById(R.id.gridView_station);
            gridView_station.setAdapter(new StationGridAdapter(list_station, getApplicationContext()));
            listView_station = (ListView) findViewById(R.id.listView_station);
            listView_station.setAdapter(new StationAdapter(list_station, getApplicationContext(), metierStation));
        }

        @Override
        protected Void doInBackground(Void... params) {
            list_station = new ArrayList<>(metierStation.getAll());
            return null;
        }
    }
}
