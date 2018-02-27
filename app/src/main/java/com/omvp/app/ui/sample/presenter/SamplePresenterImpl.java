
package com.omvp.app.ui.sample.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.sample.view.SampleView;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;

import javax.inject.Inject;

@PerFragment
public class SamplePresenterImpl extends BasePresenter<SampleView> implements SamplePresenter {

    @Inject
    GetSampleUseCase mGetSampleUseCase;
    @Inject
    GetSampleListUseCase mGetSampleListUseCase;

    @Inject
    SamplePresenterImpl(SampleView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

}
