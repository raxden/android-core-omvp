package com.raxdenstudios.domain.interactor;

import com.raxdenstudios.domain.SampleDomain;

import io.reactivex.Completable;

/**
 * Created by Angel on 05/09/2017.
 */

public interface SaveSampleUseCase {

    Completable execute(SampleDomain sampleDomain);

}
