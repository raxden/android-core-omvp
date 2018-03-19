package com.omvp.app.ui.home.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleListPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class HomePresenterModule {

    @Binds
    @PerFragment
    abstract HomePresenter presenter(HomePresenterImpl presenter);

}