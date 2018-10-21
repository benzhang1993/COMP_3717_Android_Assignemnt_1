package ca.bcit.ass1.zhang_asai;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Adapter class for continents
 */
public class ContinentsAdapter extends ArrayAdapter<String> {
    Context _context;

    // Constructor
    public ContinentsAdapter(Context context, ArrayList<String> continents) {
        super(context, 0, continents);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;

        // Check if an existing view is being reused, otherwise inflate the view
        // inflate this layout according to list row layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        // Lookup view for data population
        TextView tvRegionName = (TextView) convertView.findViewById(R.id.regionName);
        // Populate the data into the template view using the data object
        tvRegionName.setText(getItem(position));

        // Return the completed view to render on screen
        return convertView;
    }
}

