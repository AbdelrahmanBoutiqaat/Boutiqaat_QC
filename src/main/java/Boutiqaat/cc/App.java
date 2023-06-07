package Boutiqaat.cc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	   System.setProperty("webdriver.chrome.driver","C:\\Users\\Abdelrahman\\eclipse-workspace\\cc\\resources\\chromedriver1.exe");
           ChromeOptions chromeOptions = new ChromeOptions();
           chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");

           WebDriver driver = new ChromeDriver(chromeOptions);

           driver.get("https://www.boutiqaat.com/");
           WebElement Women= driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div/div[2]/button[1]/span"));
           WebElement Men= driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div/div[2]/button[2]/span"));

           
           
       
    }
}
