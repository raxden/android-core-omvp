
package com.omvp.app.ui.samples.sample_take_picture.presenter;


import android.net.Uri;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.interceptor.takePicture.TakePictureInterceptor;
import com.omvp.app.interceptor.takePicture.TakePictureListener;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureView;

import javax.inject.Inject;

public class SampleTakePicturePresenterImpl extends BasePresenter<SampleTakePictureView> implements SampleTakePicturePresenter {

    @Inject
    TakePictureInterceptor mTakePictureInterceptor;

    @Inject
    public SampleTakePicturePresenterImpl(SampleTakePictureView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
    }

    @Override
    public void takePictureImage() {
        mTakePictureInterceptor.takePicture("Elige una foto", new TakePictureListener() {
            @Override
            public void onWorkingPictureProgress(boolean workingProgress) {
                if (workingProgress) {
                    showProgress(0,"");
                } else {
                    hideProgress();
                }
            }

            @Override
            public void onPictureRetrieved(Uri picture) {
                mView.pictureRetrieved(picture);
            }
        });
    }

}
