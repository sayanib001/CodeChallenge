package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class swaggerUiPage {
	
public WebDriver driver;
	
	By calculatorController= By.xpath("//span[contains(text(),'calculator-controller')]");
	By calculatorInsert= By.xpath("//div[contains(@id,'operations-calculator-controller-insertPersonUsingPOST_1')]/div[1]/span[contains(text(),'POST')]");
	By tryItOut= By.xpath("//button[contains(text(),'Try it out')]");
	By enterParameter= By.xpath("//div[@class='body-param']/textarea");
	By executeButton= By.xpath("//button[contains(text(),'Execute')]");
	By calculatorInsertMultiple= By.xpath("//span[contains(text(),'/calculator/insertMultiple')]");
	By getTaxRelief= By.xpath("//div[contains(@id,'operations-calculator-controller-getTaxReliefUsingGET')]/div/span");
	
	
	public swaggerUiPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public WebElement calculatorController()
	{
		return driver.findElement(calculatorController);
	}
	
	public WebElement calculatorInsert()
	{
		return driver.findElement(calculatorInsert);
	}
	public WebElement tryItOut()
	{
		return driver.findElement(tryItOut);
	}
	
	public WebElement enterParameter()
	{
		return driver.findElement(enterParameter);
	}
	
	public WebElement executeButton()
	{
		return driver.findElement(executeButton);
	}
	
	public WebElement calculatorInsertMultiple()
	{
		return driver.findElement(calculatorInsertMultiple);
	}
	
	public WebElement getTaxRelief()
	{
		return driver.findElement(getTaxRelief);
	}
	

}
