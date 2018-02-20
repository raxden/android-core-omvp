package com.omvp.app.base.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.omvp.app.utils.TrackerManager;
import com.raxdenstudios.commons.util.SDKUtils;
import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.SquareMVPDialogFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * Abstract (Dialog)Fragment for all (Dialog)Fragments and child (Dialog)Fragments to extend.
 * This contains some boilerplate dependency injection code and activity {@link Context}.
 * <p>
 * <b>WHY EXTEND DialogFragment?</b>
 * {@link DialogFragment}s are simple extensions of Fragments. DialogFragments can be shown as a
 * dialog floating above the current activity or be embedded into views like regular fragments.
 * Therefore, supporting both Fragments and DialogFragments for dependency injection can simply be
 * achieved by having the base fragment class (this) extend DialogFragment instead of Fragment.
 * We could have separate base classes for Fragments and DialogFragments but that would produce
 * duplicate code. See See https://github.com/vestrel00/android-dagger-butterknife-mvp/pull/64
 * <p>
 * Note that as of Dagger 2.12, the abstract base framework type
 * {@link dagger.android.DaggerDialogFragment} has been introduced for subclassing if so desired.
 * <p>
 * <b>DEPENDENCY INJECTION</b>
 * We could extend {@link dagger.android.DaggerDialogFragment} so we can get the boilerplate
 * dagger code for free. However, we want to avoid inheritance (if possible and it is in this case)
 * so that we have to option to inherit from something else later on if needed.
 * <p>
 * <b>VIEW BINDING</b>
 * This fragment handles view bind and unbinding.
 */
public abstract class BaseFragment<TPresenter extends IPresenter, TCallback extends BaseFragmentCallback>
        extends SquareMVPDialogFragment<TPresenter>
        implements BaseView {

    /**
     * A reference to the activity Context is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Activity needs testing).
     * More importantly, the getter method (getContext()) is not available for API level below 23.
     * We could use getActivity() though since that is available since API 11. However, exposing the
     * Activity reference is less safe than just exposing the Context since a lot more can be done
     * with the Activity reference.
     * <p>
     * For more details, see https://github.com/vestrel00/android-dagger-butterknife-mvp/pull/52
     */
    @Inject
    protected Context mContext;

    /**
     * TrackerManager is used to track fragment like a view through analytics tools.
     */
    @Inject
    protected TrackerManager mTrackManager;

    @Nullable
    private Unbinder mUnbinder;

    /**
     * A reference to the callbacks. This leaves the fragment to communicate with the Activity.
     */
    protected TCallback mCallbacks;

    @Override
    public void onAttach(Activity activity) {
        if (!SDKUtils.hasMarshmallow()) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidInjection.inject(this);
        }
        super.onAttach(activity);
        initFragmentCallback(activity);
    }

    @Override
    public void onAttach(Context context) {
        if (SDKUtils.hasMarshmallow()) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidInjection.inject(this);
        }
        super.onAttach(context);
        initFragmentCallback((Activity) context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        /*
         * Bind the views here instead of in onViewCreated so that view state changed listeners
         * are not invoked automatically without user interaction.
         *
         * If we bind before this method (e.g. onViewCreated), then any checked changed
         * listeners bound by ButterKnife will be invoked during fragment recreation (since
         * Android itself saves and restores the views' states. Take a look at this gist for a
         * concrete example: https://gist.github.com/vestrel00/982d585144423f728342787341fa001d
         *
         * The lifecycle order is as follows (same if added via xml or java or if retain
         * instance is true):
         *
         * onAttach
         * onCreateView
         * onViewCreated
         * onActivityCreated
         * onViewStateRestored
         * onResume
         *
         * Note that the onCreate (and other lifecycle events) are omitted on purpose. The
         * caveat to this approach is that views, listeners, and resources bound by
         * Butterknife will be null until onViewStatedRestored. Just be careful not to use any
         * objects bound using Butterknife before onViewStateRestored.
         *
         * Fragments that do not return a non-null View in onCreateView results in onViewCreated
         * and onViewStateRestored not being called. This means that Butterknife.bind will not get
         * called, which is completely fine because there is no View to bind. Furthermore, there is
         * no need to check if getView() returns null here because this lifecycle method only gets
         * called with a non-null View.
         */
        mUnbinder = ButterKnife.bind(this, getView());
    }

    @Override
    public void onDestroyView() {
        // This lifecycle method still gets called even if onCreateView returns a null view.
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void showProgress(float progress, String message) {
        mCallbacks.showProgress(progress, message);
    }

    @Override
    public void hideProgress() {
        mCallbacks.hideProgress();
    }

    @Override
    public void showError(int code, String title, String message) {
        mCallbacks.showError(code, title, message);
    }

    @Override
    public void showMessage(int code, String title, String message) {
        mCallbacks.showMessage(code, title, message);
    }

    @Override
    public void trackView() {
        mTrackManager.trackScreen(this);
    }

    private void initFragmentCallback(Activity activity) {
        if (activity instanceof BaseFragmentCallback) {
            mCallbacks = (TCallback) activity;
        } else {
            throw new IllegalStateException(activity.getClass().getSimpleName() + " must implements " + BaseFragmentCallback.class.getSimpleName());
        }
    }

}
