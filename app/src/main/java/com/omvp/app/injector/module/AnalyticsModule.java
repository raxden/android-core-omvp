package com.omvp.app.injector.module;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.omvp.app.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AnalyticsModule {

    @Provides
    @Singleton
    static FirebaseAnalytics firebaseAnalytics(Application application) {
        return FirebaseAnalytics.getInstance(application);
    }

    @Provides
    @Singleton
    static Tracker tracker(Application application) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(application);
        return analytics.newTracker(R.xml.app_tracker);
    }

}
