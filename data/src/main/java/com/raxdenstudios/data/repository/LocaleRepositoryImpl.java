package com.raxdenstudios.data.repository;

import com.raxdenstudios.data.manager.LocaleManager;
import com.raxdenstudios.domain.repository.LocaleRepository;

import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Angel on 15/02/2018.
 */

public class LocaleRepositoryImpl implements LocaleRepository {

    private final LocaleManager localeManager;

    public LocaleRepositoryImpl(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    @Override
    public Maybe<List<Locale>> retrieveAvailableLocaleList() {
        return Maybe.create(new MaybeOnSubscribe<List<Locale>>() {
            @Override
            public void subscribe(MaybeEmitter<List<Locale>> emitter) throws Exception {
                try {
                    emitter.onSuccess(localeManager.getAvailableLocaleList());
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    @Override
    public Completable save(final Locale locale) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        localeManager.setLocale(locale);
                        emitter.onComplete();
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    @Override
    public Single<Locale> retrieve() {
        return Single.create(new SingleOnSubscribe<Locale>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Locale> emitter) throws Exception {
                try {
                    emitter.onSuccess(localeManager.getLocale());
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

}
