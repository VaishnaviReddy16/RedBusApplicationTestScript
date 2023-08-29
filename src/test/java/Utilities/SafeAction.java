package Utilities;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SafeAction {
	private WebDriver driver;
	protected static Logger Log = Logger.getLogger(SafeAction.class.getName());
	
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
	
	public void scrollToElement(WebDriver driver,WebElement element) 
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public void waitforElement(WebElement waitElement,int waitTimeSeconds) {
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(waitTimeSeconds));
		waittime.until(ExpectedConditions.visibilityOf(waitElement));
	}
	
	public void waitforclickablityOfElement(WebElement waitElement,int waitTimeSeconds) {
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(waitTimeSeconds));
		waittime.until(ExpectedConditions.elementToBeClickable(waitElement));
	}
	
	public void enterTextCharByChar(String text,WebElement element) {
		for(int i=0;i<text.length();i++) 
		{
			char chare=text.charAt(i);
			String s=new StringBuilder().append(chare).toString();
			element.sendKeys(s);
			
		}
		
	}
	public static void startTestCase(String sTestCaseName) {
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
		Log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
	}

	public static void endTestCase(String sTestCaseName) {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		Log.info("X");
		Log.info("X");
		Log.info("X");
		Log.info("X");
	}

	public static void info(String message) {

		Log.info(message);

	}

}
