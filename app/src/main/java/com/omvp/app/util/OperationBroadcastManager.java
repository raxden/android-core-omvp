package com.omvp.app.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.omvp.app.BuildConfig;
import com.raxdenstudios.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationBroadcastManager {

    private static final String OPERATION_ACTION = BuildConfig.APPLICATION_ID + ".OPERATION_ACTION";
    private static final String OPERATION = "OPERATION";
    private static final String ACTIVITY_NAME = "ACTIVITY_NAME";

    private static final int OPERATION_ACTIVITY_FINISH = 1;
    private static final int OPERATION_ACTIVITY_FINISH_ALL = 2;

    private final Activity mActivity;

    public static void finishActivity(Activity activity, Class<Activity> className) {
        Intent intent = new Intent(OPERATION_ACTION);
        intent.putExtra(OPERATION, OPERATION_ACTIVITY_FINISH);
        intent.putExtra(ACTIVITY_NAME, className.getSimpleName());
        activity.sendBroadcast(intent);
    }

    public static void finishActivity(Activity activity, List<Class<Activity>> classNameList) {
        Intent intent = new Intent(OPERATION_ACTION);
        intent.putExtra(OPERATION, OPERATION_ACTIVITY_FINISH);
        intent.putExtra(ACTIVITY_NAME, StringUtils.join(classNameList, ","));
        activity.sendBroadcast(intent);
    }

    public static void finishAllActivities(Activity activity) {
        Intent intent = new Intent(OPERATION_ACTION);
        intent.putExtra(OPERATION, OPERATION_ACTIVITY_FINISH_ALL);
        activity.sendBroadcast(intent);
    }

    public OperationBroadcastManager(Activity activity) {
        mActivity = activity;
    }

    public void register() {
        registerOperationReceiver();
    }

    public void unregister() {
        unregisterOperationReceiver();
    }

    private void registerOperationReceiver() {
        IntentFilter intentFilter = new IntentFilter(OPERATION_ACTION);
        mActivity.registerReceiver(mOperationReceiver, intentFilter);
    }

    private void unregisterOperationReceiver() {
        mActivity.unregisterReceiver(mOperationReceiver);
    }

    private BroadcastReceiver mOperationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra(OPERATION, -1)) {
                case OPERATION_ACTIVITY_FINISH:
                    if (intent.hasExtra(ACTIVITY_NAME)) {
                        List<String> activitiesToFinish = new ArrayList<>();
                        String activityName = intent.getStringExtra(ACTIVITY_NAME);
                        if (activityName.contains(",")) {
                            activitiesToFinish = Arrays.asList(activityName.split(","));
                        } else {
                            activitiesToFinish.add(activityName);
                        }
                        for (String activityToFinish : activitiesToFinish) {
                            if (activityToFinish.equals(mActivity.getClass().getName())) {
                                mActivity.finish();
                            }
                        }
                    }
                    break;
                case OPERATION_ACTIVITY_FINISH_ALL:
                    mActivity.finish();
                    break;
                default:
            }
        }
    };

}
