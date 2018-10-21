package ca.bcit.ass1.zhang_asai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.ShareActionProvider;
import java.util.ArrayList;

/**
 * Activity for Country list
 */
public class CountryListActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String SERVICE_URL = "https://restcountries.eu/rest/v2/all";
    private static String FILTER_HEADER = "?fields=";
    private static String FILTERS = "name;capital;region;population;area;borders;flag";
    private static String REQUEST_URL = SERVICE_URL + FILTER_HEADER + FILTERS;

    private ArrayList<Country> countryList;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        // Retrieve passed data
        countryList = getIntent().getParcelableArrayListExtra("countries");

        CountriesAdapter adapter = new CountriesAdapter(CountryListActivity.this, countryList);

        lv = (ListView) findViewById(R.id.countryList);
        lv.setAdapter(adapter);

        // Listener for each list item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    Country c = countryList.get((int)id);
                    Intent intent = new Intent(CountryListActivity.this, CountryDetailActivity.class);
                    intent.putExtra("index", (int) id);
                    intent.putExtra("country", c);
                    startActivity(intent);
                }
        });
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
}

