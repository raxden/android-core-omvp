package com.omvp.app.ui.samples.sample_pager.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.ui.samples.sample_pager.presenter.SamplePagerSecondPresenterImpl;

import butterknife.BindView;

public class SamplePagerSecondFragment
        extends SamplePagerFragment<SamplePagerSecondPresenterImpl, SamplePagerSecondFragment.SamplePagerSecondFragmentCallback>
        implements SamplePagerSecondView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface SamplePagerSecondFragmentCallback extends SamplePagerFragment.SamplePagerFragmentCallback {

    }

    public SamplePagerSecondFragment() {
    }

    public static SamplePagerSecondFragment newInstance(Bundle bundle) {
        SamplePagerSecondFragment fragment = new SamplePagerSecondFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void drawText(String text) {
        mTextView.setText(text);
    }
}
