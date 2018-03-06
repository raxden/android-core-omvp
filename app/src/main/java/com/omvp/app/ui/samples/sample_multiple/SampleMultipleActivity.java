package com.omvp.app.ui.samples.sample_multiple;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.samples.sample_multiple.bottom.view.SampleBottomFragment;
import com.omvp.app.ui.samples.sample_multiple.top.view.SampleTopFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class SampleMultipleActivity extends BaseFragmentActivity implements
        SampleTopFragment.FragmentCallback,
        SampleBottomFragment.FragmentCallback,
        ToolbarInterceptorCallback,
        InjectFragmentListInterceptorCallback<Fragment> {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    InjectFragmentListInterceptor mInjectFragmentListInterceptor;

    private Toolbar mToolbar;
    private SampleTopFragment mTopFragment;
    private SampleBottomFragment mBottomFragment;


    // =============== ToolbarInterceptorCallback ==================================================

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void onToolbarViewCreated(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // =============== SampleTopFragment.FragmentCallback ==========================================


    // =============== InjectFragmentsInterceptorCallback ===========================================

    @Override
    public int getFragmentCount() {
        return 2;
    }

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState, int position) {
        switch (position) {
            case 0:
                return findViewById(R.id.top_content);
            case 1:
                return findViewById(R.id.bottom_content);
        }
        return null;
    }

    @Override
    public Fragment onCreateFragment(int position) {
        switch (position) {
            case 0:
                return SampleTopFragment.newInstance(getIntent().getExtras());
            case 1:
                return SampleBottomFragment.newInstance(getIntent().getExtras());
        }
        return null;
    }

    @Override
    public void onFragmentLoaded(Fragment fragment, int position) {
        switch (position) {
            case 0:
                mTopFragment = (SampleTopFragment) fragment;
                break;
            case 1:
                mBottomFragment = (SampleBottomFragment) fragment;
                break;
        }
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mInjectFragmentListInterceptor);
    }

}
