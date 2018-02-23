package com.omvp.app.base.mvp.view;

/**
 * Abstract {@link IView} for all views to extend.
 */
public interface BaseView extends IView {

    void showProgress(float progress, String message);

    void hideProgress();

    void showError(int code, String title, String message);

    void showMessage(int code, String title, String message);

}
