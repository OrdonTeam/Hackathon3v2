package com.ordonteam.functional

import android.test.ActivityInstrumentationTestCase2
import com.ordonteam.functional.dagger.BaseTestModule
import com.ordonteam.hackathon3.GameActivity
import com.ordonteam.hackathon3.dagger.Injector

class BaseInstrumentationTestCase extends ActivityInstrumentationTestCase2<GameActivity> {

    BaseInstrumentationTestCase() {
        super(GameActivity)
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp()
        Injector.setTestModules(new BaseTestModule())
        Injector.inject(this.activity,this.activity)
    }
}
