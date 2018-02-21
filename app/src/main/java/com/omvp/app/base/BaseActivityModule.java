package com.omvp.app.base;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;

import com.omvp.app.helper.AnimationHelper;
import com.omvp.app.helper.DialogHelper;
import com.omvp.app.helper.NavigationHelper;
import com.omvp.app.helper.SnackBarHelper;
import com.omvp.app.injector.module.InterceptorActivityModule;
import com.omvp.app.injector.scope.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link Activity}.
 */
@Module(includes = InterceptorActivityModule.class)
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

//    @Provides
//    @PerActivity
//    static Bundle activityExtras(Activity activity) {
//        Bundle extras = activity.getIntent() != null ? activity.getIntent().getExtras() : new Bundle();
//        return extras;
//    }

    @Provides
    @PerActivity
    static FragmentManager activityFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }

    @Provides
    @PerActivity
    static NavigationHelper navigationHelper(Activity activity) {
        return new NavigationHelper(activity);
    }

    @Provides
    @PerActivity
    static DialogHelper dialogHelper(Activity activity, FragmentManager fragmentManager) {
        return new DialogHelper(activity, fragmentManager);
    }

    @Provides
    @PerActivity
    static SnackBarHelper snackBarHelper(Activity activity) {
        return new SnackBarHelper(activity);
    }

    @Provides
    @PerActivity
    static AnimationHelper animationHelper(Activity activity) {
        return new AnimationHelper(activity);
    }

}
