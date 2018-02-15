package com.raxdenstudios.domain.interactor.impl;

import com.raxdenstudios.domain.SampleDomain;
import com.raxdenstudios.domain.interactor.RemoveSampleUseCase;
import com.raxdenstudios.domain.repository.SampleRepository;

import io.reactivex.Completable;


public class RemoveSampleUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements RemoveSampleUseCase {

    public RemoveSampleUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Completable execute(SampleDomain domain) {
        return getRepository().remove(domain);
    }

}
