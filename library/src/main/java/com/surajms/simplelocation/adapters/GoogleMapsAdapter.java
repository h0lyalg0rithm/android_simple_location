package com.surajms.simplelocation.adapters;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.surajms.simplelocation.SimpleLocation;


/**
 * Created by surajshirvankar on 1/13/16.
 */
public class GoogleMapsAdapter implements GoogleApiClient.ConnectionCallbacks,
                                          GoogleApiClient.OnConnectionFailedListener,
                                          LocationListener,
                                          LocationServiceAdapterInterface {
    private Context context;
    GoogleApiClient googleApiClient;
    Location lastLocation;


    public GoogleMapsAdapter(Context context) {
        this.context = context;
        this.googleApiClient = createGoogleClient();
    }

//    GoogleApiClient

    @SuppressWarnings("ResourceType")
    @Override
    public void onConnected(Bundle bundle) {
        if(SimpleLocation.hasPermission(context)) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, createLocationRequest(), this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

//    EndGoogleApiClient

    private GoogleApiClient createGoogleClient() {
        return  googleApiClient = new GoogleApiClient.Builder(context)
                                    .addConnectionCallbacks(this)
                                    .addOnConnectionFailedListener(this)
                                    .addApi(LocationServices.API)
                                    .build();
    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

//    LocationServiceAdapterInterface

    @Override
    public void startListening() {
        googleApiClient.connect();
    }

    @Override
    public void stopListening() {
        if(googleApiClient.isConnected())
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }

    @Nullable
    @Override
    public Location getLastKnowLocation() {
        return lastLocation;
    }
    //    LocationServiceAdapterInterface
}
