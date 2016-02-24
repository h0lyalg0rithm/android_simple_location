package com.surajms.simplelocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

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
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

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

    public void request_permission(Activity activity) {
        if (hasPermission(context)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

//                TODO
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                ActivityCompat.requestPermissions(activity,
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        REQUEST_CODE_ASK_PERMISSIONS);

            }
        }
    }

    static public boolean hasPermission(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
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
