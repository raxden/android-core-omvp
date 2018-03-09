package com.omvp.app.injector.component;

import com.omvp.app.base.BaseApplicationModuleTest;
import com.omvp.app.base.BaseApplicationTest;
import com.omvp.app.injector.module.InjectorModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                BaseApplicationModuleTest.class,
                InjectorModule.class
        }
)
public interface ApplicationComponentTest extends AndroidInjector<BaseApplicationTest> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplicationTest> {

    }

}
