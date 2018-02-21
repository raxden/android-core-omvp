
package com.omvp.app.ui.splash.presenter;

import android.content.Context;

import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.splash.view.SplashView;

import javax.inject.Inject;

/**
 * Created by Angel on 21/02/2018.
 */
@PerFragment
public class SplashPresenterImpl extends BasePresenter<SplashView> implements SplashPresenter {

    @Inject
    SplashPresenterImpl(Context context, SplashView splashView) {
        super(context, splashView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
    }

}
