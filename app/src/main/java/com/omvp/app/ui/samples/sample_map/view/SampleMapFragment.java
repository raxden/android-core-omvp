package com.omvp.app.ui.samples.sample_map.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_map.presenter.SampleMapPresenter;

public class SampleMapFragment extends BaseViewFragment<SampleMapPresenter, SampleMapFragment.FragmentCallback> implements SampleMapView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleMapFragment newInstance(Bundle bundle) {
        SampleMapFragment fragment = new SampleMapFragment();
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
}
