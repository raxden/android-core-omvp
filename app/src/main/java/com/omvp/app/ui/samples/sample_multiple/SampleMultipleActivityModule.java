package com.omvp.app.ui.samples.sample_multiple;

import android.app.Activity;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.base.mvp.BaseFragmentActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_multiple.bottom.view.SampleBottomFragment;
import com.omvp.app.ui.samples.sample_multiple.bottom.view.SampleBottomFragmentModule;
import com.omvp.app.ui.samples.sample_multiple.top.view.SampleTopFragment;
import com.omvp.app.ui.samples.sample_multiple.top.view.SampleTopFragmentModule;
import com.omvp.app.ui.splash.view.SplashFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides splash activity dependencies
 */
@Module(includes = {
        BaseFragmentActivityModule.class
})
public abstract class SampleMultipleActivityModule {

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link Activity}."
     * <p>
     * This provides the activity required to inject the dependencies into the
     * {@link BaseActivity}.
     *
     * @param activity the SplashActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    abstract Activity activity(SampleMultipleActivity activity);

    /**
     * The main activity listens to the events in the {@link SampleTopFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract SampleTopFragment.FragmentCallback topFragmentCallback(SampleMultipleActivity activity);

    /**
     * The main activity listens to the events in the {@link SampleTopFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract SampleBottomFragment.FragmentCallback bottomFragmentCallback(SampleMultipleActivity activity);

    // =============================================================================================

    /**
     * Provides the injector for the {@link SampleTopFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SampleTopFragmentModule.class)
    abstract SampleTopFragment topFragmentInjector();

    /**
     * Provides the injector for the {@link SampleBottomFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SampleBottomFragmentModule.class)
    abstract SampleBottomFragment bottonFragmentInjector();
}
