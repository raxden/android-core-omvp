package com.omvp.app.ui.samples.sample_take_picture.view;

import android.net.Uri;

import com.omvp.app.base.mvp.view.BaseView;

/**
 * Created by Angel on 21/02/2018.
 */
public interface SampleTakePictureView extends BaseView {

    void pictureRetrieved(Uri picture);

}
