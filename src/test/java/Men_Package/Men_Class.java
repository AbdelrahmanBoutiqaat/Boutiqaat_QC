package Men_Package;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Men_Class {
	WebDriver driver1;

	By Men = By.xpath("//a[normalize-space()='MEN']");

	@BeforeClass
	public void Setup_Driver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Abdelrahman\\OneDrive\\Desktop\\cc\\cc\\resources\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
		driver1 = new ChromeDriver(chromeOptions);

	}

	@Test(priority = 1)
	public void Open_MEN() {

		driver1.get("https://www.boutiqaat.com/");
		WebElement men = driver1.findElement(Men);
		men.click();
		assertEquals(driver1.getTitle(), "Boutiqaat: Online Shopping for Men in Kuwait | Buy Footwear, Apparel, Fragrances & More");
	}

	@AfterClass
	public void AfterClass() {
		driver1.close();
		driver1.quit();

	}

}
