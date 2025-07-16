package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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
        // Start Extent Report Logging
        ExtentTest extentTest = extent.createTest("Add Employee Test");
        test.set(extentTest);

        try {
            extentTest.log(Status.INFO, "Step 1: Logging in as Admin");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("Admin", "admin123");
            extentTest.log(Status.PASS, "Logged in successfully");

            extentTest.log(Status.INFO, "Step 2: Navigating to Add Employee");
            EmployeePage empPage = new EmployeePage(driver);
            empPage.goToAddEmployee();
            extentTest.log(Status.PASS, "Navigated to Add Employee page");

            extentTest.log(Status.INFO, "Step 3: Adding a new employee");
            String firstName = "Test";
            String lastName = "User" + System.currentTimeMillis(); // unique name
            empPage.addEmployee(firstName, lastName);
            extentTest.log(Status.PASS, "Added new employee: " + firstName + " " + lastName);

            extentTest.log(Status.INFO, "Step 4: Waiting for Personal Details header to appear");
            By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isProfileLoaded = wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader)) != null;
            Assert.assertTrue(isProfileLoaded, "Failed to add new employee! Personal Details page not loaded.");
            extentTest.log(Status.PASS, "Personal Details page loaded - Employee added successfully");

        } catch (Exception e) {
            test.get().log(Status.FAIL, e);
            Assert.fail(e.getMessage());
        }
    }
}
