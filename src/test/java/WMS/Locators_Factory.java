package WMS;

import org.openqa.selenium.By;

public class Locators_Factory {
	public By Username_TextBox = By.id("UserName");
	public By Password_TextBox = By.id("Password");
	public By Login_Btn = By.xpath("//input[@value='LOGIN']");
	public By User_Role = By.id("UserRole");
	public By Adminstration = By.xpath("//a[normalize-space()='Administration']");
	public By UserMaster = By.xpath("//a[normalize-space()='UserMaster']");
	public By Add_New_Record = By.xpath("//a[normalize-space()='Add new record']");
	public By UserID = By.xpath("//input[@name='USERID']");
	public By UserName = By.xpath("//input[@name='USERNAME']");
	public By DropDown = By.xpath("//td[@validation='[object Object]']//span[@class='k-icon k-i-arrow-60-down']");
	public By Roll_ElementsList = By
			.xpath("//div[@class='k-list-container k-popup k-group k-reset k-state-border-up']//ul[@role='listbox']");
	public By Store_DropDown = By.xpath("//td[@data-container-for='STOREID']//span[@class='k-icon k-i-arrow-60-down']");
	public By Store_ElementsList = By.xpath("//body[1]/div[8]/div[1]/div[2]/ul[1]");
	public By Status = By.xpath("//input[@name='USERSTATUS']");
	public By IsBulkScan = By.xpath("//input[@name='ISBULKSCAN']");

}
