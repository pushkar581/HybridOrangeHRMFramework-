package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import utils.BaseTest;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {
        ExtentTest extentTest = extent.createTest("Logout Test - Admin");
        test.set(extentTest);

        try {
            // 1. Login first
            extentTest.log(Status.INFO, "Step 1: Logging in as Admin.");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("Admin", "admin123");

            // 2. Logout
            extentTest.log(Status.INFO, "Step 2: Logging out.");
            LogoutPage logoutPage = new LogoutPage(driver);
            logoutPage.logout();

            // 3. Assert: Back on Login Page
            extentTest.log(Status.INFO, "Step 3: Verifying that login page is displayed after logout.");
            Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Logout failed! Still logged in.");
            extentTest.log(Status.PASS, "Successfully logged out and returned to login page.");
        } catch (Exception e) {
            test.get().log(Status.FAIL, e);
            Assert.fail(e.getMessage());
        }
    }
}
