package com.omvp.app.ui.samples.sample_location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.interceptor.google.GoogleApiClientInterceptor;
import com.omvp.app.interceptor.google.GoogleApiClientInterceptorCallback;
import com.omvp.app.interceptor.location.LocationInterceptor;
import com.omvp.app.interceptor.location.LocationInterceptorCallback;
import com.omvp.app.interceptor.permission.PermissionActivityInterceptor;
import com.omvp.app.interceptor.permission.PermissionInterceptor;
import com.omvp.app.interceptor.permission.PermissionInterceptorCallback;
import com.omvp.app.ui.samples.sample_location.view.SampleLocationFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class SampleLocationActivity extends BaseFragmentActivity implements
        SampleLocationFragment.FragmentCallback,
        ToolbarInterceptorCallback,
        LocationInterceptorCallback,
        PermissionInterceptorCallback,
        GoogleApiClientInterceptorCallback,
        InjectFragmentInterceptorCallback<SampleLocationFragment> {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;
    @Inject
    LocationInterceptor mLocationInterceptor;
    @Inject
    PermissionInterceptor mPermissionInterceptor;
    @Inject
    protected GoogleApiClientInterceptor mGoogleApiClientInterceptor;

    private Toolbar mToolbar;
    private SampleLocationFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestLocationPermission();
    }


    // =============== ToolbarInterceptorCallback ==================================================

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void onToolbarViewCreated(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // =============== InjectFragmentInterceptorCallback ===========================================

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public SampleLocationFragment onCreateFragment() {
        return SampleLocationFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(SampleLocationFragment fragment) {
        mFragment = fragment;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mInjectFragmentInterceptor);
        interceptorList.add(mLocationInterceptor);
        interceptorList.add(mPermissionInterceptor);
        interceptorList.add(mGoogleApiClientInterceptor);
    }


    // =============== LocationInterceptorCallback =================================================


    @Override
    public void onLocationError(int status) {
        Timber.d("onLocationError %d", status);
        locationChanged(null);
    }

    @Override
    public void onLocationChanged(Location location) {
        Timber.d("onLocationChanged %s", location.toString());
        locationChanged(location);
    }


//    // ========= GoogleApiClientInterceptorCallback ================================================

    @Override
    public void onGoogleApiClientConnected(Bundle bundle, GoogleApiClient googleApiClient) {
        mLocationInterceptor.setGoogleApiClient(googleApiClient);
    }

    @Override
    public void onGoogleApiClientConnectionSuspended(int i) {
        Timber.d("onGoogleApiClientConnectionSuspended %d", i);
    }

    @Override
    public void onGoogleApiClientConnectionFailed(ConnectionResult connectionResult) {
        Timber.d("onGoogleApiClientConnectionFailed %s", connectionResult != null ? connectionResult.toString() : "");
    }

    // =============== PermissionInterceptorCallback ===============================================

    protected void requestLocationPermission() {
        mPermissionInterceptor.requestPermission(PermissionActivityInterceptor.Permission.LOCATION);
    }

    protected boolean hasLocationPermission() {
        return mPermissionInterceptor.hasPermission(PermissionActivityInterceptor.Permission.LOCATION);
    }

    @Override
    public void onPermissionGranted(PermissionActivityInterceptor.Permission permission) {
        Timber.d("onPermissionGranted %s", permission != null ? permission.toString() : "");
        mLocationInterceptor.requestLocationUpdates();
    }

    @Override
    public void onPermissionAlreadyGranted(PermissionActivityInterceptor.Permission permission) {
        Timber.d("onPermissionAlreadyGranted %s", permission != null ? permission.toString() : "");
        mLocationInterceptor.requestLocationUpdates();
    }

    @Override
    public void onPermissionDenied(PermissionActivityInterceptor.Permission permission) {
        Timber.d("onPermissionDenied %s", permission != null ? permission.toString() : "");
    }

    @Override
    public void onPermissionDeniedForEver(PermissionActivityInterceptor.Permission permission) {
        Timber.d("onPermissionDeniedForEver %s", permission != null ? permission.toString() : "");
    }


    private void locationChanged(Location location) {
        mFragment.locationChanged(location);
    }
}
