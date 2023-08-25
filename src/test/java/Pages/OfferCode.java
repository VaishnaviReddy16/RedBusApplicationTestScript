package Pages;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.SafeAction;

public class OfferCode extends SafeAction{
	WebDriver driver;

	public OfferCode(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="li>a[href*='offer']")
	WebElement offerpagelink;
	
	@FindBy(css="table.container tr td")
	List<WebElement> offerList;
	
	@FindBy(css="h4#OfferDiscription")
    WebElement offerPageText;

	public void getOfferCode() 
	{		
		click(offerpagelink);
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows.size());
		List<String> childwindow = new LinkedList<String>();
		childwindow.addAll(windows);
		driver.switchTo().window(childwindow.get(1));
		for(int i=0;i<offerList.size();i++) {
			waitforElement(offerList.get(1),5);
			offerList.get(1).click();
			break;
		}
		    String[] totleText=offerPageText.getText().split("Save");
			String[] firstPartofOffercode = totleText[0].split("Code");
			String offerCode =firstPartofOffercode[1];
			System.out.println(offerCode);
		}
	}
	
