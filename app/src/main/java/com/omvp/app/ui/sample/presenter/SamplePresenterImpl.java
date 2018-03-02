
package com.omvp.app.ui.sample.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.sample.view.SampleView;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;

import javax.inject.Inject;

public class SamplePresenterImpl extends BasePresenter<SampleView> implements SamplePresenter {

    @Inject
    GetSampleUseCase mGetSampleUseCase;
    @Inject
    GetSampleListUseCase mGetSampleListUseCase;
    @Inject
    GetSampleListUseCase mGetSampleListUseCase2;
    @Inject
    SampleModelDataMapper mSampleModelDataMapper;

    @Inject
    SamplePresenterImpl(SampleView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
    }

}
