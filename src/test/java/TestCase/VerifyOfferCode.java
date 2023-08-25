package TestCase;

import org.testng.annotations.Test;
import BaseItem.BaseClass;
import Pages.OfferCode;


public class VerifyOfferCode extends BaseClass{
	@Test
	public void offerCode() 
	{		
		OfferCode OfferCodeObject = new OfferCode(driver);	
		OfferCodeObject.getOfferCode();
	}
}
