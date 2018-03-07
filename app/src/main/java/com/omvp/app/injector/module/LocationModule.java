package com.omvp.app.injector.module;

import com.google.android.gms.location.LocationRequest;
import com.omvp.commons.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {

    @Provides
    @Singleton
    LocationRequest provideHighLocationRequest() {
        return LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                .setExpirationDuration(TimeUnit.SECONDS.toMillis(Constants.LOCATION_TIMEOUT_IN_SECONDS))
//                .setNumUpdates(Constants.LOCATION_UPDATES)
                .setInterval(Constants.LOCATION_INTERVAL);
    }

}
