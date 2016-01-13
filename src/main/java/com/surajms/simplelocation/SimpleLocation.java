package com.surajms.simplelocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.surajms.simplelocation.adapters.GoogleMapsAdapter;
import com.surajms.simplelocation.adapters.LocationServiceAdapter;
import com.surajms.simplelocation.adapters.LocationServiceAdapterInterface;

import java.util.List;

/**
 * Created by surajshirvankar on 1/13/16.
 */
public class SimpleLocation {
    private Context context;
    LocationServiceAdapterInterface service;


    public SimpleLocation(Context context) {
        this.context = context;
    }
    public void startTracking() {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if( status != ConnectionResult.SUCCESS) {
//            Start google service location service
            service = new GoogleMapsAdapter(context);
            service.startListening();
        }else {
//            TODO
            LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
            List<String> providers = locationManager.getAllProviders();
            if(providers.contains(LocationManager.NETWORK_PROVIDER)){
//                start network location service
            }else if(providers.contains(LocationManager.GPS_PROVIDER)){
//                start gps location service
            }else if(providers.contains(LocationManager.PASSIVE_PROVIDER)){
//                start passive location service
            }
            service = new LocationServiceAdapter(context);
            service.startListening();

        }

    }

    public void setTracker(LocationServiceAdapterInterface service) {
        this.service = service;
    }

    public Location getLastLocation() {
        return service.getLastKnowLocation();
    }

    public void automateTracking(){

    }

    public void stopTracking() {
        service.stopListening();
    }
}
