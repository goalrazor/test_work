package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    private static final Logger log = LogManager.getLogger();

    public void onTestStart(ITestResult iTestResult) {
        log.debug("Starting the test {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.debug("Test {} has been succeeded", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.debug("Test {} has been failed", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.debug("Test {} has been skipped", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.debug("Test {} takes too long", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {

    }
}
