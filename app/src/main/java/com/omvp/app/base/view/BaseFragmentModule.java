package com.omvp.app.base.view;

import android.app.Fragment;

import dagger.Module;

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of {@link Fragment}.
 */
@Module
public abstract class BaseFragmentModule {

}
