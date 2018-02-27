package com.omvp.data.network.gateway.retrofit.interceptor;


import android.text.TextUtils;

import com.omvp.data.manager.LocaleManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class HttpLocaleInterceptor implements Interceptor {

    private final LocaleManager localeManager;

    public HttpLocaleInterceptor(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Timber.d("[intercept] %s", chain.request().url().toString());
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("Accept-Language", getLanguage())
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String getLanguage() {
        String language = "";
        if (localeManager.getLocale() != null && !TextUtils.isEmpty(localeManager.getLocale().toString())) {
            language = localeManager.getLocale().toString().replaceAll("_", "-");
        }
        return language;
    }
}
