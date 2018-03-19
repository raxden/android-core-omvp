
package com.omvp.app.ui.samples.sample_multiple.bottom.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.sample_multiple.bottom.view.SampleBottomView;

import javax.inject.Inject;

public class SampleBottomPresenterImpl extends BasePresenter<SampleBottomView> implements SampleBottomPresenter {


    @Inject
    public SampleBottomPresenterImpl(SampleBottomView sampleBottomView) {
        super(sampleBottomView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

}
