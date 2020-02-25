package com.test.base;

import com.test.utilityMethods.UtilitiesMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT;

public class WebDriverSession {

    public static WebDriver webDriver;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private void setUpFirefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        webDriver = new FirefoxDriver(firefoxOptions);
        maximizeBrowser();
    }

    private void setUpInternetExplorerBrowser() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.introduceFlakinessByIgnoringSecurityDomains();
        options.ignoreZoomSettings();
        webDriver = new InternetExplorerDriver(options);
        maximizeBrowser();
    }

    private void setUpChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        webDriver = new ChromeDriver(options);
    }

    private void setUpMobileDevice() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidPackage", "com.android.chrome");
        webDriver = new ChromeDriver(options);
    }

    private void setUpChromeMobileEmulator() {
        WebDriverManager.chromedriver().setup();
        Map<String, String> mobileEmulation = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();
        mobileEmulation.put("deviceName", "Pixel 2");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.setUnhandledPromptBehaviour(ACCEPT);
        webDriver = new ChromeDriver(chromeOptions);
    }

    public void init() {
        log.info("Init Webdriver session");
        Properties configuration = UtilitiesMethods.getPropertiesFile("configuration.properties");

        String driverType = configuration.getProperty("browser");
        switch (driverType) {
            case "firefox": {
                setUpFirefoxBrowser();
                break;
            }
            case "chrome": {
                setUpChromeBrowser();
                break;
            }
            case "internetexplorer": {
                setUpInternetExplorerBrowser();
                break;
            }
            case "chrome_mobile": {
                setUpChromeMobileEmulator();
                break;
            }
            case "mobile_device": {
                setUpMobileDevice();
                break;
            }
        }
        getWebDriver().get(configuration.getProperty("url"));
    }

    public void maximizeBrowser() {
        webDriver.manage().window().maximize();
    }

    public void tearDown() {
        webDriver.close();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}

