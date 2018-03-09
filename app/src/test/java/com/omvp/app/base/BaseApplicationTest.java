package com.omvp.app.base;


import com.omvp.app.injector.component.DaggerApplicationComponentTest;

public  class BaseApplicationTest extends BaseApplication {

    @Override
    protected void initDaggerApplicationComponent() {
        DaggerApplicationComponentTest.builder().create(this).inject(this);
    }

}
