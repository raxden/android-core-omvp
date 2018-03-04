package com.omvp.app.injector.module;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;


@Module
public abstract class LocaleModule {

    @Provides
    @IntoSet
    @Singleton
    static Locale localeUK() {
        return Locale.UK;
    }

    @Provides
    @IntoSet
    @Singleton
    static Locale localeES() {
        return new Locale("es", "ES");
    }

    @Provides
    @Singleton
    @Named("default")
    static Locale localeDefault() {
        return new Locale("es", "ES");
    }


}
