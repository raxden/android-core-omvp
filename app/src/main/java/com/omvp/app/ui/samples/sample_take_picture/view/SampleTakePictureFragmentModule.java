package com.omvp.app.ui.samples.sample_take_picture.view;

import android.app.Fragment;

import com.omvp.app.base.BaseFragmentModule;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_take_picture.presenter.SampleTakePicturePresenterModule;

import dagger.Binds;
import dagger.Module;

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SampleTakePicturePresenterModule.class
})
public abstract class SampleTakePictureFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}.
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    abstract Fragment fragment(SampleTakePictureFragment fragment);

    @Binds
    @PerFragment
    abstract SampleTakePictureView view(SampleTakePictureFragment fragment);

}
