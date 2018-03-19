package com.omvp.app.base;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BaseApplicationModuleTest extends BaseApplicationModule{

    @Binds
    @Singleton
    abstract  BaseApplication provideApplication(BaseApplicationTest application);

}
