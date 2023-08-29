package TestCase;

import org.testng.annotations.Test;

import BaseItem.BaseClass;
import Pages.HomePage;

public class VerifyBookingBusSeat extends BaseClass
  {
	@Test(dataProvider="DriverTesttt")
	public void bookingTSRTCBusSeat(String Fromcity,String Tocity) 
	{		
		HomePage homePageObject = new HomePage(driver);
		homePageObject.searchbus(Fromcity, Tocity);
		homePageObject.applyingTSRTCfilter();
		homePageObject.selectingTSRTCbus();
		homePageObject.selectingdropingBoardingpoints();
		homePageObject.seatSelect();
		homePageObject.PassengerDetails();
	}
}
