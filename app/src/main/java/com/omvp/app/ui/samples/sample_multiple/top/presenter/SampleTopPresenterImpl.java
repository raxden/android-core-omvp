
package com.omvp.app.ui.samples.sample_multiple.top.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.sample_multiple.top.view.SampleTopView;

import javax.inject.Inject;

public class SampleTopPresenterImpl extends BasePresenter<SampleTopView> implements SampleTopPresenter {


    @Inject
    public SampleTopPresenterImpl(SampleTopView sampleTopView) {
        super(sampleTopView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

}
