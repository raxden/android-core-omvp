package com.omvp.app.base.mvp.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.omvp.app.base.mvp.view.BaseView;


/**
 * The presenter is responsible to act as the middle man between view and model. It retrieves data
 * from the model and returns it formatted to the view. But unlike the typical MVC, it also decides
 * what happens when you interact with the view. To access view, use mView.
 */
public abstract class BasePresenter<TView extends BaseView> implements Presenter<TView> {

    public final Context mContext;
    public final TView mView;

    public BasePresenter(Context context, TView view) {
        mContext = context;
        mView = view;
    }

    // Presenter life cycle methods ================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        if (mView != null) {
            mView.trackView();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDropView() {

    }

    @Override
    public void onSave(Bundle outState) {

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
