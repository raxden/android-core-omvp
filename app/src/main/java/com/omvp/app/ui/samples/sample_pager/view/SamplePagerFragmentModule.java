package com.omvp.app.ui.samples.sample_pager.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_pager.presenter.SamplePagerPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SamplePagerFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SamplePagerPresenterModule.class
})
public abstract class SamplePagerFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SamplePagerFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SamplePagerFragment fragment);

    @Binds
    @PerFragment
    abstract SamplePagerView view(SamplePagerFragment fragment);

}
