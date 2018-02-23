package com.omvp.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.omvp.app.injector.module.HelperModule;
import com.omvp.app.injector.module.InterceptorActivityModule;
import com.omvp.app.injector.scope.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link Activity}.
 */
@Module(includes = {
        InterceptorActivityModule.class,
        HelperModule.class
})
public abstract class BaseActivityModule {

    @Binds
    @PerActivity
    /*
     * PerActivity annotation isn't necessary since Activity instance is unique but is here for
     * convention. In general, providing Application, Activity, Fragment, BroadcastReceiver,
     * etc does not require scoped annotations since they are the components being injected and
     * their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    abstract Context activityContext(Activity activity);

    // =============================================================================================

    @Provides
    @PerActivity
    static Resources resources(Activity activity) {
        return activity.getResources();
    }

    @Provides
    @PerActivity
    static Bundle activityExtras(Activity activity) {
        return activity.getIntent() != null && activity.getIntent().getExtras() != null ? activity.getIntent().getExtras() : new Bundle();
    }

}
