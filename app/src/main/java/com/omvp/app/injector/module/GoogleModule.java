package com.omvp.app.injector.module;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.omvp.app.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GoogleModule {

    @Provides
    @Singleton
    GoogleSignInOptions provideGoogleSignInOptions(Context context) {
        return new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

}
