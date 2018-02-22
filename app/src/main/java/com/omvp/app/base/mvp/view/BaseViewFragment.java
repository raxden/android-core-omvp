package com.omvp.app.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.omvp.app.base.BaseFragment;
import com.omvp.app.base.mvp.presenter.Presenter;
import com.omvp.app.util.TrackerManager;

import javax.inject.Inject;

/**
 * @author Ángel Gómez.
 *         <p>
 *         <p>The view will contain a reference to the presenter. The only thing
 *         that the view will do is calling a method from the presenter every time there is an interface
 *         action (a button click for example).</p>
 *         <p>
 *
 *         Lifecycle.MVPFragment   ->      Presenter
 *
 *         onSaveInstanceState     ->      onSave
 *         onCreate                ->      onCreate
 *         onViewStateRestored     ->      onViewLoaded
 *         onResume                ->      onResume
 *         onPause                 ->      onPause
 *         onDestroyView           ->      onDropView
 *         onDestroy               ->      onDestroy
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
        mPresenter.onSave(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        /*
         * The Presenter.onStart method is called in onViewStateRestored so that the Fragment’s
         * views are bound before the presentation begins. This ensures that no NullPointerException
         * occurs if the Presenter calls an MVPView method that uses a bound view.
         *
         * Furthermore, Fragments that do not return a non-null View in onCreateView will result in
         * onViewStateRestored not being called. This results in Presenter.onViewLoaded not being
         * invoked. Therefore, no-UI Fragments do not support Presenter-View pairs. We could modify
         * our code to support Presenter-View pairs in no-UI Fragments if needed. However, I will
         * keep things as is since I do not consider it appropriate to have a Presenter-View pair
         * in a no-UI Fragment. Do feel free to disagree and refactor.
         */
        mPresenter.onViewStateRestored(savedInstanceState);
        mPresenter.onViewLoaded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
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

    @Override
    public void trackView() {
        mTrackerManager.trackScreen(this);
    }

}
