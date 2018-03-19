package com.omvp.app.ui.samples.sample_take_picture;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.sample_take_picture.presenter.SampleTakePicturePresenterImpl;
import com.omvp.app.ui.samples.sample_take_picture.view.SampleTakePictureView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SampleTakePicturePresenterTest extends BaseTest {

    @Mock
    SampleTakePictureView view;

    private SampleTakePicturePresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SampleTakePicturePresenterImpl(view));

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
