package com.omvp.domain.interactor;

import com.omvp.domain.SampleDomain;

import io.reactivex.Single;

/**
 * Created by Angel on 05/09/2017.
 */

public interface GetSampleUseCase {

    Single<SampleDomain> execute(final long id);

}
