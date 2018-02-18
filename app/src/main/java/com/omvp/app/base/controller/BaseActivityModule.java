package com.omvp.app.base.controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.utils.AnimationHelper;
import com.omvp.app.utils.DialogHelper;
import com.omvp.app.utils.NavigationHelper;
import com.omvp.app.utils.SnackBarHelper;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link Activity}.
 */
@Module
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
    static FragmentManager fragmentManager(Activity activity) {
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
