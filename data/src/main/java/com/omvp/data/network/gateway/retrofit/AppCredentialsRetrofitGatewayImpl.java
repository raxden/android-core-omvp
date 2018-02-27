package com.omvp.data.network.gateway.retrofit;

import android.content.Context;

import com.omvp.data.entity.SampleEntity;
import com.omvp.data.network.gateway.AppCredentialsGateway;
import com.omvp.data.network.gateway.retrofit.service.AppCredentialsRetrofitService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class AppCredentialsRetrofitGatewayImpl implements AppCredentialsGateway {

    private final Context context;
    private final AppCredentialsRetrofitService service;

    public AppCredentialsRetrofitGatewayImpl(Context context, AppCredentialsRetrofitService service) {
        this.context = context;
        this.service = service;
    }

    @Override
    public Single<SampleEntity> retrieve(String id) {
        return service.retrieve(id);
    }

    @Override
    public Maybe<List<SampleEntity>> retrieveList() {
        return service.retrieveList();
    }

    @Override
    public Completable persist(SampleEntity sampleEntity) {
        return service.persist(sampleEntity);
    }

    @Override
    public Completable remove(String id) {
        return service.remove(id);
    }

}
