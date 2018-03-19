package com.omvp.app.interceptor.takePicture;

import android.net.Uri;

/**
 * Created by Angel on 19/03/2018.
 */

public interface TakePictureListener {

    void onWorkingPictureProgress(boolean workingProgress);

    void onPictureRetrieved(Uri picture);

}
