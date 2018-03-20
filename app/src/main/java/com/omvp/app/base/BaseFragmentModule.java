package com.omvp.app.base;

import android.app.Fragment;
import android.app.FragmentManager;

import com.omvp.app.helper.DialogHelper;
import com.omvp.app.injector.module.InterceptorFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.util.DisposableManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of {@link Fragment}.
 */
@Module(includes = InterceptorFragmentModule.class)
public abstract class BaseFragmentModule {

    public static final String CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager";
    public static final String DISPOSABLE_FRAGMENT_MANAGER = "BaseFragmentModule.disposableFragmentManager";

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    static FragmentManager childFragmentManager(Fragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @Named(DISPOSABLE_FRAGMENT_MANAGER)
    @PerFragment
    static DisposableManager disposableFragmentManager() {
        return new DisposableManager();
    }

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    static DialogHelper dialogHelper(Fragment fragment, @Named(CHILD_FRAGMENT_MANAGER) FragmentManager fragmentManager) {
        return new DialogHelper(fragment, fragmentManager);
    }

}
