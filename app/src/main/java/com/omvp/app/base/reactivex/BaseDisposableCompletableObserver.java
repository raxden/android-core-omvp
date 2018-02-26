package com.omvp.app.base.reactivex;

import android.app.Fragment;
import android.content.Context;

import com.omvp.app.util.ErrorManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import timber.log.Timber;

/**
 * Created by Angel on 10/08/2017.
 */
public abstract class BaseDisposableCompletableObserver extends DisposableCompletableObserver {

    private ErrorManager mErrorManager;

    public BaseDisposableCompletableObserver(Context context) {
        mErrorManager = new ErrorManager(context.getResources());
    }

    public BaseDisposableCompletableObserver(Fragment fragment) {
        mErrorManager = new ErrorManager(fragment.getResources());
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        Timber.e(e);
        mErrorManager.parseError(e);
        onError(mErrorManager.getCode(), mErrorManager.getTitle(), mErrorManager.getMessage());
    }

    protected abstract void onError(int code, String title, String description);

}
