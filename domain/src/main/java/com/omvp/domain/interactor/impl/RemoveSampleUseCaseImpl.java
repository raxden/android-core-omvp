package com.omvp.domain.interactor.impl;

import com.omvp.domain.interactor.RemoveSampleUseCase;
import com.omvp.domain.repository.SampleRepository;

import io.reactivex.Completable;


public class RemoveSampleUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements RemoveSampleUseCase {

    public RemoveSampleUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Completable execute(long id) {
        return getRepository().remove(id);
    }

}
