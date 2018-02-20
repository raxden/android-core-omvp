package com.omvp.app.base.presenter;

import android.content.Context;
import android.content.res.Resources;

import com.omvp.app.base.view.BaseView;
import com.raxdenstudios.mvp.presenter.Presenter;


/**
 * Don´t modify this class under no circumstances, it's a joke modify it if force is with you.
 */
public abstract class BasePresenter<TView extends BaseView> extends Presenter<TView> {

    public BasePresenter(Context context) {
        super(context);
    }

    // Presenter life cycle methods ================================================================

    @Override
    public void onResume() {
        super.onResume();

        if (mView != null) {
            mView.trackView();
        }
    }

    // Methods to override by presenter childs. ====================================================

    protected void showError(int code, int title, int description) {
        showError(code, getResources().getString(title), getResources().getString(description));
    }

    protected void showError(int code, String title, String description) {
        if (mView != null) {
            mView.showError(code, title, description);
        }
    }

    protected void showProgress() {
        showProgress(0, "");
    }

    protected void showProgress(float progress, int message) {
        showProgress(progress, getResources().getString(message));
    }

    protected void showProgress(float progress, String message) {
        if (mView != null) {
            mView.showProgress(progress, message);
        }
    }

    protected void hideProgress() {
        if (mView != null) {
            mView.hideProgress();
        }
    }

    protected Resources getResources() {
        return mContext.getResources();
    }

    // Support BasePresenter methods ===============================================================


}
