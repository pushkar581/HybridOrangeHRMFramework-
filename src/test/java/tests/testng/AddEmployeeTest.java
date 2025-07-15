package tests.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.EmployeePage;
import utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AddEmployeeTest extends BaseTest {

    @Test
    public void testAddEmployee() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to Add Employee
        EmployeePage empPage = new EmployeePage(driver);
        empPage.goToAddEmployee();

        // Step 3: Add new employee
        String firstName = "Test";
        String lastName = "User" + System.currentTimeMillis(); // unique name
        empPage.addEmployee(firstName, lastName);

        // Step 4: Wait for the Personal Details header to appear
        By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isProfileLoaded = wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader)) != null;

        // Step 5: Assert
        Assert.assertTrue(isProfileLoaded, "Failed to add new employee! Personal Details page not loaded.");
    }
}
