package com.omvp.app.interceptor.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.omvp.app.helper.DialogHelper;
import com.omvp.app.util.PermissionHelper;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;


public class PermissionActivityInterceptor extends ActivityInterceptor<PermissionInterceptorCallback> implements PermissionInterceptor {

    public enum Permission {NONE, LOCATION, CAMERA, READ_EXTERNAL_STORAGE, RECORD_AUDIO}

    private DialogHelper mDialogHelper;
    private Permission mPermission;

    public PermissionActivityInterceptor(Activity activity, PermissionInterceptorCallback callback) {
        super(activity, callback);
        mActivity = activity;
        mDialogHelper = new DialogHelper(mActivity, activity.getFragmentManager());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permission permission = Permission.values()[requestCode];
        boolean permissionGranted = false;
        boolean permissionDeniedForEver = false;
        switch (permission) {
            case LOCATION:
                permissionGranted = isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION);
                permissionDeniedForEver = !(PermissionHelper.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.ACCESS_FINE_LOCATION));
                break;
            case CAMERA:
                permissionGranted = isPermissionGranted(permissions, grantResults, Manifest.permission.CAMERA);
                permissionDeniedForEver = !(PermissionHelper.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA));
                break;
            case READ_EXTERNAL_STORAGE:
                permissionGranted = isPermissionGranted(permissions, grantResults, Manifest.permission.READ_EXTERNAL_STORAGE);
                permissionDeniedForEver = !(PermissionHelper.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE));
                break;
            case RECORD_AUDIO:
                permissionGranted = isPermissionGranted(permissions, grantResults, Manifest.permission.RECORD_AUDIO);
                permissionDeniedForEver = !(PermissionHelper.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.RECORD_AUDIO));
                break;
        }
        if (permissionGranted) {
            permissionGranted();
        } else {
            if (permissionDeniedForEver) {
                permissionDeniedForEver();
            } else {
                permissionDenied();
            }
        }
    }

    @Override
    public void requestPermission(Permission permission) {
        mPermission = permission;

        String manifestPerm;
        switch (mPermission) {
            case LOCATION:
                manifestPerm = Manifest.permission.ACCESS_FINE_LOCATION;
                break;
            case CAMERA:
                manifestPerm = Manifest.permission.CAMERA;
                break;
            case READ_EXTERNAL_STORAGE:
                manifestPerm = Manifest.permission.READ_EXTERNAL_STORAGE;
                break;
            case RECORD_AUDIO:
                manifestPerm = Manifest.permission.RECORD_AUDIO;
                break;
            default:
                return;
        }
        if (PermissionHelper.checkSelfPermission(mActivity, manifestPerm)) {
            if (PermissionHelper.shouldShowRequestPermissionRationale(mActivity, manifestPerm)) {
                showRationalePermissionDialog();
            } else {
                requestPermissions();
            }
        } else {
            permissionAlreadyGranted();
        }
    }

    @Override
    public boolean hasPermission(Permission permission) {
        String manifestPerm;
        switch (permission) {
            case LOCATION:
                manifestPerm = Manifest.permission.ACCESS_FINE_LOCATION;
                break;
            case CAMERA:
                manifestPerm = Manifest.permission.CAMERA;
                break;
            case READ_EXTERNAL_STORAGE:
                manifestPerm = Manifest.permission.READ_EXTERNAL_STORAGE;
                break;
            case RECORD_AUDIO:
                manifestPerm = Manifest.permission.RECORD_AUDIO;
                break;
            default:
                return false;
        }
        return !PermissionHelper.checkSelfPermission(mActivity, manifestPerm);
    }

    private void requestPermissions() {
        switch (mPermission) {
            case LOCATION:
                PermissionHelper.requestPermissions(mActivity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        mPermission.ordinal());
                break;
            case CAMERA:
                PermissionHelper.requestPermissions(mActivity,
                        new String[]{Manifest.permission.CAMERA},
                        mPermission.ordinal());
                break;
            case READ_EXTERNAL_STORAGE:
                PermissionHelper.requestPermissions(mActivity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        mPermission.ordinal());
            case RECORD_AUDIO:
                PermissionHelper.requestPermissions(mActivity,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        mPermission.ordinal());
                break;
        }
    }

    private void showRationalePermissionDialog() {
        //TODO SHOW PERMISSIONS DIALOG
        switch (mPermission) {
            case LOCATION:
                Toast.makeText(mContext, "Actualmente tienes el GPS desactivado, activalo para poder obtener resultados de calidad", Toast.LENGTH_LONG).show();
                break;
            case CAMERA:
                Toast.makeText(mContext, "Danos permisos para poder acceder a la camara y a tus fotos", Toast.LENGTH_LONG).show();
                break;
            case READ_EXTERNAL_STORAGE:
                Toast.makeText(mContext, "Danos permisos para poder acceder a la memoria del teléfono", Toast.LENGTH_LONG).show();
                break;
            case RECORD_AUDIO:
                Toast.makeText(mContext, "Para que puedas realizar un pedido con tu voz necesitamos poder acceder al microfono.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private boolean isPermissionGranted(String[] grantPerms, int[] grantResults, String perms) {
        for (int i = 0; i < grantPerms.length; i++) {
            if (perms.equals(grantPerms[i])) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }

    private void permissionDenied() {
        if (mCallback != null) {
            mCallback.onPermissionDenied(mPermission);
        }
    }

    private void permissionDeniedForEver() {
        if (mCallback != null) {
            mCallback.onPermissionDeniedForEver(mPermission);
        }
    }

    private void permissionAlreadyGranted() {
        if (mCallback != null) {
            mCallback.onPermissionAlreadyGranted(mPermission);
        }
    }

    private void permissionGranted() {
        if (mCallback != null) {
            mCallback.onPermissionGranted(mPermission);
        }
    }

}
