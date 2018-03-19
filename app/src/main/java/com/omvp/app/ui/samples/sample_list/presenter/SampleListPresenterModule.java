package com.omvp.app.ui.samples.sample_list.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleListPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SampleListPresenterModule {

    @Binds
    @PerFragment
    abstract SampleListPresenter presenter(SampleListPresenterImpl presenter);

}