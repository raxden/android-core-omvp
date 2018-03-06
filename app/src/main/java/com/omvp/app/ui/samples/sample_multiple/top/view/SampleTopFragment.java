package com.omvp.app.ui.samples.sample_multiple.top.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_multiple.top.presenter.SampleTopPresenter;

public class SampleTopFragment extends BaseViewFragment<SampleTopPresenter, SampleTopFragment.FragmentCallback> implements SampleTopView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleTopFragment newInstance(Bundle bundle) {
        SampleTopFragment fragment = new SampleTopFragment();
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
