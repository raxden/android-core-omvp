package com.omvp.data.repository;

import android.text.TextUtils;

import com.omvp.domain.repository.LocaleRepository;
import com.raxdenstudios.preferences.AdvancedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

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

public class LocaleRepositoryImpl implements LocaleRepository {

    private static final String PREF_LANGUAGE_SELECTED = "pref_language_selected";
    private static final String PREF_AVAILABLE_LANGUAGE_LIST = "pref_language_list";

    private final AdvancedPreferences preferences;
    private final Set<Locale> availableLocaleList;
    private final Locale defaultLocale;

    @Inject
    LocaleRepositoryImpl(AdvancedPreferences preferences, @Named("default") Locale defaultLocale, Set<Locale> availableLocaleList) {
        this.preferences = preferences;
        this.defaultLocale = defaultLocale;
        this.availableLocaleList = availableLocaleList;
    }

    @Override
    public Maybe<List<Locale>> retrieveList() {
        return Maybe.create(new MaybeOnSubscribe<List<Locale>>() {
            @Override
            public void subscribe(MaybeEmitter<List<Locale>> emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        List<Locale> localeList = retrieveLocaleList();
                        if (!localeList.isEmpty()) {
                            emitter.onSuccess(localeList);
                        } else {
                            emitter.onComplete();
                        }
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    @Override
    public Completable persist(final Locale locale) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        persistLocale(locale);
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
                    if (!emitter.isDisposed()) {
                        emitter.onSuccess(retrieveLocale());
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    private Locale retrieveLocale() {
        Locale locale = null;
        String language = preferences.get(PREF_LANGUAGE_SELECTED, "");
        if (!TextUtils.isEmpty(language)) {
            String[] values = language.split("_");
            locale = new Locale(values[0], values[1]);
        } else {
            Locale defaultDeviceLocale = Locale.getDefault();
            List<Locale> availableLocaleList = retrieveLocaleList();
            for (Locale availableLocale : availableLocaleList) {
                if (defaultDeviceLocale.getLanguage().equals(availableLocale.getLanguage())) {
                    locale = defaultDeviceLocale;
                }
            }
            if (locale == null) {
                locale = defaultLocale;
            }
            preferences.put(PREF_LANGUAGE_SELECTED, locale.toString());
            preferences.commit();
        }
        return locale;
    }

    private List<Locale> retrieveLocaleList() {
        List<Locale> localeList = new ArrayList<>();
        Set<String> languageList = preferences.get(PREF_AVAILABLE_LANGUAGE_LIST, new HashSet<String>());
        if (languageList.isEmpty()) {
            for (Locale locale : availableLocaleList) {
                languageList.add(locale.toString());
            }
            preferences.put(PREF_AVAILABLE_LANGUAGE_LIST, languageList);
            preferences.commit();
        }
        for (String language : languageList) {
            String[] values = language.split("_");
            localeList.add(new Locale(values[0], values[1]));
        }
        return localeList;
    }

    private void persistLocale(Locale locale) {
        preferences.put(PREF_LANGUAGE_SELECTED, locale.toString());
        preferences.commit();
    }

}
