package com.omvp.app.injector.component;

import com.omvp.app.base.BaseApplication;
import com.omvp.app.base.BaseApplicationModule;
import com.omvp.app.injector.module.InjectorsModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Ángel Gómez on 16/02/2018.
 */

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                BaseApplicationModule.class,
                InjectorsModule.class
        }
)
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

        @Component.Builder
        abstract class Builder extends AndroidInjector.Builder<BaseApplication> {

        }

}
