package com.surajms.simplelocation.adapters;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.surajms.simplelocation.SimpleLocation;

/**
 * Created by surajshirvankar on 1/13/16.
 */
public class LocationServiceAdapter implements LocationListener, LocationServiceAdapterInterface {
    private Context context;
    private Location lastLocation;
    private LocationManager locationManager;

    public LocationServiceAdapter(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResourceType")
    @Override
    public void startListening() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(SimpleLocation.hasPermission(context)) {
            lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
    }

    @SuppressWarnings("ResourceType")
    @Override
    public void stopListening() {
        if(SimpleLocation.hasPermission(context))
            locationManager.removeUpdates(this);
    }

    @Override
    public Location getLastKnowLocation() {
        return lastLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
