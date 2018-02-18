package com.omvp.app;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.omvp.app.injector.component.DaggerApplicationComponent;
import com.omvp.app.utils.CrashReportingTree;
import com.omvp.commons.Constants;
import com.raxdenstudios.square.SquareMultiDexApplication;
import com.raxdenstudios.square.interceptor.Interceptor;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ángel Gómez on 16/02/2018.
 */
public class AppApplication extends SquareMultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        initInjector();
        initCompatVector();
        initFabric();
        initTimber();
        initCalligraphy();
        initTreeTen();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {

    }

    private void initCompatVector() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }

    private void initTimber() {
        Timber.Tree tree;
        if (BuildConfig.DEBUG) {
            tree = new Timber.DebugTree();
        } else {
            tree = new CrashReportingTree();
        }
        Timber.plant(tree);
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.DEFAULT_FONT)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initTreeTen() {
        AndroidThreeTen.init(this);
    }

    private void initInjector() {
        DaggerApplicationComponent.builder().create(this).inject(this);
    }

}
