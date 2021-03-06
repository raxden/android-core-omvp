package com.omvp.domain.interactor.impl;

import com.omvp.domain.SampleDomain;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.repository.SampleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class GetSampleListUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements GetSampleListUseCase {

    @Inject
    GetSampleListUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Maybe<List<SampleDomain>> execute() {
        return getRepository().retrieveList();
    }

}
