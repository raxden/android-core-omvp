package com.omvp.app.ui.samples.sample_list;


import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.samples.sample_list.presenter.SampleListPresenterImpl;
import com.omvp.app.ui.samples.sample_list.view.SampleListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

public class SampleListPresenterTest extends BaseTest {

    @Mock
    SampleListView view;

    private SampleListPresenterImpl presenter;


    @Before
    public void setup () throws Exception {
        presenter = Mockito.spy(new SampleListPresenterImpl(view));

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
