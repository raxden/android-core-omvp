package com.omvp.app.injector.module;

import android.app.Activity;

import com.omvp.app.injector.scope.PerActivity;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.network.NetworkActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.network.NetworkInterceptor;
import com.raxdenstudios.square.interceptor.commons.network.NetworkInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public abstract class InterceptorActivityModule {

    @Provides
    @PerActivity
    static AutoInflateLayoutInterceptor autoInflateLayoutInterceptor(Activity activity) {
        if (activity instanceof AutoInflateLayoutInterceptorCallback) {
            return new AutoInflateLayoutActivityInterceptor(activity, (AutoInflateLayoutInterceptorCallback) activity);
        }
        return null;
    }

    @Provides
    @PerActivity
    static InjectFragmentInterceptor injectFragmentInterceptor(Activity activity) {
        if (activity instanceof InjectFragmentInterceptorCallback) {
            return new InjectFragmentActivityInterceptor(activity, (InjectFragmentInterceptorCallback) activity);
        }
        return null;
    }

    @Provides
    @PerActivity
    static NetworkInterceptor networkInterceptor(Activity activity) {
        if (activity instanceof NetworkInterceptorCallback) {
            return new NetworkActivityInterceptor(activity, (NetworkInterceptorCallback) activity);
        }
        return null;
    }

    @Provides
    @PerActivity
    static ToolbarInterceptor toolbarInterceptor(Activity activity) {
        if (activity instanceof ToolbarInterceptorCallback) {
            return new ToolbarActivityInterceptor(activity, (ToolbarInterceptorCallback) activity);
        }
        return null;
    }

}
