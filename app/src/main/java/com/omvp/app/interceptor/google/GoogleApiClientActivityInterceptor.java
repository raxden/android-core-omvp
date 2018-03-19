package com.omvp.app.interceptor.google;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

public class GoogleApiClientActivityInterceptor extends ActivityInterceptor<GoogleApiClientInterceptorCallback> implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClientInterceptor {

    private GoogleApiClient.Builder mGoogleApiClientBuilder;
    private GoogleApiClient mGoogleApiClient;

    public GoogleApiClientActivityInterceptor(Activity activity, GoogleApiClient.Builder builder, GoogleApiClientInterceptorCallback callback) {
        super(activity, callback);
        mActivity = activity;
        mGoogleApiClientBuilder = builder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = mGoogleApiClientBuilder
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onStart() {
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient.isConnected() || mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mCallback.onGoogleApiClientConnected(bundle, mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mCallback.onGoogleApiClientConnectionSuspended(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mCallback.onGoogleApiClientConnectionFailed(connectionResult);
    }

}
