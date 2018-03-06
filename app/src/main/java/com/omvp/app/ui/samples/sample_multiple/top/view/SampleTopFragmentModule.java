package com.omvp.app.ui.samples.sample_multiple.top.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_multiple.top.presenter.SampleTopPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleTopFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SampleTopPresenterModule.class
})
public abstract class SampleTopFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleTopFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SampleTopFragment fragment);

    @Binds
    @PerFragment
    abstract SampleTopView view(SampleTopFragment fragment);

}
