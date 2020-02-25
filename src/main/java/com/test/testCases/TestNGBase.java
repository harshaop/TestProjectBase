package com.test.testCases;

import com.test.base.WebDriverSession;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public abstract class TestNGBase {

    WebDriverSession session = new WebDriverSession();

    @BeforeClass
    public void setup() {
        session.init();
}

    @AfterClass
    public void driverClose() {
        session.tearDown();
    }
}
