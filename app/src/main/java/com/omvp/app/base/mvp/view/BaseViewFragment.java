package com.omvp.app.base.mvp.view;

import android.os.Bundle;

import com.omvp.app.base.BaseFragment;
import com.omvp.app.base.mvp.presenter.Presenter;
import com.omvp.app.util.TrackerManager;

import javax.inject.Inject;

/**
 * A {@link BaseFragment} that contains and invokes {@link Presenter} lifecycle invocations.
 *         <p>
 *         Lifecycle.MVPFragment   ->      Presenter
 *
 *         onSaveInstanceState     ->      onSaveView
 *         onViewRestored          ->      onViewLoaded
 *         onResume                ->      onResume
 *         onPause                 ->      onPause
 *         onDestroyView           ->      onDropView
 */
public abstract class BaseViewFragment<TPresenter extends Presenter, TCallback extends BaseViewFragmentCallback>
        extends BaseFragment implements BaseView {

    @Inject
    protected TPresenter mPresenter;
    @Inject
    protected TCallback mCallback;
    @Inject
    protected TrackerManager mTrackerManager;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveView(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        /*
         * The Presenter.onStart method is called in onViewRestored so that the Fragment’s
         * views are bound before the presentation begins. This ensures that no NullPointerException
         * occurs if the Presenter calls an MVPView method that uses a bound view.
         *
         * Furthermore, Fragments that do not return a non-null View in onCreateView will result in
         * onViewRestored not being called. This results in Presenter.onViewLoaded not being
         * invoked. Therefore, no-UI Fragments do not support Presenter-View pairs. We could modify
         * our code to support Presenter-View pairs in no-UI Fragments if needed. However, I will
         * keep things as is since I do not consider it appropriate to have a Presenter-View pair
         * in a no-UI Fragment. Do feel free to disagree and refactor.
         */
        mPresenter.onViewRestored(savedInstanceState);
        mPresenter.onViewLoaded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
        mTrackerManager.trackScreen(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDropView();
    }

    // =============================================================================================

    @Override
    public void showProgress(float progress, String message) {
        mCallback.showProgress(progress, message);
    }

    @Override
    public void hideProgress() {
        mCallback.hideProgress();
    }

    @Override
    public void showError(int code, String title, String message) {
        mCallback.showError(code, title, message);
    }

    @Override
    public void showMessage(int code, String title, String message) {
        mCallback.showMessage(code, title, message);
    }

}
