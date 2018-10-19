package ca.bcit.ass1.zhang_asai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContinentsAdapter extends ArrayAdapter<String> {
    Context _context;
    public ContinentsAdapter(Context context, ArrayList<String> continents) {
        super(context, 0, continents);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
//        Country country = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        // inflate this layout accordig to list row layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }
        // Lookup view for data population
//        TextView tvCountryName = (TextView) convertView.findViewById(R.id.countryName);
        TextView tvRegionName = (TextView) convertView.findViewById(R.id.regionName);
        // Populate the data into the template view using the data object
//        tvCountryName.setText(country.getName());
        tvRegionName.setText(getItem(position));

//        ImageView imgOnePhoto = (ImageView) convertView.findViewById(R.id.thumbImage);
        //DownloadImageTask dit = new DownloadImageTask(_context, imgOnePhoto);
        //dit.execute(toon.getPicture());
//        if (country.getFlag() != null) {
            // ImageDownloaderTask is thread, async
//            new ImageDownloaderTask(imgOnePhoto).execute(country.getFlag());
//        }

        // Return the completed view to render on screen
        return convertView;
    }
}
