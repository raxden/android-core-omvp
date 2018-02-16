package com.omvp.domain.repository;

import com.omvp.domain.Credentials;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CredentialsRepository extends Repository {

    Completable persist(final Credentials credentials);

    Single<Credentials> retrieve();

}
