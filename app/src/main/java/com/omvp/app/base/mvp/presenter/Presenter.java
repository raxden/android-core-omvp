package com.omvp.app.base.mvp.presenter;

import android.os.Bundle;

import com.omvp.app.base.mvp.view.IView;

/**
 * Presenter interface
 */
public interface Presenter<TView extends IView> {

    /**
     * Called on every presenter's creation.
     *
     * @param savedState
     */
    void onCreate(Bundle savedState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    /**
     * Called when view has been loaded.
     *
     */
    void onViewLoaded();

    /**
     * Called during View's onSaveInstanceState to persist Presenter's state as well.
     *
     * @param outState
     */
    void onSave(Bundle outState);

    /**
     * Called when user a View becomes destroyed not because of configuration change
     */
    void onDestroy();

    /**
     * Called during Activity's onDestroy() or Fragment's onDestroyView(), or during android.view.View#onDetachedFromWindow()
     */
    void onDropView();

}
