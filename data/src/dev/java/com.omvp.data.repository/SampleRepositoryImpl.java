package com.omvp.data.repository;

import com.omvp.data.entity.SampleEntity;
import com.omvp.data.entity.mapper.SampleEntityDataMapper;
import com.omvp.data.network.gateway.AppGateway;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.repository.SampleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class SampleRepositoryImpl extends BaseRepositoryImpl<SampleEntityDataMapper> implements SampleRepository {

    private final AppGateway gateway;

    @Inject
    SampleRepositoryImpl(AppGateway gateway, SampleEntityDataMapper entityDataMappger) {
        super(entityDataMappger);
        this.gateway = gateway;
    }

    @Override
    public Single<SampleDomain> retrieve(long id) {
        return gateway.retrieve(Long.toString(id))
                .map(new Function<SampleEntity, SampleDomain>() {
                    @Override
                    public SampleDomain apply(SampleEntity sampleEntity) throws Exception {
                        return getDataMapper().transform(sampleEntity);
                    }
                });
    }

    @Override
    public Maybe<List<SampleDomain>> retrieveList() {
        return gateway.retrieveList()
                .map(new Function<List<SampleEntity>, List<SampleDomain>>() {
                    @Override
                    public List<SampleDomain> apply(List<SampleEntity> sampleEntities) throws Exception {
                        return getDataMapper().transform(sampleEntities);
                    }
                });
    }

    @Override
    public Completable persist(SampleDomain sampleDomain) {
        return gateway.persist(getDataMapper().inverseTransform(sampleDomain));
    }

    @Override
    public Completable remove(long id) {
        return gateway.remove(Long.toString(id));
    }

}
