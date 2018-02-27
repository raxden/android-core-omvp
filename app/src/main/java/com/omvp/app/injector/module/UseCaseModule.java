package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;
import com.omvp.domain.interactor.impl.GetSampleListUseCaseImpl;
import com.omvp.domain.interactor.impl.GetSampleUseCaseImpl;
import com.omvp.domain.repository.SampleRepository;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class UseCaseModule {

    @Provides
    @PerActivity
    static GetSampleUseCase getSampleUseCase(SampleRepository repository) {
        return new GetSampleUseCaseImpl(repository);
    }

    @Provides
    @PerActivity
    static GetSampleListUseCase getSampleListUseCase(SampleRepository repository) {
        return new GetSampleListUseCaseImpl(repository);
    }

}
