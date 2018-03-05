package com.omvp.app.ui.samples.sample_pager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;

import java.util.List;

import javax.inject.Inject;

public class SamplePagerActivity extends BaseFragmentActivity implements
        FragmentStatePagerInterceptorCallback<SamplePagerFragment> {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    FragmentStatePagerInterceptor mFragmentStatePagerInterceptor;

    private ViewPager mViewPager;
    private SamplePagerFragment mFragment;

    // =============== InjectFragmentStatePagerInterceptorCallback ===========================================

    @Override
    public ViewPager onCreateViewPager(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public void onViewPagerCreated(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    @Override
    public SamplePagerFragment onCreateFragment(int position) {
        switch (position){

        }
        return null;
    }

    @Override
    public void onFragmentLoaded(SamplePagerFragment fragment, int position) {
        switch (position){

        }
    }

    @Override
    public void onFragmentSelected(SamplePagerFragment fragment, int position) {

    }

    @Override
    public int getViewPagerElements() {
        return 1;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mFragmentStatePagerInterceptor);
    }

}
