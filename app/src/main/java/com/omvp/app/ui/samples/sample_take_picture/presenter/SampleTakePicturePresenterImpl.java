
package com.omvp.app.ui.samples.sample_take_picture.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureView;

import javax.inject.Inject;

public class SampleTakePicturePresenterImpl extends BasePresenter<SampleTakePictureView> implements SampleTakePicturePresenter {


    @Inject
    public SampleTakePicturePresenterImpl(SampleTakePictureView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

}
