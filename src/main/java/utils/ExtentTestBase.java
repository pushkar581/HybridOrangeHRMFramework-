package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class ExtentTestBase {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setUpSuite() {
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod
    public void setUpTest(Method method) {
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test skipped");
        }
    }

    // To allow use of test.get() in subclasses
    protected ExtentTest getTest() {
        return test.get();
    }
}

