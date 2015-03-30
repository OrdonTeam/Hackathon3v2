package com.ordonteam.functional

import org.junit.Ignore

import static com.ordonteam.functional.helper.Checker.checkIfViewWithTextIsDisplayed

public final class FunctionalExampleTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp()
        getActivity()
    }

    @Ignore
    public void testIsHelloWorldDisplayed() {
        checkIfViewWithTextIsDisplayed("Hello World")
    }

}
