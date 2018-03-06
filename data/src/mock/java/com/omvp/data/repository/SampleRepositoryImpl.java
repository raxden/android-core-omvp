package com.omvp.data.repository;

import com.omvp.data.StaticRepository;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.repository.SampleRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class SampleRepositoryImpl implements SampleRepository {

    @Inject
    SampleRepositoryImpl() {
       StaticRepository.init();
    }

    @Override
    public Single<SampleDomain> retrieve(long id) {
        return Single.just(StaticRepository.sampleDomainList.get(id));
    }

    @Override
    public Maybe<List<SampleDomain>> retrieveList() {
        List<SampleDomain> list = new ArrayList<>(StaticRepository.sampleDomainList.values());
        return Maybe.just(list);
    }

    @Override
    public Completable persist(SampleDomain sampleDomain) {
        StaticRepository.sampleDomainList.put(sampleDomain.getId(), sampleDomain);
        return Completable.complete();
    }

    @Override
    public Completable remove(long id) {
        StaticRepository.sampleDomainList.remove(id);
        return Completable.complete();
    }

}
