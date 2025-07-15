package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmployeePage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addButton = By.xpath("//button[contains(.,'Add')]");
    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By saveButton = By.xpath("//button[@type='submit' and contains(.,'Save')]");

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // **This method navigates to PIM > Add Employee**
    public void goToAddEmployee() {
        // Click PIM menu
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
        // Click Add button
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void addEmployee(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
}
