package ca.bcit.ass1.zhang_asai;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        int countryIndex = (Integer) getIntent().getExtras().get("index");
        Country country = (Country) getIntent().getExtras().get("country");
        Log.d("test", country.getName());
        Log.d("test", country.getRegion());

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
    }
}
