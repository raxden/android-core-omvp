package com.omvp.app.ui.samples.sample_pager.view;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.omvp.app.R;
import com.omvp.app.ui.samples.sample_pager.presenter.SamplePagerFirstPresenterImpl;

import butterknife.BindView;

public class SamplePagerFirstFragment
        extends SamplePagerFragment<SamplePagerFirstPresenterImpl, SamplePagerFirstFragment.SamplePagerFirstFragmentCallback>
        implements SamplePagerFirstView {

    @BindView(R.id.text)
    AppCompatTextView mTextView;

    public interface SamplePagerFirstFragmentCallback extends SamplePagerFragment.SamplePagerFragmentCallback {

    }

    public SamplePagerFirstFragment() {
    }

    public static SamplePagerFirstFragment newInstance(Bundle bundle) {
        SamplePagerFirstFragment fragment = new SamplePagerFirstFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void drawText(String text) {
        mTextView.setText(text);
    }
}
