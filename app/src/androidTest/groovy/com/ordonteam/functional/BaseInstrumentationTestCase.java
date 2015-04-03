package com.ordonteam.functional;

import android.test.ActivityInstrumentationTestCase2;

import com.ordonteam.hackathon3.GameActivity;


public abstract class BaseInstrumentationTestCase extends ActivityInstrumentationTestCase2<GameActivity> {

    public BaseInstrumentationTestCase() {
        super(GameActivity.class);
    }

}
