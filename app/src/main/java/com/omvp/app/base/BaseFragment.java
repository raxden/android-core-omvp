package com.omvp.app.base;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.omvp.app.helper.DialogHelper;
import com.omvp.app.util.DisposableManager;
import com.raxdenstudios.commons.util.SDKUtils;
import com.raxdenstudios.square.SquareDialogFragment;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

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
public abstract class BaseFragment extends SquareDialogFragment implements
        HasFragmentInjector {

    /**
     * A reference to the activity Context is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Activity needs testing).
     * More importantly, the getter method (getContext()) is not available for API level below 23.
     * We could use getActivity() though since that is available since API 11. However, exposing the
     * Activity reference is less safe than just exposing the Context since a lot more can be done
     * with the Activity reference.
     * <p>
     */
    @Inject
    protected Context mContext;
    @Inject
    protected Resources mResources;

    /**
     * A reference to the FragmentManager is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Fragment needs testing).
     * <p>
     */
    // Note that this should not be used within a child fragment.
    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected FragmentManager mChildFragmentManager;
    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected DialogHelper mDialogHelper;
    @Inject
    @Named(BaseFragmentModule.DISPOSABLE_FRAGMENT_MANAGER)
    protected DisposableManager mDisposableManager;

    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;

    // =============== LifeCycle ===================================================================

    @Override
    public void onAttach(Activity activity) {
        if (!SDKUtils.hasMarshmallow()) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        if (SDKUtils.hasMarshmallow()) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidInjection.inject(this);
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        mDisposableManager.dispose();
        super.onDestroy();
    }

    // =============== HasFragmentInjector =========================================================

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return mChildFragmentInjector;
    }

    // =============== Support methods =============================================================

    protected final void addChildFragment(@IdRes int containerViewId, Fragment fragment) {
        mChildFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

}
