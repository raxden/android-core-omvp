package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.data.network.gateway.AppGateway;
import com.omvp.data.repository.SampleRepositoryImpl;
import com.omvp.data.repository.mapper.SampleEntityDataMapper;
import com.omvp.domain.repository.SampleRepository;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepositoryModule {

    @Provides
    @PerActivity
    static SampleRepository sampleRepository(AppGateway gateway, SampleEntityDataMapper entityDataMapper) {
        return new SampleRepositoryImpl(gateway, entityDataMapper);
    }

}
