package com.omvp.app.injector.module;

import android.app.Activity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.omvp.app.injector.scope.PerActivity;
import com.omvp.app.interceptor.ToolbarActivityInterceptor;
import com.omvp.app.interceptor.google.GoogleApiClientActivityInterceptor;
import com.omvp.app.interceptor.google.GoogleApiClientInterceptor;
import com.omvp.app.interceptor.google.GoogleApiClientInterceptorCallback;
import com.omvp.app.interceptor.location.LocationActivityInterceptor;
import com.omvp.app.interceptor.location.LocationInterceptor;
import com.omvp.app.interceptor.operation.OperationBroadcastActivityInterceptor;
import com.omvp.app.interceptor.operation.OperationBroadcastInterceptor;
import com.omvp.app.interceptor.takePicture.TakePictureActivityInterceptor;
import com.omvp.app.interceptor.takePicture.TakePictureInterceptor;
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


    @Provides
    @PerActivity
    static LocationInterceptor locationInterceptor(Activity activity, LocationRequest locationRequest) {
        return new LocationActivityInterceptor(activity, locationRequest);
    }

    @Provides
    @PerActivity
    static GoogleApiClientInterceptor googleApiClientInterceptor(Activity activity, GoogleApiClient.Builder builder) {
        if (activity instanceof GoogleApiClientInterceptorCallback) {
            return new GoogleApiClientActivityInterceptor(activity, builder, (GoogleApiClientInterceptorCallback) activity);
        } else {
            return null;
        }
    }

    @Provides
    @PerActivity
    static TakePictureInterceptor galleryInterceptor(Activity activity) {
        return new TakePictureActivityInterceptor(activity);
    }
  
    @Provides
    @PerActivity
    static OperationBroadcastInterceptor operationBroadcastInterceptor(Activity activity) {
        return new OperationBroadcastActivityInterceptor(activity);
    }

}
