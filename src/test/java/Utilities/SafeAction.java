package Utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SafeAction {
	private WebDriver driver;

	public SafeAction(WebDriver driver) 
	{
		this.driver=driver;
	}

	public void click(WebElement clickElement) throws ElementClickInterceptedException 
	{
     try {
		clickElement.click();
	}catch(NoSuchElementException e){
		e.printStackTrace();
	}catch(WebDriverException e) {
		e.printStackTrace();
	}
     }
	
	public void type(WebElement inputelement, String inputText)
	{
			  inputelement.sendKeys(inputText);	
	}
	
	public void clear(WebElement clearelement)
	{	
		clearelement.clear();		
	}
	
	public void waitforElement(WebElement waitElement,int waitTimeSeconds) {
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(waitTimeSeconds));
		waittime.until(ExpectedConditions.visibilityOf(waitElement));
	}
	
	public void waitforclickablityOfElement(WebElement waitElement,int waitTimeSeconds) {
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(waitTimeSeconds));
		waittime.until(ExpectedConditions.elementToBeClickable(waitElement));
	}
}
