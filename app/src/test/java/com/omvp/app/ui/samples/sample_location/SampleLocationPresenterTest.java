package com.omvp.app.ui.samples.sample_location;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.sample_location.presenter.SampleLocationPresenterImpl;
import com.omvp.app.ui.samples.sample_location.view.SampleLocationView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SampleLocationPresenterTest extends BaseTest {

    @Mock
    SampleLocationView view;

    private SampleLocationPresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SampleLocationPresenterImpl(view));

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
