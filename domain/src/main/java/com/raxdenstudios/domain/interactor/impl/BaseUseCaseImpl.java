package com.raxdenstudios.domain.interactor.impl;

import com.raxdenstudios.domain.repository.Repository;

public abstract class BaseUseCaseImpl<T extends Repository> {

    private final T mRepository;

    public BaseUseCaseImpl(T repository) {
        mRepository = repository;
    }

    public T getRepository() {
        return mRepository;
    }

}
