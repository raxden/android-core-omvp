package com.omvp.app.ui.samples.sample_pager;

import android.app.Activity;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.base.mvp.BaseFragmentActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerFirstFragment;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerFirstFragmentModule;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerSecondFragment;
import com.omvp.app.ui.samples.sample_pager.view.SamplePagerSecondFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides splash activity dependencies
 */
@Module(includes = {
        BaseFragmentActivityModule.class
})
public abstract class SamplePagerActivityModule {

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
    abstract Activity activity(SamplePagerActivity activity);

    // =============================================================================================

    /**
     * The main activity listens to the events in the {@link SamplePagerFirstFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract SamplePagerFirstFragment.SamplePagerFirstFragmentCallback firstFragmentCallback(SamplePagerActivity activity);

    /**
     * The main activity listens to the events in the {@link SamplePagerSecondFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract SamplePagerSecondFragment.SamplePagerSecondFragmentCallback secondFragmentCallback(SamplePagerActivity activity);

    /**
     * Provides the injector for the {@link SamplePagerFirstFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SamplePagerFirstFragmentModule.class)
    abstract SamplePagerFirstFragment firstFragmentInjector();

    /**
     * Provides the injector for the {@link SamplePagerSecondFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SamplePagerSecondFragmentModule.class)
    abstract SamplePagerSecondFragment secondFragmentInjector();
}
