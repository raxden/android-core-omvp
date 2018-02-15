package com.raxdenstudios.domain.interactor.impl;

import com.raxdenstudios.domain.SampleDomain;
import com.raxdenstudios.domain.interactor.GetSampleListUseCase;
import com.raxdenstudios.domain.repository.SampleRepository;

import java.util.List;

import io.reactivex.Maybe;


public class GetSampleListUseCaseImpl extends BaseUseCaseImpl<SampleRepository> implements GetSampleListUseCase {

    public GetSampleListUseCaseImpl(SampleRepository repository) {
        super(repository);
    }

    @Override
    public Maybe<List<SampleDomain>> execute() {
        return getRepository().retrieveList();
    }

}
