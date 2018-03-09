package com.omvp.app.ui.home;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.home.presenter.HomePresenterImpl;
import com.omvp.app.ui.home.view.HomeView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class HomePresenterTest extends BaseTest {

    @Mock
    HomeView view;

    private HomePresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new HomePresenterImpl(view));

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
