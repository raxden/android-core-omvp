package com.omvp.data.repository;

import com.omvp.data.manager.CredentialsManager;
import com.omvp.domain.Credentials;
import com.omvp.domain.repository.CredentialsRepository;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Angel on 15/02/2018.
 */

public class CredentialsRepositoryImpl implements CredentialsRepository {

    private final CredentialsManager credentialsManager;

    public CredentialsRepositoryImpl(CredentialsManager credentialsManager) {
        this.credentialsManager = credentialsManager;
    }

    @Override
    public Completable persist(final Credentials credentials) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        credentialsManager.persist(credentials);
                        emitter.onComplete();
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    @Override
    public Single<Credentials> retrieve() {
        return Single.create(new SingleOnSubscribe<Credentials>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Credentials> emitter) throws Exception {
                try {
                    emitter.onSuccess(credentialsManager.retrieve());
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

}
