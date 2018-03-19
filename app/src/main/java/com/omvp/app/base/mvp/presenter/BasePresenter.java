package com.omvp.app.base.mvp.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.base.mvp.view.BaseView;
import com.omvp.app.base.mvp.view.IView;
import com.omvp.app.util.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;


/**
 * Abstract {@link Presenter} for all presenters to extend.
 * <p>
 * The presenter is responsible to act as the middle man between view and model. It retrieves data
 * from the model and returns it formatted to the view. But unlike the typical MVC, it also decides
 * what happens when you interact with the view. To access view, use mView.
 *
 * @param <TView> the type of the {@link IView}.
 */
public abstract class BasePresenter<TView extends BaseView> implements Presenter {

    @Inject
    protected Context mContext;
    @Inject
    protected Resources mResources;
    @Inject
    @Named(BaseFragmentModule.DISPOSABLE_FRAGMENT_MANAGER)
    protected DisposableManager mDisposableManager;

    protected TView mView;

    public BasePresenter(TView mView) {
        this.mView = mView;
    }

    // =============== LifeCycle ===================================================================

    @Override
    public void onViewRestored(Bundle savedState) {

    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDropView() {

    }

    @Override
    public void onSaveView(Bundle outState) {

    }

    // =============== Support methods =============================================================

    protected void showError(int title, int description) {
        showError(0, title, description);
    }

    protected void showError(String title, String description) {
        showError(0, title, description);
    }

    protected void showError(int code, int title, int description) {
        showError(code, mResources.getString(title), mResources.getString(description));
    }

    protected void showError(int code, String title, String description) {
        if (mView != null) {
            mView.showError(code, title, description);
        }
    }

    protected void showProgress() {
        showProgress(0, "");
    }

    protected void showProgress(int message) {
        showProgress(0, message);
    }

    protected void showProgress(String message) {
        showProgress(0, message);
    }

    protected void showProgress(float progress) {
        showProgress(progress, "");
    }

    protected void showProgress(float progress, int message) {
        showProgress(progress, mResources.getString(message));
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
}
