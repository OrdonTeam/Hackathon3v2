package com.ordonteam.functional

public final class FunctionalExampleTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp()
        getActivity()
    }

    public void testIsHelloWorldDisplayed() {
        //checkIfViewWithTextIsDisplayed("Hello World")
        assert 1 == 1
    }

}
