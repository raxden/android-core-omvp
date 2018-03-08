package com.omvp.app.interceptor.takePicture;

import android.net.Uri;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

public interface TakePictureInterceptorCallback extends InterceptorCallback {

    void onWorkingPictureProgress(boolean workingProgress);

    void onPictureRetrieved(Uri bitmap);

}
