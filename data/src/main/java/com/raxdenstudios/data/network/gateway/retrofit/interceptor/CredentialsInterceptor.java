package com.raxdenstudios.data.network.gateway.retrofit.interceptor;

import com.raxdenstudios.commons.util.MediaType;
import com.raxdenstudios.data.manager.CredentialsManager;
import com.raxdenstudios.domain.Credentials;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CredentialsInterceptor implements Interceptor {

    private final CredentialsManager credentialsManager;

    public CredentialsInterceptor(CredentialsManager credentialsManager) {
        this.credentialsManager = credentialsManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("Authorization", getAuthorization())
                .header("Content-Type", MediaType.APPLICATION_JSON.toString())
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String getAuthorization() {
        Credentials credentials = credentialsManager.retrieve();
        if (credentials != null) {
            return "Bearer " + credentials.getAccessToken();
        }
        return "";
    }

}
