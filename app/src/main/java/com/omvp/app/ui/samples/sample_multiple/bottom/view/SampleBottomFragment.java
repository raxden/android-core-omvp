package com.omvp.app.ui.samples.sample_multiple.bottom.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_multiple.bottom.presenter.SampleBottomPresenter;

public class SampleBottomFragment extends BaseViewFragment<SampleBottomPresenter, SampleBottomFragment.FragmentCallback> implements SampleBottomView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleBottomFragment newInstance(Bundle bundle) {
        SampleBottomFragment fragment = new SampleBottomFragment();
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
