package com.raxdenstudios.domain.interactor.impl;

import com.raxdenstudios.domain.SampleDomain;
import com.raxdenstudios.domain.interactor.GetSampleUseCase;
import com.raxdenstudios.domain.repository.SampleRepository;

import io.reactivex.Single;


public class GetSampleUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements GetSampleUseCase {

    public GetSampleUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Single<SampleDomain> execute() {
        return getRepository().retrieve();
    }

}
