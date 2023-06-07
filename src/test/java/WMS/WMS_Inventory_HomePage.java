package WMS;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WMS_Inventory_HomePage {
	WebDriver driver;

	By Username_TextBox = By.id("UserName");
	By Password_TextBox = By.id("Password");
	By Login_Btn = By.xpath("//input[@value='LOGIN']");
	By User_Role = By.id("UserRole");

	WebElement Username;
	WebElement Password;
	WebElement Login;
	WebElement user_role;

	String Username_ID = "202030";
	String Password_Text = "wms@1230";

	String WMS_Admin_LoginPage = "https://sandbox-wms.boutiqaat.com/ADMIN";
	String Expected_LoginPage_Title = "WMS";
	String Actual_LoginPage_Title;
	String Expected_User_Role = "INVENTORY";
	String Actual_User_Role;

	Wait<WebDriver> wait;

	@BeforeClass
	public void Setup_Driver() {
		driver = new WMS.WebDriver_factory().ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(2));

	}

	@Test(priority = 1)
	public void Navigate_to_login_page() {
		driver.get(WMS_Admin_LoginPage);
		Actual_LoginPage_Title = driver.getTitle();

		assertEquals(Actual_LoginPage_Title, Expected_LoginPage_Title);
	}

	@Test(priority = 2)
	public void Login_As_Admin() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(Username_TextBox));
		Username = driver.findElement(Username_TextBox);
		Username.sendKeys(Username_ID);
		wait.until(ExpectedConditions.presenceOfElementLocated(Password_TextBox));
		Password = driver.findElement(Password_TextBox);
		Password.sendKeys(Password_Text);
		Login = driver.findElement(Login_Btn);
		Login.click();
		user_role = driver.findElement(User_Role);
		Actual_User_Role = user_role.getText();
		assertEquals(Actual_User_Role, Expected_User_Role);
		Thread.sleep(5000);

	}

	@AfterClass
	public void AfterClass() {
		driver.close();
		driver.quit();

	}

}
