package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 10s wait
    }

    // Locators
    private By usernameField = By.cssSelector("input[name='username']");
    private By passwordField = By.name("password");
    private By loginButton   = By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button");
    private By errorMessage  = By.cssSelector(".oxd-alert-content-text"); // UPDATED

    // Actions
    
    
    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    // Utility: login with both username and password
    public void login(String username, String password) {
        // Wait for username field to ensure page is ready
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
