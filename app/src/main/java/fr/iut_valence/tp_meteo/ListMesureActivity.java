package fr.iut_valence.tp_meteo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antony.tp_meteo.R;

import java.util.ArrayList;
import java.util.List;

import fr.iut_valence.tp_meteo.entity.Mesure;
import fr.iut_valence.tp_meteo.entity.Station;
import fr.iut_valence.tp_meteo.list_adapter.MesureAdapter;
import fr.iut_valence.tp_meteo.service.ServiceStation;

public class ListMesureActivity extends AppCompatActivity {

    ListView listViewMesures;
    TextView tvId;
    TextView tvLibelle;
    TextView tvLattitude;
    TextView tvLongitude;
    TextView tvAltitude;
    private List<Mesure> mesureList;

    private ServiceStation serviceStation;
    private Station station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mesure);
        listViewMesures = (ListView) findViewById(R.id.listViewMesures);
        tvId = (TextView) findViewById(R.id.textViewIdStation);
        tvLibelle = (TextView) findViewById(R.id.textViewLibelleStation);
        tvLattitude = (TextView) findViewById(R.id.textViewLattitude);
        tvLongitude = (TextView) findViewById(R.id.textViewLongitude);
        tvAltitude = (TextView) findViewById(R.id.textViewAltitude);
        station = (Station) getIntent().getSerializableExtra("station");
        tvId.setText(station.getIdentifiant());
        tvLibelle.setText(station.getLibelle());
        new AsyncMesures().execute();


    }

    private class AsyncMesures extends AsyncTask<Void, Void, Void>{
        private ProgressDialog progressDialog = new ProgressDialog(ListMesureActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Téléchargement en cours ...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    AsyncMesures.this.cancel(true);
                }
            });
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listViewMesures.setAdapter(new MesureAdapter(getApplicationContext(), (ArrayList<Mesure>) mesureList));

            tvLattitude.setText(station.getLatitude());
            tvLongitude.setText(station.getLongitude());
            tvAltitude.setText(station.getAltitude());
            progressDialog.cancel();
        }

        @Override
        protected Void doInBackground(Void... params) {
            serviceStation = new ServiceStation(getApplicationContext(), station);
            station = serviceStation.getStation();
            mesureList = new ArrayList<Mesure>(serviceStation.getLast());
            return null;
        }
    }
}
