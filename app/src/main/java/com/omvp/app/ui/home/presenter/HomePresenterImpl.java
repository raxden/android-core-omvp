
package com.omvp.app.ui.home.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.ui.home.view.HomeView;

import javax.inject.Inject;

/**
 * Created by Angel on 21/02/2018.
 */
@PerFragment
public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {

    @Inject
    HomePresenterImpl(HomeView homeView) {
        super(homeView);
    }

}
