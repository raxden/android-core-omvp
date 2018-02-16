package com.omvp.data.network.gateway.retrofit;

import android.content.Context;

import com.omvp.data.network.gateway.retrofit.service.AppRetrofitService;
import com.omvp.data.entity.SampleEntity;
import com.omvp.data.network.gateway.AppGateway;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Angel on 15/02/2018.
 */

public class AppRetrofitGatewayImpl implements AppGateway {

    private final Context context;
    private final AppRetrofitService service;

    public AppRetrofitGatewayImpl(Context context, AppRetrofitService service) {
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
