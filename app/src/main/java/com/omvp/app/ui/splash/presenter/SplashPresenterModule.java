package com.omvp.app.ui.splash.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SplashPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SplashPresenterModule {

    @Binds
    @PerFragment
    abstract SplashPresenter splashPresenter(SplashPresenterImpl presenter);

}