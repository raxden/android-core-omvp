package com.omvp.app.ui.samples.sample_multiple.bottom.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_multiple.bottom.presenter.SampleBottomPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleBottomFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SampleBottomPresenterModule.class
})
public abstract class SampleBottomFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleBottomFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SampleBottomFragment fragment);

    @Binds
    @PerFragment
    abstract SampleBottomView view(SampleBottomFragment fragment);

}
