package com.raxdenstudios.domain.interactor;

import com.raxdenstudios.domain.SampleDomain;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Angel on 05/09/2017.
 */

public interface GetSampleListUseCase {

    Maybe<List<SampleDomain>> execute();

}
