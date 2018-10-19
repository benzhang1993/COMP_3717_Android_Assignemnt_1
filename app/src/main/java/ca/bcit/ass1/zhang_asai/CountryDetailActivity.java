package ca.bcit.ass1.zhang_asai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


//import com.bumptech.glide.Glide;

public class CountryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        int countryIndex = (Integer) getIntent().getExtras().get("index");
//        Country country = (Country) getIntent().getExtras().get("country");

        // Populate the country image
//        ImageView photo = findViewById(R.id.photo);
//        photo.setImageResource(food.getImageResourceId());
//                photo.setContentDescription(food.getDescription());

        // Populate the country name
//        TextView name = findViewById(R.id.countryName);
//        name.setText(country.getName());
//
//        TextView category = findViewById(R.id.category);
//        String categoryText = "Category: " + food.getCategory();
//        category.setText(categoryText);
//
//        // populate the country description
//        TextView origin = findViewById(R.id.countryOfOrigin);
//        String originText = "Origin: " + food.getCountryOfOrigin();
//        origin.setText(originText);
//
//        TextView unit = findViewById(R.id.unit);
//        String unitText = "Unit: " + food.getUnit();
//        unit.setText(unitText);
//
//        TextView price = findViewById(R.id.price);
//        String priceText = "Price: " + food.getPrice();
//        price.setText(priceText);
        Country country = (Country) getIntent().getExtras().get("country");


        ImageView photo = findViewById(R.id.countryFlag);
//        ImageDownloaderTask task = new ImageDownloaderTask(photo);
//        task.execute(country.getFlag());

//        Glide.with(this).load(country.getFlag()).into(photo);


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
