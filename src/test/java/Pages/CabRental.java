package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.SafeAction;

public class CabRental extends SafeAction {
	WebDriver driver;
	Properties propertiesObject;
	Logger logger = Logger.getLogger(OfferCode.class);
	 
	public CabRental(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		FileInputStream filename2;
		propertiesObject = new Properties();
		try {
			filename2 = new FileInputStream(System.getProperty("user.dir").concat("\\Resources\\data.properties"));
			propertiesObject.load(filename2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FindBy(css = "ul.rb_verticals li#cab_rental_vertical")
	WebElement cabRentalbutton;

	@FindBy(css = "input#source")
	WebElement pickUpLocation;

	@FindBy(css = "div.KPXAeithRJbCekbVxMDh div._yaQnakgYWVGq9VQOFss")
	List<WebElement> LIstOfAutoDropdownLocation;

	@FindBy(css = "input#destination")
	WebElement dropLocation;

	@FindBy(css = "input.MuiInputBase-input")
	WebElement calender;

	@FindBy(css = "svg.MuiSvgIcon-root")
	List<WebElement> moveForwordArrow;

	@FindBy(css = "div.MuiPickersCalendar-week div")
	List<WebElement> dateCells;

	@FindBy(css = "p.MuiTypography-alignCenter")
	WebElement monthAndyearText;

	@FindBy(css = "span.MuiButton-label")
	List<WebElement> okAndCancelButton;

	@FindBy(css = "span.MuiPickersClockNumber-clockNumber")
	List<WebElement> clockTimes;

	@FindBy(css = "h6.MuiTypography-subtitle1")
	List<WebElement> AMandPMbutton;

	@FindBy(className = "msp7m5_Cw6_4YikUZdsn")
	WebElement roundTripCheckbox;

	@FindBy(css = "div.sIT1HvKpAi1b9I2CBe6s")
	WebElement searchButton;

	@FindBy(css = "h3.MuiTypography-h3")
	WebElement timetext;

	@FindBy(className = "mXYbt2p_awUquuje7Owa")
	List<WebElement> listOfCab;

	@FindBy(css = "input#customer_name")
	WebElement customername;

	@FindBy(css = "input#customer_email")
	WebElement customerEmail;
	// input#customer_mobile
	@FindBy(css = "input#customer_mobile")
	WebElement customerPhoneNumber;

	@FindBy(className = "tX_9yOkbhGoj7tRirV0N")
	List<WebElement> proceedBookbutton;
	
	@FindBy(className="KPXAeithRJbCekbVxMDh")
	WebElement dropdownlist;

	/**
	 * This method Navigate to Cab rental page and selects the Pick Up Location for booking a cab
	 */
	
	public void addingPickUp() {
		Log.info("Selecting pick up city from pickupLocation Field.");
		waitforElement(cabRentalbutton, 10);
		click(cabRentalbutton);
		waitforElement(pickUpLocation, 10);
		click(pickUpLocation);
		enterTextCharByChar(propertiesObject.getProperty("CabPicUpPoint"),pickUpLocation);
		waitforclickablityOfElement(dropdownlist, 5);
		for (int i = 0; i < LIstOfAutoDropdownLocation.size(); i++) {
			if (LIstOfAutoDropdownLocation.get(i).getText().equalsIgnoreCase(propertiesObject.getProperty("CabPicUpPoint"))) 
			{
				System.out.println(LIstOfAutoDropdownLocation.get(i).getText());
		waitforElement(LIstOfAutoDropdownLocation.get(i), 10);
		click(LIstOfAutoDropdownLocation.get(i));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 break;
			}
		}		
	}
	
	/**
	 * This method selects the Drop Location for booking a cab
	 */
	public void seletingDrop() {
		Log.info("Selecting Drop city from dropLocation Field.");
		waitforElement(dropLocation, 5);
//		click(dropLocation);
		enterTextCharByChar(propertiesObject.getProperty("CabDropPoint"),dropLocation);
		waitforElement(dropdownlist, 10);
		for (int j = 0; j < LIstOfAutoDropdownLocation.size(); j++) {
			if (LIstOfAutoDropdownLocation.get(j).getText().equalsIgnoreCase(propertiesObject.getProperty("CabDropPoint"))) 
			{
				System.out.println(LIstOfAutoDropdownLocation.get(j).getText());
		        waitforElement(LIstOfAutoDropdownLocation.get(j), 10);
				click(LIstOfAutoDropdownLocation.get(j));
				break;
			}
		}
	}		
	

	/**
	 * This method selects the pickup date for booking a cab
	 */
	public void selectingPicUpDate() {
		Log.info("Selecting pick up date from calender Field.");
		waitforclickablityOfElement(calender, 10);
		click(calender);
		if (monthAndyearText.getText().contains(propertiesObject.getProperty("persentmonthname"))) {
			for (int j = 0; j < dateCells.size(); j++) {
				if (dateCells.get(j).getText().equalsIgnoreCase("30")) {
					waitforclickablityOfElement(dateCells.get(j), 5);
					click(dateCells.get(j));
					break;
				}
			}
		} else if (!monthAndyearText.getText().contains(propertiesObject.getProperty("persentmonthname"))) {
			for (int k = 0; k < 12; k++) {
				if (!monthAndyearText.getText().contains(propertiesObject.getProperty("futuremonthname"))) {
					click(moveForwordArrow.get(1));
					break;
				}
			}
			for (int i = 0; i < dateCells.size(); i++) {
				if (dateCells.get(i).getText().equalsIgnoreCase(propertiesObject.getProperty("2"))) {
					waitforclickablityOfElement(dateCells.get(i), 5);
					click(dateCells.get(i));
					break;
				}
			}
		}

	}

	/**
	 * This method selects the pickup time for booking a cab
	 */
	public void selectingPickUpTime() {
		Log.info("Selecting pick up time from clock.");
		 Actions actions = new Actions(driver);
		 actions.moveToElement(clockTimes.get(4)).click().build().perform();
		waitforclickablityOfElement(okAndCancelButton.get(7), 5);
		click(okAndCancelButton.get(7));
		waitforElement(searchButton, 10);
		waitforclickablityOfElement(searchButton, 5);
		click(searchButton);
	}

	/**
	 * This method selects the Cab Based on lowestprice from search reasult page 
	 */
	public void selectingCab() {
		Log.info("Selecting cab based on low price.");
		scrollToElement(driver, listOfCab.get(0));
		waitforElement(listOfCab.get(0), 10);
		scrollToElement(driver, listOfCab.get(1));
		int lowestPrice = Integer.MAX_VALUE;
		System.out.println("Lowest Price: "+lowestPrice);
		WebElement selectedCab = null;
		    for (WebElement cab : listOfCab) {
		            WebElement priceElement = cab.findElement(By.className("hS_1nZZo18c1AToVHGBL"));
		           String price1 = priceElement.getText().split(" ")[1];
		            int price=Integer.parseInt(price1);
		            System.out.println("Cab Price: "+price);
		            if (price < lowestPrice) {
		                lowestPrice = price;
		                selectedCab = cab;
		            }
		        }
		    System.out.println("Lowest Cab Price: "+lowestPrice);
		        if (selectedCab != null) {
                    scrollToElement(driver, selectedCab);
		        	waitforElement(selectedCab, 10);
		        	selectedCab.click();
		        }
	}
	
	/**
	 * This method Navigates to payment page and add's all the details of passenger
	 */
	
	public void PassengerDetails() {
		Log.info("Adding PassengerDetails in payment page.");
		waitforElement(proceedBookbutton.get(0), 10);
		click(proceedBookbutton.get(0));
		Log.info("Adding Passenger Details.");
		click(customername);
		type(customername, propertiesObject.getProperty("Name"));
		click(customerEmail);
		type(customerEmail, propertiesObject.getProperty("EmailId"));
		click(customerPhoneNumber);
		type(customerPhoneNumber, propertiesObject.getProperty("Phone"));
		
	}


}
