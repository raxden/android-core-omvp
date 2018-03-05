package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.ui.home.HomeActivity;
import com.omvp.app.ui.home.HomeActivityModule;
import com.omvp.app.ui.samples.sample.SampleActivity;
import com.omvp.app.ui.samples.sample.SampleActivityModule;
import com.omvp.app.ui.samples.sample_list.SampleListActivity;
import com.omvp.app.ui.samples.sample_list.SampleListActivityModule;
import com.omvp.app.ui.splash.SplashActivity;
import com.omvp.app.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class InjectorModule {

    /**
     * Provides the injector for the {@link SplashActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    abstract SplashActivity splashActivity();

    /**
     * Provides the injector for the {@link HomeActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {HomeActivityModule.class})
    abstract HomeActivity homeActivity();

    /**
     * Provides the injector for the {@link SampleActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleActivityModule.class})
    abstract SampleActivity sampleActivity();

    /**
     * Provides the injector for the {@link SampleListActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = {SampleListActivityModule.class})
    abstract SampleListActivity sampleListActivity();
}
