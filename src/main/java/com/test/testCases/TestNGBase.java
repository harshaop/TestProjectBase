package com.test.testCases;

import com.test.base.WebDriverSession;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public abstract class TestNGBase {

    public WebDriverSession session;
    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        session = new WebDriverSession();
        session.init();
        driver = session.getWebDriver();
    }

    @AfterClass
    public void driverClose() {
        session.tearDown();
    }
}
