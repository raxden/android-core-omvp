package com.omvp.data.network.gateway.retrofit.interceptor;


import android.text.TextUtils;

import com.omvp.domain.repository.LocaleRepository;

import java.io.IOException;
import java.util.Locale;

import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class HttpLocaleInterceptor implements Interceptor {

    private final LocaleRepository localeRepository;

    private String language;

    public HttpLocaleInterceptor(LocaleRepository localeRepository) {
        this.localeRepository = localeRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Timber.d("[intercept] %s", chain.request().url().toString());
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("Accept-Language", retrieveLanguage())
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String retrieveLanguage() {
        if (TextUtils.isEmpty(language)) {
            localeRepository.retrieve()
                    .subscribeWith(new DisposableSingleObserver<Locale>() {
                        @Override
                        public void onSuccess(Locale locale) {
                            language = locale.toString().replaceAll("_", "-");
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
        return language;
    }

}
