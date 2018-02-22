package com.omvp.app.injector.module;

import android.app.DialogFragment;
import android.app.Fragment;

import com.omvp.app.injector.scope.PerFragment;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewDialogFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.handlearguments.HandleArgumentsDialogFragmentInterceptor;
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
        if (!(fragment instanceof AutoInflateViewInterceptorCallback)) return null;
        if (fragment instanceof DialogFragment) {
            return new AutoInflateViewDialogFragmentInterceptor((DialogFragment) fragment, (AutoInflateViewInterceptorCallback) fragment);
        } else {
            return new AutoInflateViewFragmentInterceptor(fragment, (AutoInflateViewInterceptorCallback) fragment);
        }
    }

    @Provides
    @PerFragment
    static HandleArgumentsInterceptor handleArgumentsInterceptor(Fragment fragment) {
        if (!(fragment instanceof HandleArgumentsInterceptorCallback)) return null;
        if (fragment instanceof DialogFragment) {
            return new HandleArgumentsDialogFragmentInterceptor((DialogFragment) fragment, (HandleArgumentsInterceptorCallback) fragment);
        } else {
            return new HandleArgumentsFragmentInterceptor(fragment, (HandleArgumentsInterceptorCallback) fragment);
        }
    }

}
