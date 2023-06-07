package WMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriver_factory {
	public WebDriver driver;
	public String BaseURL = "https://sandbox-wms.boutiqaat.com/";
	
public WebDriver ChromeDriver() {
	System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\Abdelrahman\\OneDrive\\Desktop\\cc\\cc\\resources\\chromedriver.exe");
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
	driver = new ChromeDriver(chromeOptions);
	
	return driver;
}
	
public void FireFox() {

}

public void Edge() {
	
}
public void Safari() {
	
}




}
