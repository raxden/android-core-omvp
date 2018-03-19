
package com.omvp.app.ui.splash.presenter;

import android.os.Handler;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableCompletableObserver;
import com.omvp.app.ui.splash.view.SplashView;
import com.omvp.commons.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenterImpl extends BasePresenter<SplashView> implements SplashPresenter {

    private float mProgress;
    private Handler mHandler;
    private Runnable mProgressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mProgress < 100.0f) {
                showProgress(mProgress++);
                mHandler.postDelayed(mProgressRunnable, 50);
            } else {
                mHandler.removeCallbacks(mProgressRunnable);
            }
        }
    };

    @Inject
    public SplashPresenterImpl(SplashView splashView) {
        super(splashView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
        prepareApplicationToLaunch();
    }

    @Override
    public void onDropView() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mProgressRunnable);
            mHandler = null;
        }
        super.onDropView();
    }

    // Support methods =============================================================================

    private void prepareApplicationToLaunch() {
        mDisposableManager.add(makeTime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableCompletableObserver(mContext) {
                    @Override
                    protected void onStart() {
                        initProgressBar();
                        showProgress();
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                        applicationReadyToLaunch();
                    }
                }));
    }

    private Completable makeTime() {
        return Completable.timer(Constants.SPLASH_DELAY, TimeUnit.MILLISECONDS);
    }

    private void initProgressBar() {
        mHandler = new Handler();
        mHandler.post(mProgressRunnable);
    }

    private void applicationReadyToLaunch() {
        mView.applicationReadyToLaunch();
    }

}
