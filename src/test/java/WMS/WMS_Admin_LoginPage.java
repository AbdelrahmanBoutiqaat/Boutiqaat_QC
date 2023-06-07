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

public class WMS_Admin_LoginPage {

	WebDriver driver;

	String BaseURL = "https://sandbox-wms.boutiqaat.com";
	String Expected_Base_title = "Boutiqaat WMS";
	String Actual_Base_Title;

	By Admin = By.xpath("//a[normalize-space()='WMS ADMIN']");
	WebElement admin_btn;
	String Expected_Admin_Login_Page_Title = "WMS";
	String Actual_Admin_Login_Page_Title;

	@BeforeClass
	public void Setup_Driver() {
		driver = new WMS.WebDriver_factory().ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(priority = 1)
	public void Open_WMS() throws InterruptedException {
		driver.get(BaseURL);
		Actual_Base_Title = driver.getTitle();
		assertEquals(Actual_Base_Title, Expected_Base_title);
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void Navigate_to_Admin_LoginPage() throws InterruptedException {

		this.Open_WMS();

		admin_btn = driver.findElement(Admin);
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(33));
		wait.until(ExpectedConditions.presenceOfElementLocated(Admin));

		admin_btn.click();
		Actual_Admin_Login_Page_Title = driver.getTitle();
		assertEquals(Actual_Admin_Login_Page_Title, Expected_Admin_Login_Page_Title);
		Thread.sleep(1000);

	}

	@AfterClass
	public void AfterClass() {
		driver.close();
		driver.quit();

	}
}
