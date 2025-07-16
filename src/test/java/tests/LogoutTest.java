package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import utils.BaseTest;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {
        // 1. Login first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // 2. Logout
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();

        // 3. Assert: Back on Login Page (check login panel is present)
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Logout failed! Still logged in.");
    }
}

