package com.omvp.app.injector.module;

import android.content.Context;

import com.google.gson.Gson;
import com.omvp.app.BuildConfig;
import com.omvp.app.R;
import com.omvp.data.network.gateway.AppCredentialsGateway;
import com.omvp.data.network.gateway.AppGateway;
import com.omvp.data.network.gateway.retrofit.AppCredentialsRetrofitGatewayImpl;
import com.omvp.data.network.gateway.retrofit.AppRetrofitGatewayImpl;
import com.omvp.data.network.gateway.retrofit.callAdapter.RxErrorHandlingCallAdapterFactory;
import com.omvp.data.network.gateway.retrofit.interceptor.CredentialsInterceptor;
import com.omvp.data.network.gateway.retrofit.interceptor.HttpCacheInterceptor;
import com.omvp.data.network.gateway.retrofit.interceptor.HttpLocaleInterceptor;
import com.omvp.data.network.gateway.retrofit.service.AppCredentialsRetrofitService;
import com.omvp.data.network.gateway.retrofit.service.AppRetrofitService;
import com.omvp.domain.repository.CredentialsRepository;
import com.omvp.domain.repository.LocaleRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public abstract class NetworkModule {

    private static final int HTTP_CACHE_MAX_AGE = 60 * 10;              // read from cache for 10 minutes
    private static final int HTTP_CACHE_MAX_STALE = 60 * 60 * 24 * 28;  // tolerate 4-weeks stale
    private static final int TIMEOUT = 35;                              // 30 sec
    private static final int CONNECT_TIMEOUT = 15;                      // 10 sec

    @Provides
    @Singleton
    static HttpLoggingInterceptor.Level httpLoggingInterceptorLevel() {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY;
        } else {
            return HttpLoggingInterceptor.Level.NONE;
        }
    }

    // Interceptors ================================================================================

    @Provides
    @Singleton
    static HttpCacheInterceptor cacheInterceptor(Context context) {
        HttpCacheInterceptor httpCacheInterceptor = new HttpCacheInterceptor(context);
        httpCacheInterceptor.setCacheMaxAge(HTTP_CACHE_MAX_AGE);
        httpCacheInterceptor.setCacheMaxStale(HTTP_CACHE_MAX_STALE);
        return httpCacheInterceptor;
    }

    @Provides
    @Singleton
    static HttpLocaleInterceptor localeInterceptor(LocaleRepository repository) {
        return new HttpLocaleInterceptor(repository);
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor httpLoggingInterceptor(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    static CredentialsInterceptor credentialsInterceptor(CredentialsRepository repository) {
        return new CredentialsInterceptor(repository);
    }

    // OKHttpClient ================================================================================

    @Provides
    @Singleton
    static OkHttpClient httpClient(HttpLoggingInterceptor loggingInterceptor, HttpCacheInterceptor httpCacheInterceptor, HttpLocaleInterceptor httpLocaleInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addNetworkInterceptor(httpCacheInterceptor)
                .addInterceptor(httpLocaleInterceptor)
                .cache(cache)
                .retryOnConnectionFailure(true)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    @Named("credentials")
    static OkHttpClient credentialsHttpClient(HttpLoggingInterceptor loggingInterceptor, CredentialsInterceptor credentialsInterceptor, HttpLocaleInterceptor httpLocaleInterceptor) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(credentialsInterceptor)
                .addInterceptor(httpLocaleInterceptor)
                .retryOnConnectionFailure(true)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    // Retrofit ====================================================================================

    @Provides
    @Singleton
    static Retrofit retrofit(Context context, OkHttpClient httpClient, @Named("excludeFieldsWithoutExposeAnnotation") Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_domain))
                .client(httpClient)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    @Named("credentials")
    static Retrofit credentialsRetrofit(Context context, @Named("credentials") OkHttpClient httpClient, @Named("excludeFieldsWithoutExposeAnnotation") Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_domain))
                .client(httpClient)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    // RetrofitService =============================================================================

    @Provides
    @Singleton
    static AppRetrofitService appRetrofitService(Retrofit retrofit) {
        return retrofit.create(AppRetrofitService.class);
    }

    @Provides
    @Singleton
    static AppCredentialsRetrofitService appCredentialsRetrofitService(@Named("credentials") Retrofit retrofit) {
        return retrofit.create(AppCredentialsRetrofitService.class);
    }
    
    // Gateway =====================================================================================

    @Provides
    @Singleton
    static AppGateway appGateway(Context context, AppRetrofitService service) {
        return new AppRetrofitGatewayImpl(context, service);
    }

    @Provides
    @Singleton
    static AppCredentialsGateway appCredentialsGateway(Context context, AppCredentialsRetrofitService service) {
        return new AppCredentialsRetrofitGatewayImpl(context, service);
    }

}
