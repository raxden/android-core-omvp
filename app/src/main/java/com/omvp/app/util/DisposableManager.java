package com.omvp.app.util;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ángel Gómez on 23/02/2018.
 */
public class DisposableManager {

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public DisposableManager() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void dispose() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public boolean isEmpty() {
        return mCompositeDisposable.size() == 0;
    }

    public void add(Disposable disposable) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    public void remove(Disposable disposable) {
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
            if (mCompositeDisposable != null) {
                mCompositeDisposable.remove(disposable);
            }
        }
    }

    public void removeAll() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

}
