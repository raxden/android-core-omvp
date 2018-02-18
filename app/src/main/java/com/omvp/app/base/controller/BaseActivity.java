package com.omvp.app.base.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.omvp.app.utils.AnimationHelper;
import com.omvp.app.utils.DialogHelper;
import com.omvp.app.utils.NavigationHelper;
import com.omvp.app.utils.SnackBarHelper;
import com.raxdenstudios.square.SquareActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Abstract Activity for all Activities to extend.
 * <p>
 * <b>DEPENDENCY INJECTION</b>
 * We could extend {@link dagger.android.DaggerActivity} so we can get the boilerplate
 * dagger code for free. However, we want to avoid inheritance (if possible and it is in this case)
 * so that we have to option to inherit from something else later on if needed.
 */
public abstract class BaseActivity extends SquareActivity implements HasFragmentInjector {

    /**
     * A reference to the FragmentManager is injected and used instead of the getter method. This
     * enables ease of mocking and verification in tests (in case Activity needs testing).
     *
     * For more details, see https://github.com/vestrel00/android-dagger-butterknife-mvp/pull/52
     */
    @Inject
    protected FragmentManager mFragmentManager;
    @Inject
    protected NavigationHelper mNavigationHelper;
    @Inject
    protected DialogHelper mDialogHelper;
    @Inject
    protected SnackBarHelper mSnackBarHelper;
    @Inject
    protected AnimationHelper mAnimationHelper;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return mFragmentInjector;
    }
}
