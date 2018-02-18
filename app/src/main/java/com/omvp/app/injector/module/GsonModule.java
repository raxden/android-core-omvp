package com.omvp.app.injector.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class GsonModule {

    @Provides
    @Singleton
    static GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    @Singleton
    static Gson gson(GsonBuilder builder) {
        return builder.create();
    }

    @Provides
    @Singleton
    @Named("excludeFieldsWithoutExposeAnnotation")
    static Gson excludeFieldsWithoutExposeAnnotationGson(GsonBuilder builder) {
        builder.excludeFieldsWithoutExposeAnnotation();
        return builder.create();
    }

}
