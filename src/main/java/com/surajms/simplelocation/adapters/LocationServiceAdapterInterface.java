package com.surajms.simplelocation.adapters;

import android.location.Location;

/**
 * Created by surajshirvankar on 1/13/16.
 */
public interface LocationServiceAdapterInterface {
    void startListening();
    void stopListening();
    Location getLastKnowLocation();
}
