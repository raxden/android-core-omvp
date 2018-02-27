package com.omvp.app.ui.sample;

import android.os.Bundle;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.sample.view.SampleFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class SampleActivity extends BaseFragmentActivity implements
        SampleFragment.FragmentCallback,
        InjectFragmentInterceptorCallback<SampleFragment> {

    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;

    private SampleFragment mFragment;

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public SampleFragment onCreateFragment() {
        return SampleFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(SampleFragment fragment) {
        mFragment = fragment;
    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mInjectFragmentInterceptor);
    }

}
