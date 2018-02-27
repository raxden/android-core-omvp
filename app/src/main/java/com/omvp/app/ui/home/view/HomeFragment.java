package com.omvp.app.ui.home.view;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.home.presenter.HomePresenter;

/**
 * Created by Ángel Gómez on 18/02/2018.
 */
public class HomeFragment extends BaseViewFragment<HomePresenter, HomeFragment.FragmentCallback> implements HomeView {

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

}
