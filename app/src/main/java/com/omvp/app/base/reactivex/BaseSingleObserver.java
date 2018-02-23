package com.omvp.app.base.reactivex;

import android.app.Fragment;
import android.content.Context;

import com.omvp.app.util.ErrorManager;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import timber.log.Timber;

/**
 * Created by Ángel Gómez on 30/07/2017.
 */
public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private ErrorManager mErrorManager;

    public BaseSingleObserver(Context context) {
        mErrorManager = new ErrorManager(context.getResources());
    }

    public BaseSingleObserver(Fragment fragment) {
        mErrorManager = new ErrorManager(fragment.getResources());
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Timber.e(e);
        mErrorManager.parseError(e);
        onError(mErrorManager.getCode(), mErrorManager.getTitle(), mErrorManager.getMessage());
    }

    protected abstract void onError(int code, String title, String description);

}
