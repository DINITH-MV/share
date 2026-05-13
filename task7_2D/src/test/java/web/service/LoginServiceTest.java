package web.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginServiceTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String LOGIN_PAGE_PATH = "C:\\Users\\Dinith\\Desktop\\SIT333\\SIT333 7.2D\\7.2D-resources\\pages\\login.html";

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dinith\\chromedriver-win64(1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10); 
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void openLoginPage() {
        driver.navigate().to(LOGIN_PAGE_PATH);
    }

    private void fillAndSubmitForm(String username, String password, String dob) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.clear();
        if (username != null) usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.clear();
        if (password != null) passwordField.sendKeys(password);

        WebElement dobField = driver.findElement(By.id("dob"));
        dobField.clear();
        if (dob != null) dobField.sendKeys(dob);

        WebElement submitButton = driver.findElement(By.cssSelector("[type=submit]"));
        submitButton.submit();
    }
    
    // Functional Test Cases

    @Test
    public void testLoginSuccess() {
        openLoginPage();
        fillAndSubmitForm("ahsan", "ahsan_pass", "25-04-2020");

        wait.until(ExpectedConditions.titleIs("Login success"));
        Assert.assertEquals("Login success", driver.getTitle());
    }

    @Test
    public void testLoginFailure() {
        openLoginPage();
        fillAndSubmitForm("wrong_user", "wrong_pass", "01-01-2000");

        wait.until(ExpectedConditions.titleIs("Login fail"));
        Assert.assertEquals("Login fail", driver.getTitle());
    }

    @Test
    public void testLoginWithCorrectUsernameWrongPassword() {
        openLoginPage();
        fillAndSubmitForm("ahsan", "wrong_pass", "25-04-2020");

        wait.until(ExpectedConditions.titleIs("Login fail"));
        Assert.assertEquals("Login fail", driver.getTitle());
    }
    
    @Test
    public void testLoginWithCorrectUsernameAndPasswordWrongDOB() {
    	openLoginPage();
    	fillAndSubmitForm("ahsan", "wrong_pass", "25-04-2000");
    	
    	wait.until(ExpectedConditions.titleIs("Login fail"));
    	Assert.assertEquals("Login fail", driver.getTitle());
    }


    @Test
    public void testLoginWithEmptyUsername() {
        openLoginPage();
        fillAndSubmitForm("", "ahsan_pass", "25-04-2020");

        wait.until(ExpectedConditions.titleIs("Login fail"));
        Assert.assertEquals("Login fail", driver.getTitle());
    }

    @Test
    public void testLoginWithEmptyPassword() {
        openLoginPage();
        fillAndSubmitForm("ahsan", "", "25-04-2020");

        wait.until(ExpectedConditions.titleIs("Login fail"));
        Assert.assertEquals("Login fail", driver.getTitle());
    }
    
    
}
