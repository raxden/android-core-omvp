package com.omvp.app.ui.samples.sample_take_picture.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.sample_take_picture.presenter.SampleTakePicturePresenter;
import com.omvp.app.util.ImageHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class SampleTakePictureFragment extends BaseViewFragment<SampleTakePicturePresenter, SampleTakePictureFragment.FragmentCallback> implements SampleTakePictureView {


    @BindView(R.id.take_picture_image)
    AppCompatImageView mTakePictureImg;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onGalleryImageRequested();
    }

    public static SampleTakePictureFragment newInstance(Bundle bundle) {
        SampleTakePictureFragment fragment = new SampleTakePictureFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    private void setupViews() {

    }

    @OnClick(R.id.take_picture_image)
    public void onTakePictureImagePressed(View view) {
        mCallback.onGalleryImageRequested();
    }

    public void pictureRetrieved(Uri uri) {
        ImageHelper.loadImageUser(getActivity(), uri, mTakePictureImg, null);
    }
}
