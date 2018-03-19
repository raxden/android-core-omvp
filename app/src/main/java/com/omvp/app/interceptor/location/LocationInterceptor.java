package com.omvp.app.interceptor.location;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.raxdenstudios.square.interceptor.Interceptor;

public interface LocationInterceptor extends Interceptor {

    Location getLastLocation();

    void setGoogleApiClient(GoogleApiClient googleApiClient);

    void requestLocationUpdates();

}
