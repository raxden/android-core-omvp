package com.omvp.app.ui.samples.sample_map;

import android.os.Bundle;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.samples.sample_map.view.SampleMapFragment;
import com.omvp.app.ui.splash.view.SplashFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class SampleMapActivity extends BaseFragmentActivity implements
        SampleMapFragment.FragmentCallback,
        InjectFragmentInterceptorCallback<SampleMapFragment> {

    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;

    private SampleMapFragment mFragment;

    // =============== SampleMapFragment.FragmentCallback =============================================


    // =============== InjectFragmentInterceptorCallback ===========================================

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public SampleMapFragment onCreateFragment() {
        return SampleMapFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(SampleMapFragment fragment) {
        mFragment = fragment;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mInjectFragmentInterceptor);
    }

}
