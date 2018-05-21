package com.example.android.quakereport.support;

/**
 * Created by nomot on 20/05/2018.
 */

public class EarthQuake {

    // LOG_TAG
    private static String LOG_TAG = EarthQuake.class.getSimpleName();

    // Initializing Global Variables
    private double mMagnitude;
    private String mLocation;
    private long mTimeMilis;
    private String mUrl;

    public EarthQuake(double magnitude, String location, long timeMilis, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeMilis = timeMilis;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeMilis() {
        return mTimeMilis;
    }


    public boolean hasPlace() {
        return getLocation() != null;
    }

    public boolean hasDate() {
        return getTimeMilis() > 0;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        String output =
                        getMagnitude() + "\n" +
                        getLocation() + "\n" +
                                getTimeMilis() + "\n";

        return output;
    }
}
