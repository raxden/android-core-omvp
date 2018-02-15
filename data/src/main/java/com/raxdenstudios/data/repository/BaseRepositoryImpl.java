package com.raxdenstudios.data.repository;

import com.raxdenstudios.commons.ModelDataMapper;

/**
 * Created by Angel on 15/02/2018.
 */

public abstract class BaseRepositoryImpl<T extends ModelDataMapper> {

    private final T dataMappger;

    public BaseRepositoryImpl(T dataMapper) {
        this.dataMappger = dataMapper;
    }

    public T getDataMapper() {
        return dataMappger;
    }

}
