package ca.bcit.ass1.zhang_asai;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountriesAdapter extends ArrayAdapter<Country> {
    Context _context;
    public CountriesAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;

        // Get the data item for this position
        Country country = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        // inflate this layout according to list row layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvCountryName = (TextView) convertView.findViewById(R.id.countryName);
        // Populate the data into the template view using the data object
        tvCountryName.setText(country.getName());

        // Return the completed view to render on screen
        return convertView;
    }
}

