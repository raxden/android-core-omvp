package com.omvp.app.ui.sample.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.sample.presenter.SamplePresenter;

/**
 * Created by Ángel Gómez on 18/02/2018.
 */
public class SampleFragment extends BaseViewFragment<SamplePresenter, SampleFragment.FragmentCallback> implements SampleView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleFragment newInstance(Bundle bundle) {
        SampleFragment fragment = new SampleFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

}
