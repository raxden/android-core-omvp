package com.omvp.app.injector.component;

import android.content.Context;

import com.omvp.app.injector.module.AnalyticsModule;
import com.omvp.app.injector.module.ApplicationModule;
import com.omvp.app.injector.module.ModelMapperModule;
import com.omvp.app.injector.module.NetworkModule;
import com.omvp.app.utils.TrackerManager;
import com.omvp.data.manager.CredentialsManager;
import com.omvp.data.manager.LocaleManager;
import com.omvp.data.network.gateway.AppGateway;
import com.raxdenstudios.preferences.AdvancedPreferences;

import org.modelmapper.ModelMapper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ángel Gómez on 16/02/2018.
 */

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetworkModule.class,
                AnalyticsModule.class,
                ModelMapperModule.class
        }
)
public interface ApplicationComponent {

        // To continuation exposed to sub-graphs.

        Context getContext();

        ModelMapper getModelMapper();

        CredentialsManager getCredentialsManager();

        LocaleManager getLocaleManager();

        TrackerManager getTrackerManager();

        AdvancedPreferences getAdvancedPreferences();

        AppGateway getAppGateway();

}
