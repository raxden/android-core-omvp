package com.omvp.app.ui.samples.sample.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample.presenter.SamplePresenter;

public class SampleFragment extends BaseViewFragment<SamplePresenter, SampleFragment.FragmentCallback> implements SampleView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleFragment newInstance(Bundle bundle) {
        SampleFragment fragment = new SampleFragment();
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
