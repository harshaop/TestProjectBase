package com.test.testCases;

import com.test.pageObjects.GooglePage;
import org.testng.annotations.Test;

public class FirstTest extends TestNGBase {

    @Test
    public void simpleTest() {
        GooglePage gp = new GooglePage(session.getWebDriver());
        gp.enterSearchTerm();
    }
}
