package com.omvp.app.ui.home.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.home.presenter.HomePresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        HomePresenterModule.class
})
public abstract class HomeFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the HomeFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(HomeFragment fragment);

    @Binds
    @PerFragment
    abstract HomeView view(HomeFragment fragment);

}
