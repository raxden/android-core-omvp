package com.omvp.app.injector.module;

import android.app.Activity;
import android.app.Fragment;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.interceptor.ToolbarActivityInterceptor;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptor;
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptor;
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.network.NetworkActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.network.NetworkInterceptor;
import com.raxdenstudios.square.interceptor.commons.network.NetworkInterceptorCallback;
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

    @Provides
    @PerActivity
    static FragmentStatePagerInterceptor fragmentStatePagerInterceptor(Activity activity){
        if (activity instanceof FragmentStatePagerInterceptorCallback){
            return new FragmentStatePagerActivityInterceptor(activity, (FragmentStatePagerInterceptorCallback) activity);
        }
        return null;
    }

    @Provides
    @PerActivity
    static InjectFragmentListInterceptor injectFragmentListInterceptor(Activity activity){
        if (activity instanceof InjectFragmentListInterceptorCallback){
            return new InjectFragmentListActivityInterceptor(activity, (InjectFragmentListInterceptorCallback) activity);
        }
        return null;
    }
}
