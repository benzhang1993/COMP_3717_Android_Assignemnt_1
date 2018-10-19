package ca.bcit.ass1.zhang_asai;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL = "https://restcountries.eu/rest/v2/all";
    private static String FILTER_HEADER = "?fields=";
    private static String FILTERS = "name;capital;region;population;area;borders;flag";
    private static String REQUEST_URL = SERVICE_URL + FILTER_HEADER + FILTERS;
    private ArrayList<Country> countryList;

    // test commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryList = new ArrayList<Country>();
        lv = (ListView) findViewById(R.id.countryList);
        new GetContacts().execute();
    }
    /**
     * Async task class to get json by making HTTP call
     */
    // Array of empty Void
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(REQUEST_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        JSONObject c = countryJsonArray.getJSONObject(i);
                        JSONArray a = c.getJSONArray("borders");

                        String flagURL = c.getString("flag");
                        String countryName = c.getString("name");
                        String regionName = c.getString("region");
                        String capital = c.getString("capital");
                        int population = c.getInt("population");
                        int area = c.getInt("area");
                        ArrayList<String> borders = new ArrayList<String>();

                        for (int j = 0; j < a.length(); j++) {
                            borders.add(a.getString(j));
                        }

                        // tmp hash map for single contact
                        Country country = new Country();

                        // adding each child node to HashMap key => value
                        country.setFlag(flagURL);
                        country.setName(countryName);
                        country.setRegion(regionName);
                        country.setCapital(capital);
                        country.setPopulation(population);
                        country.setArea(area);
                        country.setBorders(borders);

                        // adding contact to contact list
                        countryList.add(country);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            //Toon[] toonArray = toonList.toArray(new Toon[toonList.size()]);

            CountriesAdapter adapter = new CountriesAdapter(MainActivity.this, countryList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
}
