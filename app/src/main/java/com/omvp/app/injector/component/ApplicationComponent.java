package com.omvp.app.injector.component;

import com.omvp.app.AppApplication;
import com.omvp.app.injector.module.AnalyticsModule;
import com.omvp.app.injector.module.ApplicationModule;
import com.omvp.app.injector.module.GsonModule;
import com.omvp.app.injector.module.InjectorsModule;
import com.omvp.app.injector.module.ModelMapperModule;
import com.omvp.app.injector.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Ángel Gómez on 16/02/2018.
 */

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ApplicationModule.class,
                GsonModule.class,
                ModelMapperModule.class,
                AnalyticsModule.class,
                NetworkModule.class,
                InjectorsModule.class
        }
)
public interface ApplicationComponent extends AndroidInjector<AppApplication> {

        @Component.Builder
        abstract class Builder extends AndroidInjector.Builder<AppApplication> {

        }

}
