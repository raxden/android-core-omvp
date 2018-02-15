package com.raxdenstudios.data.manager;

import com.raxdenstudios.domain.Credentials;
import com.raxdenstudios.preferences.AdvancedPreferences;

public class CredentialsManager {

    private final AdvancedPreferences preferences;

    public CredentialsManager(AdvancedPreferences preferences) {
        this.preferences = preferences;
    }

    public Credentials retrieve() {
        return preferences.get(Credentials.class.getSimpleName(), Credentials.class, new Credentials());
    }

    public void persist(Credentials credentials) {
        preferences.put(Credentials.class.getSimpleName(), credentials);
        preferences.commit();
    }

}
