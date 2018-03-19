package com.omvp.app.ui.samples.sample.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleTopPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SamplePresenterModule {

    @Binds
    @PerFragment
    abstract SamplePresenter presenter(SamplePresenterImpl presenter);

}