package WMS;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WMS_Beta_Admin_Page {

	WebDriver driver;
	By admin = By.xpath("//a[normalize-space()='WMS ADMIN']");
	WebElement Admin;
	String Expected_WMS_Admin_Title = "WMS";
	String Actual_WMS_Admin_title;
	
	

	@BeforeClass
	public void Setup_Driver() {
		 driver=new WMS.WebDriver_factory().ChromeDriver();
		

	}
	@Test(priority = 1)
	public void Open_WMS() {
		driver.get("https://staging-wms.boutiqaat.com/");
	}

	@Test(priority = 2)
	public void Open_Admin_Page() {
		Admin = driver.findElement(admin);
		Admin.click();
		Actual_WMS_Admin_title = driver.getTitle();
		assertEquals(Actual_WMS_Admin_title, Expected_WMS_Admin_Title);

	}

	@AfterClass
	public void AfterClass() {
		driver.close();
		driver.quit();

	}

}
