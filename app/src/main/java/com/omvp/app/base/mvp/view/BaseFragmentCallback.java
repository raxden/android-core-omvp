package com.omvp.app.base.mvp.view;

/**
 * Don´t modify this class under no circumstances, it's a joke modify it if force is with you.
 */
public interface BaseFragmentCallback {

    void showProgress(float progress, String message);

    void hideProgress();

    void showError(int code, String title, String message);

    void showMessage(int code, String title, String message);

}
