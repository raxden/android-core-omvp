package com.omvp.app.base.reactivex;

import android.app.Fragment;
import android.content.Context;

import com.omvp.app.util.ErrorManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Created by Ángel Gómez on 30/07/2017.
 */
public abstract class BaseDisposableSingleObserver<T> extends DisposableSingleObserver<T> {

    private ErrorManager mErrorManager;

    public BaseDisposableSingleObserver(Context context) {
        mErrorManager = new ErrorManager(context.getResources());
    }

    public BaseDisposableSingleObserver(Fragment fragment) {
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
