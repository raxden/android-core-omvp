
package com.omvp.app.ui.samples.sample_location.presenter;


import android.location.Location;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.sample_location.view.SampleLocationView;

import javax.inject.Inject;

public class SampleLocationPresenterImpl extends BasePresenter<SampleLocationView> implements SampleLocationPresenter {

    private Location mCurrentLocation;

    @Inject
    public SampleLocationPresenterImpl(SampleLocationView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
    }

    @Override
    public void locationChanged(Location location) {
        mCurrentLocation = location;
        if (mCurrentLocation != null) {
            getLocationData ();
        }
    }

    private void getLocationData () {
        mView.drawLocation (String.valueOf(mCurrentLocation.getLatitude()), String.valueOf(mCurrentLocation.getLongitude()));
    }
}
