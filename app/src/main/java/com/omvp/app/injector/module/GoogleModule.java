package com.omvp.app.injector.module;

import android.content.Context;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.omvp.app.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GoogleModule {

    @Provides
    @Singleton
    GoogleSignInOptions googleSignInOptions(Context context) {
        return new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    @Provides
    @Singleton
    GoogleApiClient.Builder googleApiClientBuilder(Context context, GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient
                .Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions);
    }

}
