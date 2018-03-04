package com.omvp.data.repository;

import com.omvp.domain.Credentials;
import com.omvp.domain.repository.CredentialsRepository;
import com.raxdenstudios.preferences.AdvancedPreferences;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

public class CredentialsRepositoryImpl implements CredentialsRepository {

    private final AdvancedPreferences preferences;

    @Inject
    CredentialsRepositoryImpl(AdvancedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public Completable persist(final Credentials credentials) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        preferences.put(Credentials.class.getSimpleName(), credentials);
                        preferences.commit();
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
                    Credentials credentials = preferences.get(Credentials.class.getSimpleName(), Credentials.class, new Credentials());
                    emitter.onSuccess(credentials);
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

}
