
package com.omvp.app.ui.samples.sample_map.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.sample_map.view.SampleMapView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class SampleMapPresenterImpl extends BasePresenter<SampleMapView> implements SampleMapPresenter {


    @Inject
    SampleMapPresenterImpl(SampleMapView sampleMapView) {
        super(sampleMapView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

    }

}
