package com.omvp.app.interceptor.permission;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

public interface PermissionInterceptorCallback extends InterceptorCallback {

    void onPermissionGranted(PermissionActivityInterceptor.Permission permission);

    void onPermissionAlreadyGranted(PermissionActivityInterceptor.Permission permission);

    void onPermissionDenied(PermissionActivityInterceptor.Permission permission);

    void onPermissionDeniedForEver(PermissionActivityInterceptor.Permission permission);
}
