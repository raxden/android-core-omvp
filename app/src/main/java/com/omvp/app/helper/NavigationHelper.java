package com.omvp.app.helper;

import android.app.Activity;
import android.os.Bundle;

import com.omvp.app.ui.splash.SplashActivity;
import com.raxdenstudios.commons.manager.NavigationManager;

public class NavigationHelper {

    private Activity mActivity;

    public NavigationHelper(Activity activity) {
        mActivity = activity;
    }

    public void launchSplash() {
        finishAllActivities();
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SplashActivity.class)
                .launch();
    }

    private void finishAllActivities() {
//        Intent intent = new Intent(OperationBroadcastActivityInterceptor.OPERATION_ACTION);
//        intent.putExtra(OperationBroadcastActivityInterceptor.OPERATION, OperationBroadcastActivityInterceptor.OPERATION_FINISH_ALL);
//        mActivity.sendBroadcast(intent);
    }

    private Bundle getExtras() {
        Bundle extras = mActivity.getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        return extras;
    }

}
