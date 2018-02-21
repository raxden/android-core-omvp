package com.omvp.app;

import com.crashlytics.android.Crashlytics;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.omvp.app.base.BaseApplication;
import com.omvp.app.util.CrashReportingTree;
import com.omvp.commons.Constants;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ángel Gómez on 16/02/2018.
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initFabric();
        initTimber();
        initCalligraphy();
        initTreeTen();
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

}
