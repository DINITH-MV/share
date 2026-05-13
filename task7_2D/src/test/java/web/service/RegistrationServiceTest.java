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

public class RegistrationServiceTest {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private final String REG_PAGE_PATH = "C:\\Users\\Dinith\\Desktop\\SIT333\\SIT333 7.2D\\7.2D-resources\\pages\\register.html";

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Dinith\\chromedriver-win64(1)\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@AfterClass
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private void openRegistrationPage() {
		driver.navigate().to(REG_PAGE_PATH);
	}

	private void fillAndSubmitForm(String fname, String lname, String email, String dob, String password,
			String confPassword) {
		WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
		firstNameField.clear();
		if (fname != null)
			firstNameField.sendKeys(fname);

		WebElement lastNameField = driver.findElement(By.id("lname"));
		lastNameField.clear();
		if (lname != null)
			lastNameField.sendKeys(lname);

		WebElement emailField = driver.findElement(By.id("email"));
		emailField.clear();
		if (email != null)
			emailField.sendKeys(email);

		WebElement dobField = driver.findElement(By.id("dob"));
		dobField.clear();
		if (dob != null)
			dobField.sendKeys(dob);

		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.clear();
		if (password != null)
			passwordField.sendKeys(password);

		WebElement confPasswordField = driver.findElement(By.id("confPassword"));
		confPasswordField.clear();
		if (confPassword != null)
			confPasswordField.sendKeys(confPassword);

		WebElement submitButton = driver.findElement(By.cssSelector("button[type=submit], input[type=submit]"));
		submitButton.submit();
	}
	
	private void assertStatus(String expected) {
        // wait for header element
        WebElement statusH2 = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("registration-status"))
        );
        String heading = statusH2.getText().trim();
        Assert.assertEquals(
            "Registration status text",
            "Registration status: " + expected,
            heading
        );

        // check meta tag
        WebElement meta = driver.findElement(
            By.cssSelector("meta[name='registration-result']")
        );
        String content = meta.getAttribute("content").trim();
        Assert.assertEquals(
            "Meta registration-result",
            expected,
            content
        );

    }

	// Functional Test Cases

	@Test
    public void testValidRegistration() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "Dinith@gmail.com",
            "20-09-2001", "DinithPass1!", "DinithPass1!"
        );
        assertStatus("success");
    }

    @Test
    public void testMissingFirstName() {
        openRegistrationPage();
        fillAndSubmitForm(
            null, "Vanderlan", "Dinith@gmail.com",
            "20-09-2001", "DinithPass1!", "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testMissingLastName() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", null, "Dinith@gmail.com",
            "20-09-2001", "DinithPass1!", "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testMissingEmail() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", null,
            "20-09-2001", "DinithPass1!", "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testMissingDob() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "Dinith@gmail.com",
            null, "DinithPass1!", "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testMissingPassword() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "Dinith@gmail.com",
            "20-09-2001", null, "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testMissingConfirmPassword() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "Dinith@gmail.com",
            "20-09-2001", "DinithPass1!", null
        );
        assertStatus("fail");
    }

    @Test
    public void testAllFieldsEmpty() {
        openRegistrationPage();
        fillAndSubmitForm(null, null, null, null, null, null);
        assertStatus("fail");
    }

    @Test
    public void testInvalidEmailFormat() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "wronggmail.com",
            "20-09-2001", "DinithPass1!", "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testInvalidDob() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "Dinith@gmail.com",
            "1890-01-01", "DinithPass1!", "DinithPass1!"
        );
        assertStatus("fail");
    }

    @Test
    public void testPasswordsDoNotMatch() {
        openRegistrationPage();
        fillAndSubmitForm(
            "Dinith", "Vanderlan", "Dinith@gmail.com",
            "20-09-2001", "DinithPass1!", "DinPass12!"
        );
        assertStatus("fail");
    }

}
