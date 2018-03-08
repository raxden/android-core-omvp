package com.omvp.app.ui.samples.sample_take_picture;

import android.app.Activity;

import com.omvp.app.base.BaseActivity;
import com.omvp.app.base.BaseActivityModule;
import com.omvp.app.base.mvp.BaseFragmentActivityModule;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureFragment;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides splash activity dependencies
 */
@Module(includes = {
        BaseFragmentActivityModule.class
})
public abstract class SampleTakePictureActivityModule {

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link Activity}."
     * <p>
     * This provides the activity required to inject the dependencies into the
     * {@link BaseActivity}.
     *
     * @param activity the SplashActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    abstract Activity activity(SampleTakePictureActivity activity);

    /**
     * The main activity listens to the events in the {@link SampleTakePictureFragment}.
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    abstract SampleTakePictureFragment.FragmentCallback fragmentCallback(SampleTakePictureActivity activity);

    // =============================================================================================

    /**
     * Provides the injector for the {@link SampleTakePictureFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SampleTakePictureFragmentModule.class)
    abstract SampleTakePictureFragment fragmentInjector();

}
