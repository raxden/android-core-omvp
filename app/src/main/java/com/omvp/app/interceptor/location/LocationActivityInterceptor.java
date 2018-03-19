package com.omvp.app.interceptor.location;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.omvp.app.R;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.interceptor.ActivitySimpleInterceptor;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LocationActivityInterceptor extends ActivitySimpleInterceptor implements LocationInterceptor {

    private final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    private final static String KEY_LOCATION = "location";
    private final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;
    /**
     * Provides access to the Location Settings API.
     */
    private SettingsClient mSettingsClient;
    /**
     * Callback for Location events.
     */
    private LocationCallback mLocationCallback;
    /**
     * Represents a geographical location.
     */
    private Location mCurrentLocation;
    /**
     * Stores the types of location services the client is interested in using. Used for checking
     * settings to determine if the device has optimal location settings.
     */
    private LocationSettingsRequest mLocationSettingsRequest;

    // ====================



    private LocationRequest mLocationRequest;
    private boolean mRequestingLocationUpdates;
    private boolean mRequestingLocationUpdatesWorking;
    private CompositeDisposable mCompositeDisposable;
    private CountDownLatch mCountDownLatch;
    private Object o = new Object();
    private Location mLocation;
    private List<LocationListener> mLocationListenerList;
    private RxPermissions mRxPermissions;
    private boolean mHasLocationPermission;

    public LocationActivityInterceptor(Activity activity, LocationRequest locationRequest) {
        super(activity);
        mLocationRequest = locationRequest;

        mCountDownLatch = new CountDownLatch(1);
        mRxPermissions = new RxPermissions(activity);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restoreDataFromBundle(savedInstanceState);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mActivity);
        mSettingsClient = LocationServices.getSettingsClient(mActivity);

        createLocationCallback();
        buildLocationSettingsRequest();
//        requestLocationPermissions();
    }

    @Override
    public void onResume() {
        super.onResume();
        restoreLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationListenerList != null) {
            mLocationListenerList.clear();
            mLocationListenerList = null;
        }
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    // =============================================================================================

    @Override
    public void addLocationListener(LocationListener locationListener) {
        if (mLocationListenerList == null) {
            mLocationListenerList = new ArrayList<>();
        }
        mLocationListenerList.add(locationListener);
    }

    private void restoreDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from the Bundle, and make sure that
            // the Start Updates and Stop Updates buttons are correctly enabled or disabled.
            if (savedInstanceState.keySet().contains(KEY_REQUESTING_LOCATION_UPDATES)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(KEY_REQUESTING_LOCATION_UPDATES);
            }
            // Update the value of mCurrentLocation from the Bundle and update the UI to show the
            // correct latitude and longitude.
            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {
                // Since KEY_LOCATION was found in the Bundle, we can be sure that mCurrentLocation
                // is not null.
                mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            }
        }
    }

    /**
     * Creates a callback for receiving location events.
     */
    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mCurrentLocation = locationResult.getLastLocation();
                onLocationChanged(mCurrentLocation);
            }
        };
    }

    /**
     * Uses a {@link com.google.android.gms.location.LocationSettingsRequest.Builder} to build
     * a {@link com.google.android.gms.location.LocationSettingsRequest} that is used for checking
     * if a device has the needed location settings.
     */
    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void onLocationChanged(Location location) {
        if (mLocationListenerList != null) {
            for (LocationListener locationListener : mLocationListenerList) {
                locationListener.onLocationChanged(location);
            }
        }
    }





    @Override
    public void requestLocationUpdates() {
        mCompositeDisposable.add(Completable
                .create(new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                        try {
                            if (!emitter.isDisposed()) {
                                Timber.d("requestLocationUpdates await...");
                                if (isGoogleApiClientPrepared()) {
                                    mCountDownLatch.countDown();
                                }
                                mCountDownLatch.await();
                                Timber.d("requestLocationUpdates restored!");
                                emitter.onComplete();
                            }
                        } catch (Exception ex) {
                            emitter.onError(ex);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    protected void onStart() {
                        Timber.d("requestLocationUpdates onStart!");
                        mRequestingLocationUpdates = true;
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("requestLocationUpdates onComplete!");
                        performLocationUpdates();
                        mRequestingLocationUpdates = false;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                        mRequestingLocationUpdates = false;
                    }
                }));
    }

    @Override
    public Location getLastLocation() {
        return mLocation;
    }

    @SuppressWarnings({"MissingPermission"})
    private void performLocationUpdates() {
        synchronized (o) {
            Timber.d("performLocationUpdates trying to perform...");
            if (!isGoogleApiClientPrepared()) {
                Timber.e("performLocationUpdates - Failed to retrieveUser location updates, GoogleApiClient is not prepared.");
            } else if (!mHasLocationPermission) {
                Timber.e("performLocationUpdates - Failed to retrieveUser location updates, missing location permission.");
            } else if (!hasGPSProviderEnabled()) {
                Timber.e("performLocationUpdates - Failed to retrieveUser location updates, missing location permission.");
            } else {
                Timber.d("performLocationUpdates SUCCESS!");
                mRequestingLocationUpdatesWorking = true;


                FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(mActivity);
                client.requestLocationUpdates(mLocationRequest, )


                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location != null) {
                    onLocationChanged(location);
                }
            }
        }
    }

    private void restoreLocationUpdates() {
        synchronized (o) {
            Timber.d("restoreLocationUpdates");
            if (!mRequestingLocationUpdates && !mRequestingLocationUpdatesWorking) {
                requestLocationUpdates();
            }
        }
    }

    private void stopLocationUpdates() {
        synchronized (o) {
            Timber.d("stopLocationUpdates");
            if (isGoogleApiClientPrepared()) {
                removeLocationUpdates();
            }
        }
    }

    private void removeLocationUpdates() {
        synchronized (o) {
            Timber.d("removeLocationUpdates");
            mRequestingLocationUpdates = false;
            mRequestingLocationUpdatesWorking = false;
            mCountDownLatch = new CountDownLatch(1);
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    private boolean hasGPSProviderEnabled() {
        if (Utils.hasGPSProviderEnabled(mContext)) {
            return true;
        } else {
            new AlertDialog.Builder(mActivity)
                    .setTitle(R.string.gps_provider_service_title)
                    .setMessage(R.string.gps_provider_service_description)
                    .setPositiveButton(R.string.gps_provider_service_positive_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            mActivity.startActivity(gpsOptionsIntent);
                        }
                    })
                    .create()
                    .show();
            return false;
        }
    }

    private void requestLocationPermissions() {
        mCompositeDisposable.add(mRxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Permission>() {
                    @Override
                    public void onNext(Permission permission) {
                        mHasLocationPermission = permission.granted;
                        if (permission.shouldShowRequestPermissionRationale) {
                            new AlertDialog.Builder(mActivity)
                                    .setTitle(R.string.location_permission_title)
                                    .setMessage(R.string.location_permission_description)
                                    .setPositiveButton(R.string.location_permission_positive_button, null)
                                    .create()
                                    .show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

}
