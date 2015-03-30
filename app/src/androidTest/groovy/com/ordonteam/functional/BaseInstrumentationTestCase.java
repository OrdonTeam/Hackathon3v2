package com.ordonteam.functional;

import android.test.ActivityInstrumentationTestCase2;

import com.ordonteam.hackathon3.MainActivity;


public abstract class BaseInstrumentationTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    public BaseInstrumentationTestCase() {
        super(MainActivity.class);
    }

}
