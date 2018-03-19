package com.omvp.app.interceptor.location;

import android.location.Location;

import com.google.android.gms.location.LocationListener;
import com.raxdenstudios.square.interceptor.Interceptor;

public interface LocationInterceptor extends Interceptor {

    enum LocationError {LOCATE_PERMISSION_FAILED, GOOGLE_API_CLIENT_FAILED, LOCATE_PROVIDER_FAILED}

    Location getLastLocation();

    void requestLocationUpdates();

    void addLocationListener(LocationListener locationListener);

}
