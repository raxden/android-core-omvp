package com.omvp.app.ui.samples.sample_list.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_list.presenter.SampleListPresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleListFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SampleListPresenterModule.class
})
public abstract class SampleListFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleListFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SampleListFragment fragment);

    @Binds
    @PerFragment
    abstract SampleListView view(SampleListFragment fragment);

}
