package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.SafeAction;

public class HomePage extends SafeAction {
	WebDriver driver;
	Properties propertiesObject;
	 Logger logger = Logger.getLogger(OfferCode.class);

	public HomePage(WebDriver driver) {
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

	@FindBy(css = "input#src")
	WebElement fromField;

	@FindBy(className = "sc-iwsKbI")
	List<WebElement> fromDropdownCities;

	@FindBy(css = "div.sc-htoDjs input#dest")
	WebElement toField;

	@FindBy(css = "ul.sc-dnqmqq li")
	List<WebElement> ToDropdownCities;

	@FindBy(className = "sc-cSHVUG")
	WebElement calenderIcon;

	@FindBy(className = "fgdqFw")
	WebElement currentDate;

	@FindBy(css = "span.DayTiles__CalendarDaysSpan-sc-1xum02u-1")
	List<WebElement> calenderdates;

	@FindBy(id = "search_button")
	WebElement searchbutton;

	@FindBy(css = "div[class*='tripleFive']")
	List<WebElement> primolist;

	@FindBy(css = "div[class='close-primo']>i")
	WebElement closeprimolist;

	@FindBy(css = "ul.dt-time-filter li.checkbox label.custom-checkbox")
	List<WebElement> departuretimes;

	@FindBy(css = "label[title='GARUDA PLUS']")
	WebElement garudaplusRTCbus;

	@FindBy(css = "ul.bus-items div li div.bus-item")
	List<WebElement> busList;

	@FindBy(css = "div.w-14 div[class='button']")
	WebElement viewBuses;

	@FindBy(css = "div.w-16 div")
	WebElement TSRTCbuscountOnHeding;

	@FindBy(css = "div.column-two")
	List<WebElement> TSRTCbuscountOnResultpage;

	@FindBy(css = "div.fare span")
	List<WebElement> seatprice;

	@FindBy(className = "rat-green")
	List<WebElement> heighratingBusList;

	@FindBy(className = "view-seats")
	WebElement viewSeatButton;

	@FindBy(css = "ul.height-bpdp-single-deck li")
	List<WebElement> bordingDropingpointlist;

	@FindBy(css = "div.bp span.bpdp-point")
	WebElement boardingpointbutton;

	@FindBy(css = "div.dp span.bpdp-point")
	WebElement dropingpointbutton;

	@FindBy(css = "div.bpdp-seats-btn button.gotoseat_btn")
	WebElement viewSeattap;

	@FindBy(css = "ul.multiFare li")
	List<WebElement> setseatprice;

	@FindBy(css = "button.continue")
	WebElement proceedToBookButton;

	@FindBy(css = "div.input_block label.custinfo_label input.input_box")
	List<WebElement> PassengerDetails;

	@FindBy(className = "float_left")
	WebElement femailGender;

	@FindBy(css = "ul.sc-dxZgTM li.sc-iomxrj div div")
	List<WebElement> cityofrecidenceList;

	@FindBy(className = "gtm-continueBooking")
	WebElement proceedToPayButton;

	@FindBy(css = "div.fxvMrr div")
	List<WebElement> calenderlist;

	@FindBy(css = "canvas[data-type='lower']")
	WebElement canvasElement;

	/**
	 * This method Navigate to Home page and selects the From and To city Location for booking a TSRTC bus
	 */
	public void searchbus(String fromCityName, String toCityName) {
		Log.info("Selecting From city from FromField.");
		click(fromField);
		type(fromField, fromCityName);
		for (int i = 0; i < fromDropdownCities.size(); i++) {
			if (fromDropdownCities.get(i).getText().equalsIgnoreCase(fromCityName)) {
				click(fromDropdownCities.get(i));
				break;
			}
		}
		Log.info("Selecting To city from ToField.");
		click(toField);
		type(toField, toCityName);
		for (int j = 0; j < ToDropdownCities.size(); j++) {
			if (ToDropdownCities.get(j).getText().equalsIgnoreCase(toCityName)) {
				click(ToDropdownCities.get(j));
				break;
			}
		}

		Log.info("Selecting Date from Calender.");
		click(calenderIcon);
		if (calenderlist.get(1).getText().contains(propertiesObject.getProperty("monthandyear"))) 
		  {
			for (int j = 0; j < calenderdates.size(); j++) {
				if (calenderdates.get(j).getText().equalsIgnoreCase(propertiesObject.getProperty("date"))) 
				{
					click(calenderdates.get(j));
					break;
				}
		}}else if(!calenderlist.get(1).getText().contains(propertiesObject.getProperty("monthandyear"))){
			    for (int k = 0; k < 12; k++) {
			    	if (!calenderlist.get(1).getText().contains(propertiesObject.getProperty("monthandyear"))) 
					{
			    		click(calenderlist.get(3));
						break;
					}
			    }
				for (int i = 0; i < calenderdates.size(); i++) {
					if (calenderdates.get(i).getText().equalsIgnoreCase(propertiesObject.getProperty("date"))) 
					{
						click(calenderdates.get(i));
						break;
					}
				}
			    }
		Log.info("Searching buses for Entered destination.");
		click(searchbutton);
	}

	/**
	 * This method Navigate to Search result page and apply filters After 6PM and TSRTC bus on result page 
	 */
	
	public void applyingTSRTCfilter() throws ElementClickInterceptedException 
	{
		Log.info("Closing prime banner in search result page.");
		waitforElement(departuretimes.get(3), 10);
		if (primolist.size() > 0) {
			scrollToElement(driver, closeprimolist);
			waitforElement(closeprimolist, 10);
			waitforclickablityOfElement(closeprimolist, 5);
			click(closeprimolist);
		}
		Log.info("Applying departure Time filter.");
		scrollToElement(driver, departuretimes.get(3));
		waitforElement(departuretimes.get(3), 10);
		click(departuretimes.get(3));
		Log.info("Applying TSRTC bus filter.");
		scrollToElement(driver, garudaplusRTCbus);
		waitforElement(garudaplusRTCbus, 10);
		waitforclickablityOfElement(garudaplusRTCbus, 10);
		click(garudaplusRTCbus);
		if (primolist.size() > 0) {
			scrollToElement(driver, closeprimolist);
			waitforElement(closeprimolist, 10);
			waitforclickablityOfElement(closeprimolist, 5);
			click(closeprimolist);
		}
		click(viewBuses);
//		String[] actulbuscount = TSRTCbuscountOnHeding.getText().split(" ");
//		String actualcont = actulbuscount[0];
//		int count = TSRTCbuscountOnResultpage.size();
	}

	/**
	 * This method Selecting TSRTC bus based on Lowest price and heigh rating  
	 */
	
	public void selectingTSRTCbus() {
		Log.info("Selecting bus based on low price and heigh rating.");
		waitforElement(viewBuses, 10);
		click(viewBuses);
		double highestRating = 0;
		WebElement selectedBus = null;
		double lowestPrice = Double.MAX_VALUE;
		for (WebElement bus : busList) {
			double price = Double
					.parseDouble(bus.findElement(By.cssSelector("span.f-19")).getText().replaceAll("[^0-9.]", ""));
			double rating = Double.parseDouble(bus.findElement(By.cssSelector("div.rating-sec")).getText());

			if (price < lowestPrice || (price == lowestPrice && rating > highestRating)) {
				lowestPrice = price;
				highestRating = rating;
				selectedBus = bus;
			}
		}
		if (selectedBus != null) {
			
			WebElement selectviewseat = selectedBus.findElement(By.cssSelector("div.m-top-16 div.view-seats"));
			scrollToElement(driver, selectviewseat);
			click(selectviewseat);
		}
	}

	/**
	 * This method Selects droping and Boarding location for selected TSRTC bus  
	 */
	public void selectingdropingBoardingpoints() {
		Log.info("Selecting boarding Point.");
		waitforElement(boardingpointbutton, 5);
		click(boardingpointbutton);
		for (int i = 0; i < bordingDropingpointlist.size(); i++)
		{
			if (bordingDropingpointlist.get(i).getText().contains(propertiesObject.getProperty("bordingpoint"))) {
				click(bordingDropingpointlist.get(i));
				break;
			}
		}
		Log.info("Selecting droping point.");
		click(dropingpointbutton);
		for (int i = 0; i < bordingDropingpointlist.size(); i++) 
		{
			if (bordingDropingpointlist.get(i).getText().contains(propertiesObject.getProperty("Dropingpoint"))) {
				click(bordingDropingpointlist.get(i));
				break;
			}
		}
		scrollToElement(driver, viewSeattap);
		waitforclickablityOfElement(viewSeattap, 30);
		click(viewSeattap);

	}

	/**
	 * This method Selects Seat on bus
	 */
	public void seatSelect() {
		Log.info("Selecting seat from bus.");
		//click(canvasElement);
		scrollToElement(driver, canvasElement);
		Actions actions = new Actions(driver);
		actions.moveToElement(canvasElement, 0, 0).moveByOffset((700/16)*3, 1).click().build().perform();
		scrollToElement(driver, proceedToBookButton);
		click(proceedToBookButton);
	}

	/**
	 * This method add's Seat PassengerDetails and proceed to payment button
	 */
	public void PassengerDetails() {
		
		for (int i = 0; i < PassengerDetails.size(); i++) {
			PassengerDetails.get(i);
		}
		Log.info("Adding Passenger Details.");
		click(PassengerDetails.get(0));
		type(PassengerDetails.get(0), propertiesObject.getProperty("Name"));
		click(PassengerDetails.get(1));
		click(femailGender);
		type(PassengerDetails.get(1), propertiesObject.getProperty("Age"));
		click(PassengerDetails.get(2));
		type(PassengerDetails.get(2), propertiesObject.getProperty("CityofResidence"));
		for (int i = 0; i < cityofrecidenceList.size(); i++) {
			click(cityofrecidenceList.get(0));
			break;
		}
		click(PassengerDetails.get(4));
		type(PassengerDetails.get(4), propertiesObject.getProperty("EmailId"));
		click(PassengerDetails.get(5));
		type(PassengerDetails.get(5), propertiesObject.getProperty("Phone"));
		click(proceedToPayButton);
	}
}
