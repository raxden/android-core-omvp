package com.omvp.app.injector.module;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.omvp.app.AppApplication;
import com.omvp.app.R;
import com.omvp.app.utils.TrackerManager;
import com.omvp.data.manager.CredentialsManager;
import com.omvp.data.manager.LocaleManager;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.preferences.AdvancedPreferences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class ApplicationModule {

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
    abstract Application application(AppApplication application);

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
    static LocaleManager localeManager(Application application, AdvancedPreferences advancedPreferences) {
        Set<String> availableLocaleList = new HashSet<>(Arrays.asList(application.getResources().getStringArray(R.array.available_locale_list)));
        return new LocaleManager(advancedPreferences, availableLocaleList);
    }

    @Provides
    @Singleton
    static CredentialsManager provideCredentialsManager(AdvancedPreferences advancedPreferences) {
        return new CredentialsManager(advancedPreferences);
    }

    @Provides
    @Singleton
    static TrackerManager provideTrackerManager(Tracker tracker, FirebaseAnalytics firebaseAnalytics) {
        return new TrackerManager(tracker, firebaseAnalytics);
    }

}
