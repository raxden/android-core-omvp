package com.omvp.app.interceptor.location;

import android.Manifest;
import android.app.Activity;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.omvp.app.helper.DialogHelper;
import com.omvp.app.util.PermissionUtil;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LocationActivityInterceptor extends ActivityInterceptor<LocationInterceptorCallback> implements
        LocationListener,
        LocationInterceptor {

    public static final int LOCATE_PERMISSION_FAILED = 1;
    public static final int GOOGLE_API_CLIENT_FAILED = 2;
    public static final int LOCATE_PROVIDER_FAILED = 3;

    private DialogHelper mDialogHelper;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private boolean mRequestingLocationUpdates;
    private boolean mRequestingLocationUpdatesWorking;
    private Disposable mDisposable;
    private CountDownLatch mCountDownLatch;
    private Object o = new Object();
    private Location mLocation;

    public LocationActivityInterceptor(Activity activity, LocationRequest locationRequest, LocationInterceptorCallback callback) {
        super(activity, callback);
        mLocationRequest = locationRequest;
        mDialogHelper = new DialogHelper(mActivity, activity.getFragmentManager());
        mCountDownLatch = new CountDownLatch(1);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResume");
        restoreLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.d("onPause");

        stopLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");

        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        Timber.d("setGoogleApiClient");
        mGoogleApiClient = googleApiClient;
        mCountDownLatch.countDown();
    }

    public void requestLocationUpdates() {
        Disposable d = Completable.create(
                new CompletableOnSubscribe() {
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
                        Timber.d("requestLocationUpdates onError!");
                        mRequestingLocationUpdates = false;
                        Timber.e(e);
                    }
                });
        mDisposable = d;
    }

    @Override
    public Location getLastLocation() {
        return mLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        Timber.d("onLocationChanged " + location.toString());
        mLocation = location;
        mCallback.onLocationChanged(location);
    }

    @SuppressWarnings({"MissingPermission"})
    private void performLocationUpdates() {
        synchronized (o) {
            Timber.d("performLocationUpdates trying to perform...");
            if (!isGoogleApiClientPrepared()) {
                Timber.d("performLocationUpdates - Failed to retrieveUser location updates, GoogleApiClient is not prepared.");
                onLocationError(GOOGLE_API_CLIENT_FAILED);
            } else if (!hasLocationPermission()) {
                Timber.d("performLocationUpdates - Failed to retrieveUser location updates, missing location permission.");
                onLocationError(LOCATE_PERMISSION_FAILED);
            } else if (!hasGPSProviderEnabled()) {
                Timber.d("performLocationUpdates - Failed to retrieveUser location updates, missing location permission.");
                onLocationError(LOCATE_PROVIDER_FAILED);
            } else {
                Timber.d("performLocationUpdates SUCCESS!");
                mRequestingLocationUpdatesWorking = true;
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location != null) {
                    onLocationChanged(location);
                } else {
                    onLocationError(-1);
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
            Timber.d("hasGPSProviderEnabled gps enable...]");
            return true;
        } else {
            Timber.d("hasGPSProviderEnabled gps disabled...");
            //TODO SHOW DIALOG
            Toast.makeText(mContext, "Actualmente tienes el GPS desactivado, activalo para poder obtener resultados de calidad", Toast.LENGTH_LONG).show();

            return false;
        }
    }

    private void onLocationError(int status) {
        mCallback.onLocationError(status);
    }

    private boolean isGoogleApiClientPrepared() {
        return mGoogleApiClient != null && mGoogleApiClient.isConnected();
    }

    private boolean hasLocationPermission() {
        boolean hasLocationPermission = !PermissionUtil.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION);
        Timber.d("hasLocationPermission " + hasLocationPermission);
        return hasLocationPermission;
    }
}
