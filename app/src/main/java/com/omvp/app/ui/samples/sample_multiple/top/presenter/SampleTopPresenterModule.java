package com.omvp.app.ui.samples.sample_multiple.top.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleTopPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SampleTopPresenterModule {

    @Binds
    @PerFragment
    abstract SampleTopPresenter presenter(SampleTopPresenterImpl presenter);

}