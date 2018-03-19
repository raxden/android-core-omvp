package com.omvp.app.ui.samples.sample_multiple;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.sample_multiple.top.presenter.SampleTopPresenterImpl;
import com.omvp.app.ui.samples.sample_multiple.top.view.SampleTopView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SampleTopPresenterTest extends BaseTest {

    @Mock
    SampleTopView view;

    private SampleTopPresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SampleTopPresenterImpl(view));

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
