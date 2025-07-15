package tests.junit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.DashboardPage;
import utils.BaseTest;

public class LoginJUnitTest extends BaseTest {

    @Test
    public void testValidLoginWithJUnit() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Wait for dashboard to load
        try { Thread.sleep(2000); } catch (Exception e) {}

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assertions.assertTrue(dashboardPage.isDashboardDisplayed(),
            "Login failed: Dashboard not loaded! (JUnit)");
    }
}
