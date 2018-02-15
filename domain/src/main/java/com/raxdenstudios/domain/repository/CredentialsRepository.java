package com.raxdenstudios.domain.repository;

import com.raxdenstudios.domain.Credentials;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CredentialsRepository extends Repository {

    Completable persist(final Credentials credentials);

    Single<Credentials> retrieve();

}
