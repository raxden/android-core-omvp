
package com.omvp.app.ui.samples.sample.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.sample.view.SampleView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

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
    public SamplePresenterImpl(SampleView sampleView) {
        super(sampleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        mDisposableManager.add(mGetSampleListUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableMaybeObserver<List<SampleDomain>>() {
                    @Override
                    public void onSuccess(List<SampleDomain> sampleDomains) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}
