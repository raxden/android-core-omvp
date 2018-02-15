package com.raxdenstudios.domain.interactor;

import com.raxdenstudios.domain.SampleDomain;

import io.reactivex.Single;

/**
 * Created by Angel on 05/09/2017.
 */

public interface GetSampleUseCase {

    Single<SampleDomain> execute();

}
