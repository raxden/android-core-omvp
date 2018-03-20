
package com.omvp.app.ui.samples.sample_location.presenter;


import android.location.Location;

import com.google.android.gms.location.LocationListener;
import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.interceptor.location.LocationInterceptor;
import com.omvp.app.ui.samples.sample_location.view.SampleLocationView;

import javax.inject.Inject;

public class SampleLocationPresenterImpl extends BasePresenter<SampleLocationView> implements SampleLocationPresenter {

    @Inject
    LocationInterceptor mLocationInterceptor;

    @Inject
    public SampleLocationPresenterImpl(SampleLocationView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
        mLocationInterceptor.startLocationUpdates();
        mLocationInterceptor.addLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                drawLocation(location);
            }
        });
    }

    private void drawLocation(Location location) {
        if (mView != null) {
            mView.drawLocation(
                    location != null ? String.valueOf(location.getLatitude()) : "",
                    location != null ? String.valueOf(location.getLongitude()) : "");
        }
    }

}
