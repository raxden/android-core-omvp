package com.omvp.app.ui.home;

import com.omvp.app.R;
import com.omvp.app.ui.BaseTest;
import com.omvp.app.ui.home.view.HomeFragment;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

public class HomeFragmentTest extends BaseTest {

    HomeActivity mActivity;

    HomeFragment mFragment;

    @Before
    public void setUp() {

        mActivity = Robolectric.setupActivity(HomeActivity.class);

        mFragment = (HomeFragment) mActivity.getFragmentManager().findFragmentById(R.id.content);

        startFragment(mFragment);

    }

    @Test
    public void check_Activity_NotNull() {
        assertNotNull(mActivity);
    }

    @Test
    public void check_Fragment_notNull () {
        assertThat(mFragment, is(notNullValue()));
    }


}
