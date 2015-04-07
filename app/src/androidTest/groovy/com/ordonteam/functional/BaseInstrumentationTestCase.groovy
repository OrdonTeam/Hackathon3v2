package com.ordonteam.functional

import android.test.ActivityInstrumentationTestCase2
import com.ordonteam.hackathon3.GameActivity

class BaseInstrumentationTestCase extends ActivityInstrumentationTestCase2<GameActivity> {

    BaseInstrumentationTestCase() {
        super(GameActivity)
    }

}
