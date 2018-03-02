package com.omvp.app.base;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.omvp.app.helper.AnimationHelper;
import com.omvp.app.helper.DialogHelper;
import com.omvp.app.helper.NavigationHelper;
import com.omvp.app.helper.SnackBarHelper;
import com.omvp.app.util.DisposableManager;
import com.omvp.app.util.OperationBroadcastManager;
import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Abstract Activity for all Activities to extend.
 */
public abstract class BaseActivity extends SquareActivity implements
        AutoInflateLayoutInterceptorCallback,
        HasFragmentInjector {

    @Inject
    protected Resources mResources;
    @Inject
    protected Bundle mExtras;
    @Inject
    protected NavigationHelper mNavigationHelper;
    @Inject
    protected DialogHelper mDialogHelper;
    @Inject
    protected SnackBarHelper mSnackBarHelper;
    @Inject
    protected AnimationHelper mAnimationHelper;
    @Inject
    protected DisposableManager mDisposableManager;
    @Inject
    protected OperationBroadcastManager mOperationBroadcastManager;

    @Inject
    AutoInflateLayoutInterceptor mAutoInflateLayoutInterceptor;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    protected View mContentView;

    // =============== LifeCycle ===================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        mOperationBroadcastManager.register();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mOperationBroadcastManager.unregister();
        mDisposableManager.dispose();
    }

    // ========= AutoInflateLayoutInterceptorCallback ==============================================

    @Override
    public void onContentViewCreated(View view, Bundle savedInstanceState) {
        mContentView = view;
    }

    // =============== HasFragmentInjector =========================================================

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return mFragmentInjector;
    }

    // =============== Support methods =============================================================

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        interceptorList.add(mAutoInflateLayoutInterceptor);
    }

}
