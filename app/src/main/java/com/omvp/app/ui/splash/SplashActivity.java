package com.omvp.app.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.splash.view.SplashFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class SplashActivity extends BaseFragmentActivity implements
        SplashFragment.FragmentCallback,
        InjectFragmentInterceptorCallback<SplashFragment> {

    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;

    private SplashFragment mSplashFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public SplashFragment onCreateFragment() {
        return SplashFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(SplashFragment fragment) {
        mSplashFragment = fragment;
    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mInjectFragmentInterceptor);
    }

}
