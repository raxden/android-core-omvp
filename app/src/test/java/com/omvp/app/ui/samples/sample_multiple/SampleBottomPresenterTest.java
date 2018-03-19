package com.omvp.app.ui.samples.sample_multiple;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.sample_multiple.bottom.presenter.SampleBottomPresenterImpl;
import com.omvp.app.ui.samples.sample_multiple.bottom.view.SampleBottomView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SampleBottomPresenterTest extends BaseTest {

    @Mock
    SampleBottomView view;

    private SampleBottomPresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SampleBottomPresenterImpl(view));

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
