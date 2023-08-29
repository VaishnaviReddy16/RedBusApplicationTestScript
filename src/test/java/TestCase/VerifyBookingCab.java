package TestCase;

import org.testng.annotations.Test;

import BaseItem.BaseClass;
import Pages.CabRental;

public class VerifyBookingCab extends BaseClass {
	@Test
	public void bookingcab() throws InterruptedException {

		CabRental CabRentalObject =new CabRental(driver);
		CabRentalObject.addingPickUp();
		CabRentalObject.seletingDrop();
		CabRentalObject.selectingPicUpDate();
		CabRentalObject.selectingPickUpTime();
		CabRentalObject.selectingCab();
		CabRentalObject.PassengerDetails();		
	}
}
