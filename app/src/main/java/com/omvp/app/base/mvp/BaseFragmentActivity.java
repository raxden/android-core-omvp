package com.omvp.app.base.mvp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.IdRes;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;

import javax.inject.Inject;

/**
 * Abstract Activity for all Activities to extend.
 */
public abstract class BaseFragmentActivity extends BaseActivity implements BaseViewFragmentCallback {

    /**
     * A reference to the FragmentManager is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Activity needs testing).
     */
    @Inject
    protected FragmentManager mFragmentManager;


    // =============== BaseViewFragmentCallback ====================================================

    @Override
    public void showProgress(float progress, String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(int code, String title, String message) {

    }

    @Override
    public void showMessage(int code, String title, String message) {

    }

    // =============== Support methods =============================================================

    protected final void addFragment(@IdRes int containerViewId, Fragment fragment) {
        mFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

}
