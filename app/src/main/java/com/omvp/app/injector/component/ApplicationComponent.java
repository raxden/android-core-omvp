package com.omvp.app.injector.component;

import com.omvp.app.base.BaseApplication;
import com.omvp.app.base.BaseApplicationModule;
import com.omvp.app.injector.module.InjectorModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Injects application dependencies.
 *
 *      (*) Generated Classes by Dagger.
 *       - BaseApplicationModule
 *       - InjectorModule - Module responsable to inject dependencies to activities used in project
 *       - AndroidInjectionModule - Dagger module required to use this aproach.
 *
 *
 * ApplicationComponent ----- BaseApplicationModule - InjectorModule - AndroidInjectionModule
 *         |   |                                            |
 *         |   |                                      SplashActivity
 *         |   |                                       SampleActivity
 *         |   |
 *         |   |
 *         |   |                                                    BaseActivityModule
 *         |   |                                                           |
 *         |   |                                                BaseFragmentActivityModule
 *         |   |                                                         |         |
 *         | *SplashActivitySubComponent ------------------ SplashActivityModule   |
 *         |                          |                                            |
 *       *HomeActivitySubComponent ---|----------------------------------- SampleActivityModule
 *                      |             |
 *                      |             |
 *                      |             |                                BaseFragmentModule
 *                      |             |                                     |    |
 *                      |  *SplashFragmentSubComponent --- SplashFragmentModule  |
 *                      |                                                        |
 *           *HomeFragmentSubcomponent --------------------------------- SampleFragmentModule
 *
 */
@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                BaseApplicationModule.class,
                InjectorModule.class
        }
)
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

        @Component.Builder
        abstract class Builder extends AndroidInjector.Builder<BaseApplication> {

        }

}
