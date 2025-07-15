package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators: adjust if UI changes!
    private By userMenu = By.className("oxd-userdropdown-name");
    private By logoutButton = By.xpath("//a[text()='Logout']");

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}

