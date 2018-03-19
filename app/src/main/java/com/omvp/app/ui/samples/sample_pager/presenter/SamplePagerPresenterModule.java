package com.omvp.app.ui.samples.sample_pager.presenter;

import com.omvp.app.base.mvp.presenter.BasePresenterModule;
import com.omvp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SamplePagerPresenterModule dependencies.
 */
@Module(includes = BasePresenterModule.class)
public abstract class SamplePagerPresenterModule {

    @Binds
    @PerFragment
    abstract SamplePagerFirstPresenter firstPresenter(SamplePagerFirstPresenterImpl presenter);

    @Binds
    @PerFragment
    abstract SamplePagerSecondPresenter secondPresenter(SamplePagerSecondPresenterImpl presenter);
}