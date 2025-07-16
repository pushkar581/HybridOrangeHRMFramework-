package tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import utils.BaseTest;
import utils.ExcelUtils;
import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testdata/LoginData.xlsx";
        return ExcelUtils.getTableArray(filePath, "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void testLoginWithMultipleData(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(driver);
        boolean isDashboardPresent = dashboardPage.isDashboardDisplayed();

        System.out.println("Tested login with: " + username + " / " + password);

        if(username.equals("Admin") && password.equals("admin123")) {
            // Positive test: Assert dashboard appears for valid login
            Assert.assertTrue(isDashboardPresent, "Login should succeed for Admin/admin123");
        } else {
            // Negative test: Assert error message appears for invalid login
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Invalid credentials"), "Login should fail with error message");
        }
    }
}
