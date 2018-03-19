package com.omvp.app.ui.splash;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.splash.presenter.SplashPresenterImpl;
import com.omvp.app.ui.splash.view.SplashView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SplashPresenterTest extends BaseTest {

    @Mock
    SplashView view;

    private SplashPresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SplashPresenterImpl(view));

    }

    @Test
    public void check_presenter_not_null () {
        assertNotNull(presenter);
    }


    @Test
    public void check_view_not_null () {
        assertNotNull(view);
    }
}
