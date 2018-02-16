package com.omvp.data.network.gateway.retrofit.interceptor;

import android.content.Context;

import com.raxdenstudios.commons.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Raxden on 17/07/2016.
 */
public class CacheInterceptor implements Interceptor {

    private static final int DEFAULT_CACHE_MAX_AGE = 60 * 10; // read from cache for 10 minutes
    private static final int DEFAULT_CACHE_MAX_STALE = 60 * 60 * 24 * 28; // tolerate 4-weeks stale

    private Context mContext;
    private int cacheMaxAge;
    private int cacheMaxStale;

    public CacheInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Timber.d("[intercept] %s", chain.request().url().toString());
        Response originalResponse = chain.proceed(chain.request());
        if (NetworkUtils.isNetworkAvailable(mContext)) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + (getCacheMaxAge() > 0 ? getCacheMaxAge() : DEFAULT_CACHE_MAX_AGE))
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + (getCacheMaxStale() > 0 ? getCacheMaxStale() : DEFAULT_CACHE_MAX_STALE))
                    .build();
        }
    }

    public int getCacheMaxAge() {
        return cacheMaxAge;
    }

    public void setCacheMaxAge(int cacheMaxAge) {
        this.cacheMaxAge = cacheMaxAge;
    }

    public int getCacheMaxStale() {
        return cacheMaxStale;
    }

    public void setCacheMaxStale(int cacheMaxStale) {
        this.cacheMaxStale = cacheMaxStale;
    }

}
