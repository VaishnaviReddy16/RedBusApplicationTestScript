package BaseItem;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
   protected WebDriver driver;
	DataFormatter formater = new DataFormatter();
	
	@BeforeMethod
	public void browseer(){
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishnavi.reddy\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
	 driver=new ChromeDriver();
	   // WebDriver driver=new EdgeDriver();
	//driver.get("https://www.redbus.in/");
	driver.get("https://www.redbus.in/bus-tickets/bangalore-to-hyderabad?fromCityName=Bengaluru&fromCityId=122&srcCountry=IND&toCityName=Hyderabad&toCityId=124&destCountry=IND&onward=30-Aug-2023&opId=0&busType=Any");	
	 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@DataProvider(name = "DriverTesttt")
	public Object[][] getdata() throws IOException {
		FileInputStream filename = new FileInputStream(
				"C:\\Users\\vaishnavi.reddy\\OneDrive - Qualitest Group\\RedBusInputs.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(filename);
		XSSFSheet firstSheet = workBook.getSheetAt(0);
		
		int rowcount = firstSheet.getPhysicalNumberOfRows();
		XSSFRow row = firstSheet.getRow(0);
		//int column = row.getLastCellNum();
		Object data[][] = new Object[rowcount][2];
		for (int i = 0; i < rowcount ; i++) {
			row = firstSheet.getRow(i);
			//for (int j = 0; j < column; j++) {
				//XSSFCell cell = row.getCell(j);
				data[i][0] = row.getCell(0).getStringCellValue();
				data[i][1] = row.getCell(1).getStringCellValue();
			}
		
		return data;
	}

	//@AfterTest
	//public void closeBrwoser() {
	// driver.quit();	
}
