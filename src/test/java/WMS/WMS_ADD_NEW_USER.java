package WMS;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WMS_ADD_NEW_USER {

	WebDriver driver;
	By Username_TextBox = new WMS.Locators_Factory().Username_TextBox;
	By Password_TextBox = By.id("Password");
	By Login_Btn = By.xpath("//input[@value='LOGIN']");
	By User_Role = By.id("UserRole");
	By Adminstration = By.xpath("//a[normalize-space()='Administration']");
	By UserMaster = By.xpath("//a[normalize-space()='UserMaster']");
	By Add_New_Record = By.xpath("//span[@class='k-icon k-i-plus']");
	By UserID = By.xpath("//input[@name='USERID']");
	By UserName = By.xpath("//input[@name='USERNAME']");
	By DropDown = By.xpath("//td[@validation='[object Object]']//span[@class='k-icon k-i-arrow-60-down']");
	By Roll_ElementsList = By
			.xpath("//div[@class='k-list-container k-popup k-group k-reset k-state-border-up']//ul[@role='listbox']");
	By Store_DropDown = By.xpath("//td[@data-container-for='STOREID']//span[@class='k-icon k-i-arrow-60-down']");
	By Store_ElementsList = By.xpath("//body[1]/div[8]/div[1]/div[2]/ul[1]");
	By Status = By.xpath("//input[@name='USERSTATUS']");
	By IsBulkScan = By.xpath("//input[@name='ISBULKSCAN']");

	WebElement Username;
	WebElement Password;
	WebElement Login;
	WebElement user_role;
	WebElement adminstration;
	WebElement userMaster;
	WebElement add_new_record;
	WebElement userid;
	WebElement username;
	WebElement RollDropdown;
	WebElement StoreDropdown;
	List<WebElement> Roll_List;
	List<WebElement> Store_List;
	WebElement status;
	WebElement isBulkScan;

	String Username_ID = "202050";
	String Password_Text = "wms@1230";
	String WMS_Admin_LoginPage = "https://sandbox-wms.boutiqaat.com/ADMIN";
	String Expected_LoginPage_Title = "WMS";
	String Actual_LoginPage_Title;
	String Expected_User_Role = "ADMIN";
	String Actual_User_Role;

	FileInputStream inputStream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	Wait<WebDriver> wait;

	@BeforeClass
	public void Setup_Driver() throws IOException {
		driver = new WMS.WebDriver_factory().ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		inputStream = new FileInputStream(
				new File("C:\\Users\\Abdelrahman\\OneDrive\\Desktop\\cc\\cc\\resources\\AutomatedUserCreation.xlsx"));
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheetAt(0);

		wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(3));

	}

	@Test(priority = 1)
	public void Open_Login_page() {

		driver.get(WMS_Admin_LoginPage);
		Actual_LoginPage_Title = driver.getTitle();
	}

	@Test(priority = 2)
	public void Login() throws InterruptedException {
		Username = driver.findElement(Username_TextBox);
		Username.sendKeys(Username_ID);
		Password = driver.findElement(Password_TextBox);
		Password.sendKeys(Password_Text);
		Login = driver.findElement(Login_Btn);
		Login.click();

		
		user_role = driver.findElement(User_Role);
		Actual_User_Role = user_role.getText();
		assertEquals(Actual_User_Role, Expected_User_Role);
	}

	@Test(priority = 3)
	public void Open_User_Master_Page() throws Exception{
		adminstration = driver.findElement(Adminstration);
		adminstration.click();
		userMaster = driver.findElement(UserMaster);
		userMaster.click();
		

		
	}

	@Test(priority = 4)
	public void Create_new_record() throws Exception{
		Thread.sleep(3000);
		add_new_record = driver.findElement(Add_New_Record);
		add_new_record.click();
	}

	@Test(priority = 5)
	public void Add_user_credentials() {
		wait.until(ExpectedConditions.presenceOfElementLocated(UserID));
		userid = driver.findElement(UserID);
		userid.sendKeys(sheet.getRow(1).getCell(0).toString());
		username = driver.findElement(UserName);
		username.sendKeys(sheet.getRow(1).getCell(1).toString());
	}

	@Test(priority = 6)
	public void Choose_User_Type() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(DropDown));
		RollDropdown = driver.findElement(DropDown);
		wait.until(ExpectedConditions.elementToBeClickable(RollDropdown));
		RollDropdown.click();
		Roll_List = driver.findElements(Roll_ElementsList);
		ArrayList<String> X = new ArrayList<>();

		for (int i = 0; i < Roll_List.size(); i++) {
			X.add(Roll_List.get(i).getText());
			System.out.println(Roll_List.get(i).getText());
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='ADMIN']")));
		driver.findElement(By.xpath("//li[normalize-space()='ADMIN']")).click();
		
	}

	@Test(priority = 7)
	public void Choose_user_store() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(Store_DropDown));
		StoreDropdown = driver.findElement(Store_DropDown);
		StoreDropdown.click();
		
		Store_List = driver.findElements(Store_ElementsList);
		ArrayList<String> Y = new ArrayList<>();

		for (int i = 0; i < Store_List.size(); i++) {
			Y.add(Store_List.get(i).getText());
			System.out.println(Store_List.get(i).getText());
		}

		driver.findElement(By.xpath("//li[normalize-space()='1001']")).click();

	}

	@Test(priority = 8)
	public void Check_boxes() throws InterruptedException {
		status = driver.findElement(Status);
		status.click();
		isBulkScan = driver.findElement(IsBulkScan);
		isBulkScan.click();

	}

	@AfterClass
	public void AfterClass() {
		driver.close();
		driver.quit();

	}
}
