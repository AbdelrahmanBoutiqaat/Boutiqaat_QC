package Women_Package;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Women_Class {
	WebDriver driver1;
	By Women = By.xpath("//a[normalize-space()='WOMEN']");

	@BeforeClass
	public void Setup_Driver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Abdelrahman\\OneDrive\\Desktop\\cc\\cc\\resources\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
		driver1 = new ChromeDriver(chromeOptions);

	}

	@Test(priority = 1)
	public void Open_Women() {

		driver1.get("https://www.boutiqaat.com/");
		WebElement women = driver1.findElement(Women);
		women.click();

		assertEquals(driver1.getTitle(),
				"Boutiqaat: Online Shopping for Women in Kuwait | Buy Beauty, Cosmetics, Fragrances & More");

	}

	@AfterClass
	public void AfterClass() {
		driver1.close();
		driver1.quit();

	}

}
