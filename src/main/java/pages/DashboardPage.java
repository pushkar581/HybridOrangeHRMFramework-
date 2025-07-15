package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DashboardPage {
    WebDriver driver;

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Header locator for the dashboard
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    // Waits for the dashboard header to appear, returns true if found
    public boolean isDashboardDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
