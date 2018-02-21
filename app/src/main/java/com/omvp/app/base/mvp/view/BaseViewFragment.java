package com.omvp.app.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.omvp.app.base.BaseFragment;
import com.omvp.app.base.mvp.presenter.Presenter;

import javax.inject.Inject;

/**
 * @author Ángel Gómez.
 *         <p>
 *         <p>The view will contain a reference to the presenter. The only thing
 *         that the view will do is calling a method from the presenter every time there is an interface
 *         action (a button click for example).</p>
 *         <p>
 *         Lifecycle    MVPFragment             ->      Presenter
 *         onSaveInstanceState     ->      onSave
 *         onCreate                ->      onCreate
 *         onActivityCreated       ->      onViewLoaded
 *         onResume                ->      onResume
 *         onPause                 ->      onPause
 *         onDestroyView           ->      onDropView
 *         onDestroy               ->      onDestroy
 */
public abstract class BaseViewFragment<TPresenter extends Presenter> extends BaseFragment implements BaseView {

    @Inject
    public TPresenter mPresenter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onSave(outState);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.onCreate(savedInstanceState);
        }
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
         * onViewStateRestored not being called. This results in Presenter.onStart not being
         * invoked. Therefore, no-UI Fragments do not support Presenter-View pairs. We could modify
         * our code to support Presenter-View pairs in no-UI Fragments if needed. However, I will
         * keep things as is since I do not consider it appropriate to have a Presenter-View pair
         * in a no-UI Fragment. Do feel free to disagree and refactor.
         */
        if (mPresenter != null) {
            mPresenter.onViewLoaded();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDropView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    // =============================================================================================

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

    @Override
    public void trackView() {

    }

}
