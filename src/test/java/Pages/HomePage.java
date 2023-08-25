package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.SafeAction;

public class HomePage extends SafeAction {
	WebDriver driver;
	Properties propertiesObject;

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

	@FindBy(className="fgdqFw")
	WebElement currentDate;
	
	@FindBy(css="span.DayTiles__CalendarDaysSpan-sc-1xum02u-1")
	List<WebElement> calenderdates;
	
	@FindBy(id = "search_button")
	WebElement searchbutton;
	 
	@FindBy(css = "ul.dt-time-filter li.checkbox label.custom-checkbox")
	List<WebElement> departuretimes;

	@FindBy(css = "label[title='GARUDA PLUS']")
	WebElement garudaplusRTCbus;
	
	@FindBy(css="ul.bus-items div li div.bus-item")
	List<WebElement> busList;

	@FindBy(css = "div.w-14 div[class='button']")
	WebElement viewBuses;

	@FindBy(css = "div.w-16 div")
	WebElement TSRTCbuscountOnHeding;
	
	@FindBy(css="div.column-two")
	List<WebElement> TSRTCbuscountOnResultpage;
	
	@FindBy(css="div.fare span")
	List<WebElement> seatprice;
	
	@FindBy(className="rat-green")
	List<WebElement> heighratingBusList;
	
	@FindBy(className="view-seats")
	WebElement viewSeatButton;
	
	@FindBy(css="ul.height-bpdp-single-deck li")
	List<WebElement> bordingDropingpointlist;
	
	@FindBy(css="div.bp span.bpdp-point")
	WebElement boardingpointbutton;
	
	@FindBy(css="div.dp span.bpdp-point")
	WebElement dropingpointbutton;
	
	@FindBy(css="div.bpdp-seats-btn button.gotoseat_btn")
	WebElement viewSeattap;
	
	@FindBy(css="ul.multiFare li")
	List<WebElement> setseatprice;
	
	@FindBy(css="button.continue")
	WebElement proceedToBookButton;
	
	@FindBy(css="div.input_block label.custinfo_label input.input_box")
	List<WebElement> PassengerDetails;
	
	@FindBy(className="gtm-continueBooking")
	WebElement proceedToPayButton;


	public void searchbus(String fromCityName, String toCityName) 
	{
		click(fromField);
		type(fromField, fromCityName);
		for (int i = 0; i < fromDropdownCities.size(); i++) {
			if (fromDropdownCities.get(i).getText().equalsIgnoreCase(fromCityName)) {
				click(fromDropdownCities.get(i));
			}
		}
		click(toField);
		waitforElement(toField,5);
		type(toField, toCityName);
		for (int j = 0; j < ToDropdownCities.size(); j++) {
			if (ToDropdownCities.get(j).getText().equalsIgnoreCase(toCityName)) {
				click(ToDropdownCities.get(j));
			}
		}
		click(calenderIcon);
		for (int k = 0; k < calenderdates.size(); k++) {
			if (calenderdates.get(k).getText().equalsIgnoreCase("30")) {
				click(calenderdates.get(k));
			}
		}
	
		//click(currentDate);
		click(searchbutton);		
	}
	
	public void applyingdepartTimefilters() throws ElementClickInterceptedException
	{   
		waitforElement(departuretimes.get(3),20);
		waitforclickablityOfElement(departuretimes.get(3),30);		
		click(departuretimes.get(3));
	}
	
	public void applyingTSRTCfilter() throws ElementClickInterceptedException
	{
		waitforElement(garudaplusRTCbus,10);
		waitforclickablityOfElement(garudaplusRTCbus,10);	
		click(garudaplusRTCbus);
		String[] actulbuscount = TSRTCbuscountOnHeding.getText().split(" ");
		String actualcont=actulbuscount[0];
	    int count=	TSRTCbuscountOnResultpage.size();
	}
	
	public void selectingTSRTCbus() 
	{
		waitforElement(viewBuses,10);
		click(viewBuses);
		double highestRating = 0;
     	WebElement selectedBus = null;
	    double lowestPrice = Double.MAX_VALUE;
	    for (WebElement bus : busList) {
	    	System.out.println(busList.size());
	    	double price = Double.parseDouble(bus.findElement(By.cssSelector("span.f-19")).getText().replaceAll("[^0-9.]", ""));
			double rating = Double.parseDouble(bus.findElement(By.cssSelector("div.rating-sec")).getText());

			if (price < lowestPrice || (price == lowestPrice && rating > highestRating)) {
	     	lowestPrice = price;
			highestRating = rating;
			selectedBus = bus;
			}
	     }
         
	    if(selectedBus != null) {
		System.out.println(selectedBus.getText());
		selectedBus.findElement(By.cssSelector("div.m-top-16 div.view-seats")).click();
		}
    }

	public void selectingdropingBoardingpoints() 
	{
		waitforElement(boardingpointbutton,5);
		click(boardingpointbutton);
		for (int i = 0; i < bordingDropingpointlist.size(); i++) {
			if (bordingDropingpointlist.get(i).getText().contains("Hebbal")) {
				click(bordingDropingpointlist.get(i));
				break;
			}
		}
		click(dropingpointbutton);
		for (int i = 0; i < bordingDropingpointlist.size(); i++) {
			if (bordingDropingpointlist.get(i).getText().contains("Central Bus Station (CBS)")) {
				click(bordingDropingpointlist.get(i));
				break;
			}
		}
		waitforclickablityOfElement(viewSeattap,30);
		click(viewSeattap);
		
	}
	

	public void seatSelect() 
	{
		
		WebElement canvasElement = driver.findElement(By.cssSelector("canvas.pointer"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].getContext('2d').strokeStyle = 'red';", canvasElement);

		Actions actions = new Actions(driver);
		actions.moveToElement(canvasElement, 10, 10)
		       .clickAndHold()
		       .moveByOffset(50, 0) 
		       .release() 
		       .perform(); 
		click(proceedToBookButton);
	}
	
	public void PassengerDetails() 
	{
		for (int i = 0; i < PassengerDetails.size(); i++) {
			click(PassengerDetails.get(0));
			type(PassengerDetails.get(0),propertiesObject.getProperty("Name"));
			click(PassengerDetails.get(1));
			type(PassengerDetails.get(1),propertiesObject.getProperty("Age"));
			click(PassengerDetails.get(2));
			type(PassengerDetails.get(2),propertiesObject.getProperty("CityofResidence"));
			click(PassengerDetails.get(4));
			type(PassengerDetails.get(4),propertiesObject.getProperty("EmailId"));
			click(PassengerDetails.get(5));
			type(PassengerDetails.get(5),propertiesObject.getProperty("Phone"));
			}
		click(proceedToPayButton);
		}

		
	}
		

