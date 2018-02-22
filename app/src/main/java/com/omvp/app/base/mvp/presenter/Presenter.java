package com.omvp.app.base.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

    /**
     * Resumes the presentation. This should be called in the view's (Activity or Fragment)
     * onResume() method.
     */
    void onResume();

    /**
     * Pauses the presentation. This should be called in the view's Activity or Fragment)
     * onPause() method.
     */
    void onPause();

    /**
     * Starts the presentation. This should be called in the view's (Activity or Fragment)
     * onCreate() or onViewStatedRestored() method respectively.
     *
     * @param savedState the saved instance state that contains state saved in
     *                           {@link #onSave(Bundle)}
     */
    void onViewStateRestored(@Nullable Bundle savedState);

    /**
     * Called when view has been loaded.
     *
     */
    void onViewLoaded();

    /**
     * Save the state of the presentation (if any). This should be called in the view's
     * (Activity or Fragment) onSaveInstanceState().
     *
     * @param outState the out state to save instance state
     */
    void onSave(Bundle outState);


    /**
     * Called during Activity's onDestroy() or Fragment's onDestroyView(), or during android.view.View#onDetachedFromWindow()
     */
    void onDropView();

}
