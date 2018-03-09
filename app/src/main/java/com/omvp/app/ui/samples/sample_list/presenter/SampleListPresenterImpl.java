
package com.omvp.app.ui.samples.sample_list.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableMaybeObserver;
import com.omvp.app.model.SampleModel;
import com.omvp.app.model.mapper.SampleModelDataMapper;
import com.omvp.app.ui.samples.sample_list.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.sample_list.view.SampleListView;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SampleListPresenterImpl extends BasePresenter<SampleListView>
        implements SampleListPresenter, SampleListAdapter.AdapterCallback {

    @Inject
    GetSampleListUseCase mGetSampleListUseCase;
    @Inject
    SampleModelDataMapper mSampleModelDataMapper;

    private List<SampleDomain> mSampleDomainList;

    @Inject
    public SampleListPresenterImpl(SampleListView sampleListView) {
        super(sampleListView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadSampleList();
    }

    @Override
    public void sampleItemSelected(int position) {
        sampleItemSelected(mSampleDomainList.get(position));
    }

    private void loadSampleList() {
        mDisposableManager.add(mGetSampleListUseCase.execute()
                .map(new Function<List<SampleDomain>, List<SampleModel>>() {
                    @Override
                    public List<SampleModel> apply(List<SampleDomain> sampleDomains) throws Exception {
                        mSampleDomainList = sampleDomains;
                        return mSampleModelDataMapper.transform(sampleDomains);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableMaybeObserver<List<SampleModel>>(mContext) {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        showProgress();
                    }

                    @Override
                    public void onSuccess(List<SampleModel> sampleModelList) {
                        hideProgress();
                        drawSampleList(sampleModelList);
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                        showEmptyView();
                    }
                }));
    }

    private void drawSampleList(List<SampleModel> sampleModelList) {
        if (mView != null) {
            mView.drawSampleList(sampleModelList);
        }
    }

    private void showEmptyView() {
        if (mView != null) {
            mView.showEmptyView();
        }
    }

    private void sampleItemSelected(SampleDomain sampleDomain) {
        if (mView != null) {
            mView.onSampleItemSelected(sampleDomain);
        }
    }
}
