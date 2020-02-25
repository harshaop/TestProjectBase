package com.test.pageObjects;

import com.sun.org.apache.xml.internal.security.Init;
import com.test.base.WebDriverSession;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage extends WebDriverSession {
    WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    public void enterSearchTerm() {
        searchField.click();
        searchField.sendKeys("google");
    }
}
