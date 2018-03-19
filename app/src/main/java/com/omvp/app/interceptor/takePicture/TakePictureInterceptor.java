package com.omvp.app.interceptor.takePicture;

import com.raxdenstudios.square.interceptor.Interceptor;

public interface TakePictureInterceptor extends Interceptor {

    void takePicture(final String chooserTitle);

}
