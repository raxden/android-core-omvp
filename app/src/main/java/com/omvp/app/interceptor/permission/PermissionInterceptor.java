package com.omvp.app.interceptor.permission;

import com.raxdenstudios.square.interceptor.Interceptor;

public interface PermissionInterceptor extends Interceptor {

    void requestPermission(PermissionActivityInterceptor.Permission permission);

    boolean hasPermission(PermissionActivityInterceptor.Permission permission);
}
