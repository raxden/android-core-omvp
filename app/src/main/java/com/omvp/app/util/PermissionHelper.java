package com.omvp.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionHelper {

    public static boolean checkSelfPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public static void requestPermissions(Activity activity, String[] permissions, int constantPermission) {
        ActivityCompat.requestPermissions(activity, permissions, constantPermission);
    }

}
