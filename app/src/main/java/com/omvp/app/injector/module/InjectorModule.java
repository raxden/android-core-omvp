package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.ui.home.HomeActivity;
import com.omvp.app.ui.home.HomeActivityModule;
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

}
