package com.omvp.app.ui.samples.sample_pager.presenter;


import com.omvp.app.ui.samples.sample_pager.view.SamplePagerFirstFragment;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerFirstView;

import javax.inject.Inject;

public class SamplePagerFirstPresenterImpl extends SamplePagerPresenterImpl<SamplePagerFirstView> implements SamplePagerFirstPresenter {

    @Inject
    public SamplePagerFirstPresenterImpl(SamplePagerFirstFragment view) {
        super(view);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        drawText("Fragment #1");
    }

    private void drawText(String text){
        if (mView != null){
            mView.drawText(text);
        }
    }
}
