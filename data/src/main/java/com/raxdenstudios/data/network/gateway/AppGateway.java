package com.raxdenstudios.data.network.gateway;

import com.raxdenstudios.data.entity.SampleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface AppGateway {

    // Sample Flow

    Single<SampleEntity> retrieve(String id);

    Maybe<List<SampleEntity>> retrieveList();

    Completable persist(SampleEntity sampleEntity);

    Completable remove(String id);

}
