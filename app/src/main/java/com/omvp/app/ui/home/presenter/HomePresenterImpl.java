
package com.omvp.app.ui.home.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.home.view.HomeView;

import javax.inject.Inject;

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {

    @Inject
    public HomePresenterImpl(HomeView homeView) {
        super(homeView);
    }

}
