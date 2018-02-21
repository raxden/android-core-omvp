package com.omvp.app.base;

import android.app.Fragment;
import android.app.FragmentManager;

import com.omvp.app.injector.scope.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of {@link Fragment}.
 */
@Module
public abstract class BaseFragmentModule {

    static final String CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.mChildFragmentManager";

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    static FragmentManager childFragmentManager(Fragment fragment) {
        return fragment.getChildFragmentManager();
    }

}
