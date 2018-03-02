package com.omvp.app.base;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import com.omvp.app.injector.module.HelperModule;
import com.omvp.app.injector.module.InterceptorActivityModule;
import com.omvp.app.injector.module.RepositoryModule;
import com.omvp.app.injector.module.UseCaseModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.util.DisposableManager;
import com.omvp.app.util.OperationBroadcastManager;

import dagger.Module;
import dagger.Provides;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link Activity}.
 */
@Module(includes = {
        InterceptorActivityModule.class,
        RepositoryModule.class,
        UseCaseModule.class,
        HelperModule.class
})
public abstract class BaseActivityModule {

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

    @Provides
    @PerActivity
    static DisposableManager activityDisposableManager() {
        return new DisposableManager();
    }

    @Provides
    @PerActivity
    static OperationBroadcastManager operationBroadcastManager(Activity activity) {
        return new OperationBroadcastManager(activity);
    }

}
