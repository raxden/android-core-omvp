package com.omvp.app.interceptor.location;

import android.location.Location;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

public interface LocationInterceptorCallback extends InterceptorCallback {

    void onLocationError(int status);

    void onLocationChanged(Location location);
}
