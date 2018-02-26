package com.omvp.app.ui.splash.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.splash.presenter.SplashPresenter;

/**
 * Created by Ángel Gómez on 18/02/2018.
 */
public class SplashFragment extends BaseViewFragment<SplashPresenter, SplashFragment.FragmentCallback> implements SplashView {

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onLaunchApplication();
    }

    public static SplashFragment newInstance(Bundle bundle) {
        SplashFragment fragment = new SplashFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void applicationReadyToLaunch() {
        mCallback.onLaunchApplication();
    }

}
