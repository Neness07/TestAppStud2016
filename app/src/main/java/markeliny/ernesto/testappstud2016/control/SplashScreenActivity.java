package markeliny.ernesto.testappstud2016.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

import markeliny.ernesto.testappstud2016.R;
import markeliny.ernesto.testappstud2016.database.IDBManager;
import markeliny.ernesto.testappstud2016.database.MySQLiteDatabaseManager;
import markeliny.ernesto.testappstud2016.util.UtilitiesSingleton;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String URL_JSON = "http://54.72.181.8/yolo/contacts.json";

    private UtilitiesSingleton uSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Checking internet connection
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadData().execute();
        } else {
            //Toast de test
            Toast.makeText(SplashScreenActivity.this, "Pas de connexion internet.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private class DownloadData extends AsyncTask<Void, Void, Void> {

        private ProgressDialog pDialog = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SplashScreenActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(URL_JSON);
                IDBManager dbm = new MySQLiteDatabaseManager(SplashScreenActivity.this);
                UtilitiesSingleton.initializeInstance(url,dbm);
                UtilitiesSingleton.getInstance().downloadFromDatabase();
                if(UtilitiesSingleton.getInstance().getRockStarsList() == null
                        || UtilitiesSingleton.getInstance().getRockStarsList().size() == 0){
                    UtilitiesSingleton.getInstance().downloadFromJSON();
                }
            } catch (IOException e) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(SplashScreenActivity.this, "Probleme de téléchargement des données", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (UtilitiesSingleton.getInstance().getRockStarsList() != null) {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(SplashScreenActivity.this, "Un problème est survenu. Accès reseau",
                        Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
