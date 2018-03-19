package com.omvp.app.ui.samples.sample;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.sample.presenter.SamplePresenterImpl;
import com.omvp.app.ui.samples.sample.view.SampleView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SamplePresenterTest extends BaseTest {

    @Mock
    SampleView view;

    private SamplePresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SamplePresenterImpl(view));

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
