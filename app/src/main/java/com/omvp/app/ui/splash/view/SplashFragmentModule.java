package com.omvp.app.ui.splash.view;

import android.app.Fragment;

import com.omvp.app.base.view.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SplashFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class
})
public abstract class SplashFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SplashFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SplashFragment fragment);


}
