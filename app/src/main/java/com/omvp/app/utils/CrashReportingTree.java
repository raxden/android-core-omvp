package com.omvp.app.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * Created by agomez on 10/03/2016.
 */
public class CrashReportingTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }
        if (message != null && messageCondition(message)) {
            return;
        }
        Crashlytics.logException(t);
    }

    private boolean messageCondition(String message) {
        return message.contains("java.net.UnknownHostException")
                || message.contains("java.net.ConnectException")
                || message.contains("java.net.SocketTimeoutException");
    }

}
