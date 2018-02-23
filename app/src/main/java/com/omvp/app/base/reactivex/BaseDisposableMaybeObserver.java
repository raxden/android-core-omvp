package com.omvp.app.base.reactivex;

import android.app.Fragment;
import android.content.Context;

import com.omvp.app.util.ErrorManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Angel on 10/08/2017.
 */
public abstract class BaseDisposableMaybeObserver<T> extends DisposableMaybeObserver<T> {

    private ErrorManager mErrorManager;

    public BaseDisposableMaybeObserver(Context context) {
        mErrorManager = new ErrorManager(context.getResources());
    }

    public BaseDisposableMaybeObserver(Fragment fragment) {
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
