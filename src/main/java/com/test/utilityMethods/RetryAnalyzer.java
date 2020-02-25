package com.test.utilityMethods;

/*
 * This method decides how many times a test needs to be rerun.
 * TestNg will call this method every time a test fails. So we
 * can put some code in here to decide when to rerun the test.
 *
 * Note: This method will return true if a tests needs to be retried
 * and false it not.
 */

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.util.Properties;

public class RetryAnalyzer extends TestListenerAdapter implements IRetryAnalyzer {
    Properties configuration = UtilitiesMethods.getPropertiesFile("configuration.properties");
    int counter = 0;
    int retryLimit = Integer.parseInt(configuration.getProperty("retryTimes"));

    @Override
    public boolean retry(ITestResult result) {
        if(!result.isSuccess()) {
            result.setStatus(ITestResult.SUCCESS);
            String message = Thread.currentThread().getName() + ": Error in " + result.getName() + " Retrying "
                    + (retryLimit + 1 - counter) + " more times";
            Reporter.log(message);
            if (counter < retryLimit) {
                counter++;
                return true;
            }else{
                result.setStatus(ITestResult.FAILURE);
            }
        }
        return false;
    }
}
