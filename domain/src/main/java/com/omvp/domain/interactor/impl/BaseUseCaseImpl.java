package com.omvp.domain.interactor.impl;

import com.omvp.domain.repository.Repository;

public abstract class BaseUseCaseImpl<T extends Repository> {

    private final T mRepository;

    public BaseUseCaseImpl(T repository) {
        mRepository = repository;
    }

    public T getRepository() {
        return mRepository;
    }

}
