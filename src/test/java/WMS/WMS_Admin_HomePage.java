package WMS;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import org.apache.poi.ss.usermodel.Row;

public class WMS_Admin_HomePage {
    WebDriver driver;
	
	By Username_TextBox=By.id("UserName");
	By Password_TextBox=By.id("Password");
	By Login_Btn = By.xpath("//input[@value='LOGIN']");
	By User_Role=By.id("UserRole");
	
	
	WebElement Username;
	WebElement Password;
	WebElement Login;
	WebElement user_role;
	
	String Username_ID="202050";
	String Password_Text = "wms@1230";
	
	String WMS_Admin_LoginPage="https://sandbox-wms.boutiqaat.com/ADMIN";
	String Expected_LoginPage_Title="WMS";
	String Actual_LoginPage_Title;
	String Expected_User_Role="ADMIN";
	String Actual_User_Role;
	
	
	String Reading;
	
	@BeforeClass
	public void Setup_Driver() throws IOException {
		
		 driver=new WMS.WebDriver_factory().ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 
		// WMS_Inventory_HomePage newObj=new WMS_Inventory_HomePage();
		 //newObj.Navigate_to_login_page();
		 
		
	        FileInputStream inputStream = new FileInputStream(new File ("C:\\Users\\Abdelrahman\\OneDrive\\Desktop\\cc\\cc\\resources\\AutomatedUserCreation.xlsx"));
	        XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
	        XSSFSheet sheet=workbook.getSheetAt(0);  
	        Row row=sheet.getRow(0);
	        Cell cell = row.getCell(0);
	        System.out.println(sheet.getRow(0).getCell(0));
	        
	        String x= cell.getStringCellValue();
	        System.out.println("---------------------");
	        System.out.println(sheet.getRow(1).getCell(0).toString()); 
	       
		 
		 
	}
	@Test (priority = 1)
	public void Navigate_to_admin_login_page() {
		driver.get(WMS_Admin_LoginPage);
		Actual_LoginPage_Title=driver.getTitle();
		assertEquals(Actual_LoginPage_Title, Expected_LoginPage_Title);
	}
	@Test(priority = 2)
	public void Login_As_Admin() throws InterruptedException {
		/*
		Username = driver.findElement(Username_TextBox);
		Username.sendKeys(Username_ID);
		Thread.sleep(5000);
		Password= driver.findElement(Password_TextBox);
		Password.sendKeys(Password_Text);
		Thread.sleep(5000);
		Login= driver.findElement(Login_Btn);
		Login.click();
		user_role=driver.findElement(User_Role);
		Actual_User_Role=user_role.getText();
		assertEquals(Actual_User_Role, Expected_User_Role);
		
		Thread.sleep(5000);
	*/	
	}
	
	
	
	@AfterClass
	public void AfterClass() {
		driver.close();
		driver.quit();

	}
}
