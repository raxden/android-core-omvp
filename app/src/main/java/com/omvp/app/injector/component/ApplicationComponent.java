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
 *       - (*) Generated Classes by Dagger.
 *       - BaseApplicationModule - Module that contains all dependencies that the project will use.
 *       - InjectorModule - Module responsible to inject dependencies to activities used in project
 *       - AndroidInjectionModule - Dagger module required to use this approach.
 *
 * =================================================================================================
 * |                                                                                               |
 * |  ApplicationComponent ----- BaseApplicationModule - InjectorModule - AndroidInjectionModule   |
 * |        |   |                                            |                                     |
 * |        |   |                                      SplashActivity                              |
 * |        |   |                                       HomeActivity                               |
 * |        |   |                                                                                  |
 * |        |   |                                                                                  |
 * |        |   |                                                    BaseActivityModule            |
 * |        |   |                                                           |                      |
 * |        |   |                                                BaseFragmentActivityModule        |
 * |        |   |                                                         |         |              |
 * |        | *SplashActivitySubComponent ------------------ SplashActivityModule   |              |
 * |        |                          |                                            |              |
 * |      *HomeActivitySubComponent ---|----------------------------------- SampleActivityModule   |
 * |                     |             |                                                           |
 * |                     |             |                                                           |
 * |                     |             |                                BaseFragmentModule         |
 * |                     |             |                                     |    |                |
 * |                     |  *SplashFragmentSubComponent --- SplashFragmentModule  |                |
 * |                     |                                                        |                |
 * |          *HomeFragmentSubcomponent --------------------------------- SampleFragmentModule     |
 * |                                                                                               |
 * =================================================================================================
 *
 * Base elements (annotations) of Dagger2
 *
 *      - @Inject - base annotation whereby the "dependency is requested".
 *      - @Module - classes which methods "provide dependencies".
 *      - @Provide - methods inside @Module, which "tell Dagger how we what to build and present a
 *      dependency".
 *      - @Component - bridge between @Inject and @Module.
 *      - @Scope - enables to create global and local singletons.
 *      - @Qualifier - if different objects of the same type are necessary.
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
