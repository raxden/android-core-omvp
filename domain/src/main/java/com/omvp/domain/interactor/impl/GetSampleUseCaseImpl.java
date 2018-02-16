package com.omvp.domain.interactor.impl;

import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleUseCase;
import com.omvp.domain.repository.SampleRepository;

import io.reactivex.Single;


public class GetSampleUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements GetSampleUseCase {

    public GetSampleUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Single<SampleDomain> execute(long id) {
        return getRepository().retrieve(id);
    }

}
