package com.omvp.app.base;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.omvp.app.injector.component.DaggerApplicationComponent;
import com.raxdenstudios.commons.util.SDKUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * The Android {@link MultiDexApplication}.
 */
public class BaseApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    // =============== LifeCycle ===================================================================

    @Override
    public void onCreate() {
        super.onCreate();

        initCompatVector();
        initDaggerApplicationComponent();
    }

    // =============== HasActivityInjector =========================================================

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    // =============== Support methods =============================================================

    private void initCompatVector() {
        if (!SDKUtils.hasLollipop()) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }

    protected void initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this);
    }

}
