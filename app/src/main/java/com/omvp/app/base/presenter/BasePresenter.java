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

    protected void showError(int title, int description) {
        showError(getResources().getString(title), getResources().getString(description));
    }

    protected void showError(String title, String description) {
        if (mView != null) {
            mView.showError(title, description);
        }
    }

    protected void showLoading() {
        showLoading("");
    }

    protected void showLoading(int message) {
        showLoading(getResources().getString(message));
    }

    protected void showLoading(String message) {
        if (mView != null) {
            mView.showProgress(message);
        }
    }

    protected void hideLoading() {
        if (mView != null) {
            mView.hideProgress();
        }
    }

    protected Resources getResources() {
        return mContext.getResources();
    }

    // Support BasePresenter methods ===============================================================


}
