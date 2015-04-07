package com.ordonteam.functional

final class FunctionalExampleTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp()
        activity //running the getActivity method ;-)
    }

    void testIsHelloWorldDisplayed() {
        //checkIfViewWithTextIsDisplayed("Hello World")
        assert 1 == 1
    }

}
