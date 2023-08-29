package TestCase;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import BaseItem.BaseClass;
import Pages.OfferCode;

public class VerifyOfferCode extends BaseClass{
	@Test
	public void offerCode() 
	{		
		PropertyConfigurator.configure("C:\\Users\\vaishnavi.reddy\\eclipse-workspace\\RedBusBookingWebsite\\Resources\\log4j.properties");
       
		OfferCode OfferCodeObject = new OfferCode(driver);	
		OfferCodeObject.getOfferCode();
	}
}