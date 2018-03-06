package com.omvp.app.ui.samples.sample_multiple.bottom.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleBottomPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SampleBottomPresenterModule {

    @Binds
    @PerFragment
    abstract SampleBottomPresenter presenter(SampleBottomPresenterImpl presenter);

}