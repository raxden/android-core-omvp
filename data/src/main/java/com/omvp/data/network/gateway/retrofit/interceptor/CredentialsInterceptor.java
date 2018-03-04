package com.omvp.data.network.gateway.retrofit.interceptor;

import android.text.TextUtils;

import com.omvp.domain.Credentials;
import com.omvp.domain.repository.CredentialsRepository;
import com.raxdenstudios.commons.util.MediaType;

import java.io.IOException;

import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CredentialsInterceptor implements Interceptor {

    private final CredentialsRepository credentialsRepository;

    private String authorization;

    public CredentialsInterceptor(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("Authorization", retrieveAuthorization())
                .header("Content-Type", MediaType.APPLICATION_JSON.toString())
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String retrieveAuthorization() {
        if (TextUtils.isEmpty(authorization)) {
            credentialsRepository.retrieve()
                    .subscribeWith(new DisposableSingleObserver<Credentials>() {
                        @Override
                        public void onSuccess(Credentials credentials) {
                            authorization = "Bearer " + credentials.getAccessToken();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
        return authorization;
    }

}
