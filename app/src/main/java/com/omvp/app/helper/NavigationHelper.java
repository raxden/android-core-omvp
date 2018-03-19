package com.omvp.app.helper;

import android.app.Activity;
import android.os.Bundle;

import com.omvp.app.interceptor.operation.OperationBroadcastActivityInterceptor;
import com.omvp.app.ui.home.HomeActivity;
import com.omvp.app.ui.samples.sample.SampleActivity;
import com.omvp.app.ui.samples.sample_list.SampleListActivity;
import com.omvp.app.ui.samples.sample_location.SampleLocationActivity;
import com.omvp.app.ui.samples.sample_multiple.SampleMultipleActivity;
import com.omvp.app.ui.samples.sample_pager.SamplePagerActivity;
import com.omvp.app.ui.samples.sample_take_picture.SampleTakePictureActivity;
import com.omvp.app.ui.splash.SplashActivity;
import com.raxdenstudios.commons.manager.NavigationManager;

public class NavigationHelper {

    private Activity mActivity;

    public NavigationHelper(Activity activity) {
        mActivity = activity;
    }

    public void launchSplash() {
        OperationBroadcastActivityInterceptor.finishAllActivities(mActivity);
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SplashActivity.class)
                .launch();
    }

    public void launchHomeAndFinishPreviousViews() {
        OperationBroadcastActivityInterceptor.finishAllActivities(mActivity);
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(HomeActivity.class)
                .launch();
    }

    public void launchSample() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleActivity.class)
                .launch();
    }

    public void launchSample(long sampleItemId) {
        Bundle extras = getExtras();
        extras.putLong(Long.class.getSimpleName(), sampleItemId);
        new NavigationManager.Builder(mActivity)
                .putData(extras)
                .navigateTo(SampleActivity.class)
                .launch();
    }

    public void launchSampleList() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleListActivity.class)
                .launch();
    }

    public void launchSamplePager() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SamplePagerActivity.class)
                .launch();
    }

    public void launchSampleMap() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleMultipleActivity.class)
                .launch();
    }

    public void launchSampleLocation() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleLocationActivity.class)
                .launch();
    }


    public void launchSampleTakePicture() {
        new NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateTo(SampleTakePictureActivity.class)
                .launch();
    }

    private Bundle getExtras() {
        return mActivity.getIntent() != null && mActivity.getIntent().getExtras() != null ? mActivity.getIntent().getExtras() : new Bundle();
    }


}
