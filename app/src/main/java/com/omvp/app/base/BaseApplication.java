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
public abstract class BaseApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        initCompatVector();
        initDaggerApplicationComponent();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    private void initCompatVector() {
        if (!SDKUtils.hasLollipop()) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }

    private void initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this);
    }

}
