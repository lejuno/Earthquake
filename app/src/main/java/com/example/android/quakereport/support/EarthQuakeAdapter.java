package com.example.android.quakereport.support;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.graphics.drawable.GradientDrawable;

import org.w3c.dom.Text;

/**
 * Created by nomot on 20/05/2018.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    private static final String LOCATION_SEPARATOR = " of ";


    public EarthQuakeAdapter(Context context, List<EarthQuake> earthquakelist) {
        super(context, 0, earthquakelist);
    }


    /**
     * getView()
     * Build each suggestion as a card view.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final EarthQuake currentItem = getItem(position);
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Magnitude
        DecimalFormat formatter = new DecimalFormat("0.0");
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.tv_magnitude);
        magnitudeTextView.setText(formatter.format(currentItem.getMagnitude()));
        // Configure a cor de fundo apropriada no círculo de magnitude.
        // Busque o fundo do TextView, que é um GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        // Obtenha a cor de fundo apropriada, baseada na magnitude do terremoto atual
        int magnitudeColor = getMagnitudeColor(currentItem.getMagnitude());
        // Configure a cor no círculo de magnitude
        magnitudeCircle.setColor(magnitudeColor);


        // Location
        String originalLocation = currentItem.getLocation();
        String primaryLocation;
        String locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView locationTextView = (TextView)
                listItemView.findViewById(R.id.tv_location_offset);
        locationTextView.setText(locationOffset);

        // Primary Location
        TextView primaryLocationTextView = (TextView)
                listItemView.findViewById(R.id.tv_primarylocation);
        primaryLocationTextView.setText(primaryLocation);

        // Date and Time
        Date dateObject;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD,yyyy", Locale.ENGLISH);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        dateObject = new Date(currentItem.getTimeMilis());
        String dateToDisplay = dateFormat.format(dateObject);
        String timeToDisplay = timeFormat.format(dateObject);
        TextView dateTextView = (TextView)
                listItemView.findViewById(R.id.tv_date);
        dateTextView.setText(dateToDisplay);
        TextView timeTextView = (TextView)
                listItemView.findViewById(R.id.tv_time);
        timeTextView.setText(timeToDisplay);

        // Return list item
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
