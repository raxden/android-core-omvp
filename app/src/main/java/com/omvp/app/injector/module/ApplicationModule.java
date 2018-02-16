package com.omvp.app.injector.module;

import android.content.ContentResolver;
import android.content.Context;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omvp.app.AppApplication;
import com.omvp.app.R;
import com.omvp.app.utils.TrackerManager;
import com.omvp.data.manager.CredentialsManager;
import com.omvp.data.manager.LocaleManager;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.preferences.AdvancedPreferences;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private static final String CACHE_DIRECTORY = "responses";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;         // 10 MiB;

    private final AppApplication mApplication;

    public ApplicationModule(AppApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    AppApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    ContentResolver provideContentResolver() {
        return mApplication.getContentResolver();
    }

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), CACHE_DIRECTORY);
        return new Cache(httpCacheDirectory, CACHE_SIZE);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        return builder.create();
    }

    @Provides
    @Singleton
    @Named("excludeFieldsWithoutExposeAnnotation")
    Gson provideExcludeFieldsWithoutExposeAnnotationGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        return builder.create();
    }

    @Provides
    @Singleton
    AdvancedPreferences provideAdvancedPreferences(Context context) {
        return new AdvancedPreferences(context, Utils.getPackageName(context), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    LocaleManager provideLocaleManager(Context context, AdvancedPreferences advancedPreferences) {
        Set<String> availableLocaleList = new HashSet<>(Arrays.asList(context.getResources().getStringArray(R.array.available_locale_list)));
        return new LocaleManager(advancedPreferences, availableLocaleList);
    }

    @Provides
    @Singleton
    CredentialsManager provideCredentialsManager(AdvancedPreferences advancedPreferences) {
        return new CredentialsManager(advancedPreferences);
    }

    @Provides
    @Singleton
    TrackerManager provideTrackerManager(Tracker tracker, FirebaseAnalytics firebaseAnalytics) {
        return new TrackerManager(tracker, firebaseAnalytics);
    }

}
