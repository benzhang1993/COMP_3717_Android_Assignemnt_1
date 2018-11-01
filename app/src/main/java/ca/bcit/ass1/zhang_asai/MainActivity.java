package ca.bcit.ass1.zhang_asai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.ShareActionProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Main activity
 */
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
    private Set<String> regionList = new HashSet<String>();
    private ArrayList<String> regionArrayList;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView student1 = findViewById(R.id.student1);
        TextView student2 = findViewById(R.id.student2);
        countryList = new ArrayList<Country>();
        lv = (ListView) findViewById(R.id.continentsList);

        new GetContacts().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareActionIntent("Sending location data.");
        return true;
    }

    // Call to update the share intent
    private void setShareActionIntent(String text) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, text);
        mShareActionProvider.setShareIntent(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mydevice:
                Intent i = new Intent(this, MyDeviceInfoActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    /**
     * Async task class to get json by making HTTP call
     */
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

            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    // looping through All Countries
                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        JSONObject c = countryJsonArray.getJSONObject(i);

                        // Get country attributes
                        JSONArray a = c.getJSONArray("borders");
                        String flagURL = c.getString("flag");
                        String countryName = c.getString("name");
                        String regionName = c.getString("region");
                        String capital = c.getString("capital");
                        int population = c.getInt("population");

                        // If a JSON datum does not have area field, skip it
                        int area = 0;
                        if (c.has("area")) {
                            area = c.getInt("area");
                        }

                        // Retrieve borders
                        ArrayList<String> borders = new ArrayList<String>();
                        for (int j = 0; j < a.length(); j++) {
                            borders.add(a.getString(j));
                        }

                        Country country = new Country();

                        // Set attributes to a country instance
                        country.setFlag(flagURL);
                        country.setName(countryName);
                        country.setRegion(regionName);
                        country.setCapital(capital);
                        country.setPopulation(population);
                        country.setArea(area);
                        country.setBorders(borders);

                        // Store countries to each list
                        countryList.add(country);
                        regionList.add(regionName);
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
            regionList.remove("");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            // Create adapter for continents. Sort regionList in ascending order before it.
            regionArrayList = new ArrayList<String>(regionList);
            Collections.sort(regionArrayList, String.CASE_INSENSITIVE_ORDER);
            ContinentsAdapter adapter = new ContinentsAdapter(MainActivity.this, regionArrayList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    String continentName = regionArrayList.get((int) id);

                    // Retrieve countries exist in the chosen continent
                    ArrayList<Country> countriesInContinent = new ArrayList<Country>();
                    for(Country country : countryList) {
                        if (continentName.equals(country.getRegion())) {
                            countriesInContinent.add(country);
                        }
                    }

                    Intent intent = new Intent(MainActivity.this, CountryListActivity.class);
                    intent.putExtra("index", (int) id);
                    intent.putExtra("countries", countriesInContinent);

                    startActivity(intent);
                }
            });
        }
    }
}
