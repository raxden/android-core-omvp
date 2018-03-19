package com.omvp.app.ui.samples.sample_take_picture;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.BaseFragmentActivity;
import com.omvp.app.interceptor.permission.PermissionActivityInterceptor;
import com.omvp.app.interceptor.takePicture.TakePictureInterceptor;
import com.omvp.app.interceptor.takePicture.TakePictureInterceptorCallback;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

public class SampleTakePictureActivity extends BaseFragmentActivity implements
        SampleTakePictureFragment.FragmentCallback,
        ToolbarInterceptorCallback,
        TakePictureInterceptorCallback,
        InjectFragmentInterceptorCallback<SampleTakePictureFragment> {

    @Inject
    ToolbarInterceptor mToolbarInterceptor;
    @Inject
    InjectFragmentInterceptor mInjectFragmentInterceptor;
    @Inject
    TakePictureInterceptor mTakePictureInterceptor;

    private Toolbar mToolbar;
    private SampleTakePictureFragment mFragment;

    // =============== ToolbarInterceptorCallback ==================================================

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void onToolbarViewCreated(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // =============== InjectFragmentInterceptorCallback ===========================================

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public SampleTakePictureFragment onCreateFragment() {
        return SampleTakePictureFragment.newInstance(mExtras);
    }

    @Override
    public void onFragmentLoaded(SampleTakePictureFragment fragment) {
        mFragment = fragment;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        super.setupInterceptors(interceptorList);
        interceptorList.add(mToolbarInterceptor);
        interceptorList.add(mInjectFragmentInterceptor);
        interceptorList.add(mTakePictureInterceptor);
    }

    // ========= PermissionInterceptorCallback =====================================================
    @Override
    public void onPermissionGranted(PermissionActivityInterceptor.Permission permission) {
        super.onPermissionGranted(permission);
        if (permission == PermissionActivityInterceptor.Permission.CAMERA) {
            mTakePictureInterceptor.takePicture(getResources().getString(R.string.select_a_picture));
        }
    }

    @Override
    public void onPermissionAlreadyGranted(PermissionActivityInterceptor.Permission permission) {
        super.onPermissionAlreadyGranted(permission);
        if (permission == PermissionActivityInterceptor.Permission.CAMERA) {
            mTakePictureInterceptor.takePicture(getResources().getString(R.string.select_a_picture));
        }
    }


    // ========= TakePictureInterceptorCallback ====================================================
    @Override
    public void onWorkingPictureProgress(boolean workingProgress) {
        if (workingProgress) {
            showProgress(0,"");
        } else {
            hideProgress();
        }
    }

    @Override
    public void onPictureRetrieved(Uri uri) {
        mFragment.pictureRetrieved(uri);
    }

    @Override
    public void onGalleryImageRequested() {
        if (mPermissionInterceptor.hasPermission(PermissionActivityInterceptor.Permission.CAMERA)) {
            mTakePictureInterceptor.takePicture(getResources().getString(R.string.select_a_picture));
        } else {
            mPermissionInterceptor.requestPermission(PermissionActivityInterceptor.Permission.CAMERA);
        }
    }
}
