package com.omvp.app.ui.samples.sample_pager.presenter;


import com.omvp.app.ui.samples.sample_pager.view.SamplePagerSecondFragment;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerSecondView;

import javax.inject.Inject;

public class SamplePagerSecondPresenterImpl extends SamplePagerPresenterImpl<SamplePagerSecondView> implements SamplePagerSecondPresenter {

    @Inject
    public SamplePagerSecondPresenterImpl(SamplePagerSecondFragment view) {
        super(view);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        drawText("Fragment #2");
    }

    private void drawText(String text){
        if (mView != null){
            mView.drawText(text);
        }
    }
}
