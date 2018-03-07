package com.omvp.app.ui.samples.sample_location.presenter;

import android.location.Location;

import com.omvp.app.base.mvp.presenter.Presenter;

public interface SampleLocationPresenter extends Presenter {

    void locationChanged(Location location);

}
