package ca.bcit.ass1.zhang_asai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.ahmadrosid.svgloader.SvgLoader;

/**
 * Activity for country detail.
 */
public class CountryDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Country country = (Country) getIntent().getExtras().get("country");

        ImageView photo = findViewById(R.id.countryFlag);

        // Uses SVG Loader to load vector image
        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(country.getFlag(), photo);

        // Set each field datum
        TextView name = findViewById(R.id.countryDetailName);
        name.setText(country.getName());

        TextView capital = findViewById(R.id.countryCapital);
        capital.setText("Capital:" + country.getCapital());

        TextView region = findViewById(R.id.countryRegion);
        region.setText("Region: " + country.getRegion());

        TextView population = findViewById(R.id.countryPopulation);
        population.setText("Population: " + Integer.toString(country.getPopulation()));

        TextView area = findViewById(R.id.countryArea);
        area.setText("Area Code: " + Integer.toString(country.getArea()));

        TextView borders = findViewById(R.id.countryBorders);
        borders.setText("Borders: ");
        for(int i = 0; i < country.getBorders().size(); i++) {
            borders.append(country.getBorders().get(i) + "\n");
        }
    }
}
