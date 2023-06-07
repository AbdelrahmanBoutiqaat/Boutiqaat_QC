package WMS;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WMS_Beta_Mobile_Page {

	WebDriver driver;
	By Mobile = By.xpath("//a[normalize-space()='MOBILE WMS']");
	WebElement WMS_Mobile;

	String Actual_WMS_Mobile_Page_title;
	String Expeced_WMS_Mobile_Page_Title = "WMS - Login";

	@BeforeClass
	public void Setup_Driver() {
		driver=new WMS.WebDriver_factory().ChromeDriver();

	}

	@Test(priority = 1)
	public void Open_WMS() throws InterruptedException {
		driver.get("https://sandbox-wms.boutiqaat.com/");
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void Open_Mobile_Page() {
		WMS_Mobile = driver.findElement(Mobile);
		WMS_Mobile.click();
		Actual_WMS_Mobile_Page_title = driver.getTitle();
		assertEquals(Actual_WMS_Mobile_Page_title, Expeced_WMS_Mobile_Page_Title);

	}

	@AfterClass
	public void AfterClass() {
		driver.close();
		driver.quit();

	}

}
