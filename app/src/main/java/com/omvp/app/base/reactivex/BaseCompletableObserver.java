package com.omvp.app.base.reactivex;

import android.app.Fragment;
import android.content.Context;

import com.omvp.app.util.ErrorManager;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import timber.log.Timber;

/**
 * Created by Angel on 10/08/2017.
 */
public abstract class BaseCompletableObserver implements CompletableObserver {

    private ErrorManager mErrorManager;

    public BaseCompletableObserver(Context context) {
        mErrorManager = new ErrorManager(context.getResources());
    }

    public BaseCompletableObserver(Fragment fragment) {
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
