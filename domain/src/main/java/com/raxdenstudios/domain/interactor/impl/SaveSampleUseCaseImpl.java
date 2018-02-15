package com.raxdenstudios.domain.interactor.impl;

import com.raxdenstudios.domain.SampleDomain;
import com.raxdenstudios.domain.interactor.SaveSampleUseCase;
import com.raxdenstudios.domain.repository.SampleRepository;

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
