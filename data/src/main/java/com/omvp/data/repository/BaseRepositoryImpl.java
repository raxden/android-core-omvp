package com.omvp.data.repository;

import com.omvp.commons.DataMapper;

/**
 * Created by Angel on 15/02/2018.
 */

public abstract class BaseRepositoryImpl<T extends DataMapper> {

    private final T dataMappger;

    public BaseRepositoryImpl(T dataMapper) {
        this.dataMappger = dataMapper;
    }

    public T getDataMapper() {
        return dataMappger;
    }

}
