package com.omvp.app.base;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.omvp.app.injector.module.AnalyticsModule;
import com.omvp.app.injector.module.CacheModule;
import com.omvp.app.injector.module.GoogleModule;
import com.omvp.app.injector.module.GsonModule;
import com.omvp.app.injector.module.LocaleModule;
import com.omvp.app.injector.module.LocationModule;
import com.omvp.app.injector.module.MapperModule;
import com.omvp.app.injector.module.NetworkModule;
import com.omvp.app.injector.module.RepositoryModule;
import com.omvp.app.util.TrackerManager;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.preferences.AdvancedPreferences;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Provides application-wide dependencies.
 */
@Module(
        includes = {
                LocaleModule.class,
                GsonModule.class,
                MapperModule.class,
                AnalyticsModule.class,
                CacheModule.class,
                RepositoryModule.class,
                LocationModule.class,
                GoogleModule.class,
                NetworkModule.class
        }
)
public abstract class BaseApplicationModule {

    @Binds
    @Singleton
    /*
     * Singleton annotation isn't necessary since Application instance is unique but is here for
     * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
     * them to be scoped since they are the components being injected and their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    abstract Application application(BaseApplication application);

    @Binds
    @Singleton
    abstract Context applicationContext(Application application);

    // =============================================================================================

    @Provides
    @Singleton
    static ContentResolver contentResolver(Application application) {
        return application.getContentResolver();
    }

    @Provides
    @Singleton
    static AdvancedPreferences advancedPreferences(Application application) {
        return new AdvancedPreferences(application, Utils.getPackageName(application), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    static TrackerManager trackerManager(Tracker tracker, FirebaseAnalytics firebaseAnalytics) {
        return new TrackerManager(tracker, firebaseAnalytics);
    }

}
