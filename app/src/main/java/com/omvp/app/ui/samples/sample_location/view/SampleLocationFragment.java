package com.omvp.app.ui.samples.sample_location.view;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_location.presenter.SampleLocationPresenter;

import butterknife.BindView;

public class SampleLocationFragment extends BaseViewFragment<SampleLocationPresenter, SampleLocationFragment.FragmentCallback> implements SampleLocationView {

    @BindView(R.id.longitude_value)
    AppCompatTextView mLongitude;

    @BindView(R.id.latitude_value)
    AppCompatTextView mLatitude;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleLocationFragment newInstance(Bundle bundle) {
        SampleLocationFragment fragment = new SampleLocationFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    private void setupViews() {

    }

    public void locationChanged(Location location) {
        mPresenter.locationChanged(location);
    }

    @Override
    public void drawLocation(String latitude, String longitude) {
        mLongitude.setText(longitude);
        mLatitude.setText(latitude);
    }
}
