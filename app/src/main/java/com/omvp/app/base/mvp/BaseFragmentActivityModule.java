package com.omvp.app.base.mvp;

import android.app.Activity;
import android.app.FragmentManager;

import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link Activity}.
 */
@Module(includes = {
        BaseActivityModule.class
})
public abstract class BaseFragmentActivityModule {

    @Provides
    @PerActivity
    static FragmentManager activityFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }

}
