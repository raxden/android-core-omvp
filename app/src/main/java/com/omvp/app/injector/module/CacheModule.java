package com.omvp.app.injector.module;

import android.content.Context;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

@Module
public abstract class CacheModule {

    private static final String CACHE_DIRECTORY = "responses";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;         // 10 MiB;

    @Provides
    @Singleton
    static Cache cache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), CACHE_DIRECTORY);
        return new Cache(httpCacheDirectory, CACHE_SIZE);
    }

}
