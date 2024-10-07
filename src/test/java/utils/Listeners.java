package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

import static utils.ExtentReport.configExtentReport;


public class Listeners implements ITestListener {
    ExtentReports report;
    ExtentTest test;
    String parameters=null;
    Object[] params;
    private static final ThreadLocal<ExtentTest> saveTestObject=new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        report=configExtentReport((String)context.getAttribute("browser"));
    }
    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
    @Override
    public void onTestStart(ITestResult result) {
        params = result.getParameters();
        parameters = (params.length == 0) ? "" : Arrays.toString(params);
        test=report.createTest(result.getMethod().getMethodName()+parameters);
        saveTestObject.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        saveTestObject.get().log(Status.PASS,"Successful");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        saveTestObject.get().fail(result.getThrowable());
        if (parameters==null){
            params = result.getParameters();
            parameters = (params.length == 0) ? "" : Arrays.toString(params);
        }
        saveTestObject.get().addScreenCaptureFromPath("/src/test/screenshot/"+result.getMethod().getMethodName()+parameters+" in "+ result.getTestContext().getAttribute("browser")+" browser" +".png");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        saveTestObject.get().log(Status.SKIP,"Skip");
        saveTestObject.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }
}
