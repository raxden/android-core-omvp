package com.raxdenstudios.data.repository;

import com.raxdenstudios.data.entity.SampleEntity;
import com.raxdenstudios.data.network.gateway.AppGateway;
import com.raxdenstudios.data.repository.mapper.SampleEntityDataMapper;
import com.raxdenstudios.domain.SampleDomain;
import com.raxdenstudios.domain.repository.SampleRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Angel on 15/02/2018.
 */

public class SampleRepositoryImpl extends BaseRepositoryImpl<SampleEntityDataMapper> implements SampleRepository {

    private final AppGateway gateway;

    public SampleRepositoryImpl(AppGateway gateway, SampleEntityDataMapper entityDataMappger) {
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
