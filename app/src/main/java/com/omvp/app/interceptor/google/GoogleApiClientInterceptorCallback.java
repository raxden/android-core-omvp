package com.omvp.app.interceptor.google;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.raxdenstudios.square.interceptor.InterceptorCallback;

public interface GoogleApiClientInterceptorCallback extends InterceptorCallback {

    void onGoogleApiClientConnected(Bundle bundle, GoogleApiClient googleApiClient);

    void onGoogleApiClientConnectionSuspended(int i);

    void onGoogleApiClientConnectionFailed(ConnectionResult connectionResult);
}
