package com.omvp.data.network.gateway.retrofit.service;

import com.omvp.data.entity.SampleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Angel on 15/02/2018.
 */

public interface AppRetrofitService {

    // Sample Flow

    @GET("sample/{id}")
    Single<SampleEntity> retrieve(
            @Path("id") final String id
    );

    @GET("sample/list")
    Maybe<List<SampleEntity>> retrieveList();

    @POST("sample")
    Completable persist(
            @Body final SampleEntity sampleEntity
    );

    @DELETE("sample/{id}")
    Completable remove(
            @Path("id") final String id
    );

}
