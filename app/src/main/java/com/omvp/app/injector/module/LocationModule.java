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
    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    LocationRequest locationRequest() {
        return LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                // Sets the desired interval for active location updates. This interval is
                // inexact. You may not receive updates at all if no location sources are available, or
                // you may receive them slower than requested. You may also receive updates faster than
                // requested if other applications are requesting location at a faster interval.
                .setInterval(Constants.LOCATION_INTERVAL)
                // Sets the fastest rate for active location updates. This interval is exact, and your
                // application will never receive updates faster than this value.
                .setFastestInterval(Constants.LOCATION_FASTEST_INTERVAL);
    }

}
