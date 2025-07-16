package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import utils.BaseTest;
import utils.ExcelUtils;
import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testdata/LoginData.xlsx";
        return ExcelUtils.getTableArray(filePath, "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void testLoginWithMultipleData(String username, String password) {
        ExtentTest extentTest = extent.createTest("Login Test: " + username + " / " + password);
        test.set(extentTest);

        try {
            extentTest.log(Status.INFO, "Step 1: Attempting login with: " + username + " / " + password);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);

            DashboardPage dashboardPage = new DashboardPage(driver);
            boolean isDashboardPresent = dashboardPage.isDashboardDisplayed();

            extentTest.log(Status.INFO, "Step 2: Checked Dashboard visibility.");

            if(username.equals("Admin") && password.equals("admin123")) {
                // Positive test: Assert dashboard appears for valid login
                extentTest.log(Status.INFO, "Positive test - expecting dashboard.");
                Assert.assertTrue(isDashboardPresent, "Login should succeed for Admin/admin123");
                extentTest.log(Status.PASS, "Dashboard displayed for valid credentials.");
            } else {
                // Negative test: Assert error message appears for invalid login
                String errorMsg = loginPage.getErrorMessage();
                extentTest.log(Status.INFO, "Negative test - expecting error message. Error received: " + errorMsg);
                Assert.assertTrue(errorMsg.contains("Invalid credentials"), "Login should fail with error message");
                extentTest.log(Status.PASS, "Error message validated for invalid credentials.");
            }
        } catch (Exception e) {
            test.get().log(Status.FAIL, e);
            Assert.fail(e.getMessage());
        }
    }
}
