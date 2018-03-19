package com.omvp.app.util;

import android.app.Activity;
import android.app.Fragment;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.omvp.app.ui.home.view.HomeFragment;
import com.omvp.app.ui.splash.view.SplashFragment;
import com.raxdenstudios.commons.util.Utils;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class TrackerManager {

    private Activity mActivity;
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;

    private Map<String, String> mScreenNames = new HashMap<>();

    public TrackerManager(Activity activity, Tracker tracker, FirebaseAnalytics firebaseAnalytics) {
        mActivity = activity;
        mTracker = tracker;
        mFirebaseAnalytics = firebaseAnalytics;
        initScreenNames();
    }

    public void trackScreen(Fragment fragment) {
        String fragmentName = fragment.getClass().getName();
        if (mScreenNames.containsKey(fragmentName)) {
            String screenName = mScreenNames.get(fragmentName);
            if (Utils.hasValue(screenName)) {
                trackScreen(screenName);
            }
        }
    }

    public void trackScreen(String screenName) {
        Timber.d("trackScreen: %s", screenName);
        if (mTracker != null) {
            mTracker.setScreenName(screenName);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        if (mFirebaseAnalytics != null) {
            mFirebaseAnalytics.setCurrentScreen(mActivity, screenName,null);
        }
    }

    public void trackEvent(String category, String action, String label) {
        Timber.d("TrackEvent - Category: %s , Action: %s, Label: %s", category, action, label);
        if (mTracker != null) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setAction(action)
                    .setCategory(category)
                    .setLabel(label)
                    .build());
        }
    }

    private void initScreenNames() {
        mScreenNames.put(SplashFragment.class.getName(), "Splash view");
        mScreenNames.put(HomeFragment.class.getName(), "Home view");
        // TODO
    }

}
