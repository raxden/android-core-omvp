package com.omvp.app.suites;


import com.omvp.app.ui.samples.sample.SamplePresenterTest;
import com.omvp.app.ui.samples.sample_list.SampleListPresenterTest;
import com.omvp.app.ui.samples.sample_multiple.SampleBottomPresenterTest;
import com.omvp.app.ui.samples.sample_multiple.SampleTopPresenterTest;
import com.omvp.app.ui.samples.sample_pager.SamplePagerPresenterTest;
import com.omvp.app.ui.samples.sample_take_picture.SampleTakePicturePresenterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SamplePresenterTest.class,
        SampleListPresenterTest.class,
        SampleBottomPresenterTest.class,
        SampleTopPresenterTest.class,
        SamplePagerPresenterTest.class,
        SampleTakePicturePresenterTest.class

})
public class SamplesPresentersTestSuite {}
