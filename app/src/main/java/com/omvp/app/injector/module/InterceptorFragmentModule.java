package com.omvp.app.injector.module;

import android.app.Fragment;

import com.omvp.app.injector.scope.PerFragment;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.handlearguments.HandleArgumentsFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.handlearguments.HandleArgumentsInterceptor;
import com.raxdenstudios.square.interceptor.commons.handlearguments.HandleArgumentsInterceptorCallback;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Fragment state and expose it to the graph.
 */
@Module
public abstract class InterceptorFragmentModule {

    @Provides
    @PerFragment
    static AutoInflateViewInterceptor autoInflateViewInterceptor(Fragment fragment) {
        if (fragment instanceof AutoInflateViewInterceptorCallback) {
            return new AutoInflateViewFragmentInterceptor(fragment, (AutoInflateViewInterceptorCallback) fragment);
        } else {
            return null;
        }
    }

    @Provides
    @PerFragment
    static HandleArgumentsInterceptor handleArgumentsInterceptor(Fragment fragment) {
        if (fragment instanceof HandleArgumentsInterceptorCallback) {
            return new HandleArgumentsFragmentInterceptor(fragment, (HandleArgumentsInterceptorCallback) fragment);
        } else {
            return null;
        }
    }

}
