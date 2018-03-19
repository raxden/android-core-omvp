package com.omvp.app.ui.samples.sample_take_picture;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.interceptor.takePicture.TakePictureInterceptor;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class SampleTakePictureActivity extends BaseFragmentActivity implements
        SampleTakePictureFragment.FragmentCallback,
        ToolbarInterceptorCallback,
        InjectFragmentInterceptorCallback<SampleTakePictureFragment> {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;
    @Inject
    TakePictureInterceptor mTakePictureInterceptor;

    private Toolbar mToolbar;
    private SampleTakePictureFragment mFragment;

    // =============== ToolbarInterceptorCallback ==================================================

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void onToolbarViewCreated(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // =============== InjectFragmentInterceptorCallback ===========================================

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public SampleTakePictureFragment onCreateFragment() {
        return SampleTakePictureFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(SampleTakePictureFragment fragment) {
        mFragment = fragment;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mInjectFragmentInterceptor);
        interceptorList.add(mTakePictureInterceptor);
    }

}
