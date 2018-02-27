package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.data.repository.CredentialsRepositoryImpl;
import com.omvp.data.repository.LocaleRepositoryImpl;
import com.omvp.data.repository.SampleRepositoryImpl;
import com.omvp.domain.repository.CredentialsRepository;
import com.omvp.domain.repository.LocaleRepository;
import com.omvp.domain.repository.SampleRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    @PerActivity
    abstract SampleRepository sampleRepository(SampleRepositoryImpl repository);

    @Binds
    @PerActivity
    abstract LocaleRepository localeRepository(LocaleRepositoryImpl repository);

    @Binds
    @PerActivity
    abstract CredentialsRepository credentialsRepository(CredentialsRepositoryImpl repository);

}
