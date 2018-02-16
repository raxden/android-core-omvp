package com.omvp.domain.interactor.impl;

import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.SaveSampleUseCase;
import com.omvp.domain.repository.SampleRepository;

import io.reactivex.Completable;


public class SaveSampleUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements SaveSampleUseCase {

    public SaveSampleUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Completable execute(SampleDomain domain) {
        return getRepository().persist(domain);
    }

}
