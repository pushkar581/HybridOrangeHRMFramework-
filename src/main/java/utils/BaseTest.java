package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

// TestNG imports
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

// JUnit 5 imports
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected WebDriver driver;

    // TestNG setup
    @BeforeMethod
    public void setUpTestNG() {
        startDriver();
    }

    @AfterMethod
    public void tearDownTestNG() {
        quitDriver();
    }

    // JUnit setup
    @BeforeEach
    public void setUpJUnit() {
        startDriver();
    }

    @AfterEach
    public void tearDownJUnit() {
        quitDriver();
    }

    // Actual driver startup code
    private void startDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    private void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
