package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class landingPage {
	
	public WebDriver driver;
	
	By dispenseNow= By.xpath("//a[contains(text(),'Dispense Now')]");
	By cashDispensed= By.xpath("//div[contains(text(),'Cash dispensed')]");
	By browseButton= By.xpath("//input[@class='custom-file-input']");
	
	public landingPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public WebElement dispenseButton()
	{
		return driver.findElement(dispenseNow);
	}
	
	
	public WebElement cashDispensed()
	{
		return driver.findElement(cashDispensed);
	}
	
	public WebElement browseButton()
	{
		return driver.findElement(browseButton);
	}

}
