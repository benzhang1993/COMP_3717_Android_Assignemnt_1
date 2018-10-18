package ca.bcit.ass1.zhang_asai;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL = "https://restcountries.eu/rest/v2/all";
//    private static String FILTERS = "?fields=region;name;capital;population;area;borders;flag";
    private static String FILTER_HEADER = "?fields=";
    private static String FILTERS = "name;capital;region;population;area;borders;flag";
    private static String REQUEST_URL = SERVICE_URL + FILTER_HEADER + FILTERS;
//    private ArrayList<Toon> toonList;

    // test commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
