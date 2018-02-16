package com.omvp.data.manager;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.preferences.AdvancedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class LocaleManager {

    private static final String PREF_LANGUAGE_SELECTED = "pref_language_selected";
    private static final String PREF_AVAILABLE_LANGUAGE_LIST = "pref_language_list";

    private final AdvancedPreferences preferences;
    private final Set<String> availableLocaleList;

    public LocaleManager(AdvancedPreferences advancedPreferences, Set<String> availableLocaleList) {
        this.preferences = advancedPreferences;
        this.availableLocaleList = availableLocaleList;
    }

    public List<Locale> getAvailableLocaleList() {
        List<Locale> localeList = new ArrayList<>();
        Set<String> languageList = preferences.get(PREF_AVAILABLE_LANGUAGE_LIST, new HashSet<String>());
        if (languageList.isEmpty()) {
            preferences.put(PREF_AVAILABLE_LANGUAGE_LIST, availableLocaleList);
            preferences.commit();
        }
        for (String language : languageList) {
            String[] values = language.split("_");
            localeList.add(new Locale(values[0], values[1]));
        }
        return localeList;
    }

    public Locale getDefaultLocale() {
        String[] values = preferences.get(PREF_LANGUAGE_SELECTED, "es_ES").split("_");
        return new Locale(values[0], values[1]);
    }

    public Locale getLocale() {
        Locale locale = null;
        String language = preferences.get(PREF_LANGUAGE_SELECTED, "");
        if (Utils.hasValue(language)) {
            String[] values = language.split("_");
            locale = new Locale(values[0], values[1]);
        } else {
            Locale defaultAppLocale = getDefaultLocale();
            Locale defaultDeviceLocale = Locale.getDefault();
            List<Locale> availableLocaleList = getAvailableLocaleList();
            for (Locale availableLocale : availableLocaleList) {
                if (defaultDeviceLocale.getLanguage().equals(availableLocale.getLanguage())) {
                    locale = defaultDeviceLocale;
                }
            }
            if (locale == null) {
                locale = defaultAppLocale;
            }
            preferences.put(PREF_LANGUAGE_SELECTED, locale.toString());
            preferences.commit();
        }
        return locale;
    }

    public void setLocale(Locale locale) {
        if (locale != null) {
            preferences.put(PREF_LANGUAGE_SELECTED, locale.toString());
            preferences.commit();
        }
    }

}
