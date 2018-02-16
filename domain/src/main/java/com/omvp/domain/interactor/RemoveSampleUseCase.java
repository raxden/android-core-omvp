package com.omvp.domain.interactor;

import io.reactivex.Completable;

/**
 * Created by Angel on 05/09/2017.
 */

public interface RemoveSampleUseCase {

    Completable execute(long id);

}
