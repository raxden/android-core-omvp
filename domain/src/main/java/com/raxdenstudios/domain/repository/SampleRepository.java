package com.raxdenstudios.domain.repository;

import com.raxdenstudios.domain.SampleDomain;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Angel on 15/02/2018.
 */

public interface SampleRepository extends Repository {

    Single<SampleDomain> retrieve(final long id);

    Maybe<List<SampleDomain>> retrieveList();

    Completable persist(final SampleDomain sampleDomain);

    Completable remove(final long id);

}
