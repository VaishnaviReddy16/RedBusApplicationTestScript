package BaseItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
   protected WebDriver driver;
	DataFormatter formater = new DataFormatter();
	
	@BeforeMethod
	public void browseer(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishnavi.reddy\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
	   	ChromeOptions option=new ChromeOptions();
    	option.addArguments("--disable-notifications");
	    driver=new ChromeDriver(option);  
	    
	    driver.get("https://www.redbus.in/");	    
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

	@AfterTest
	public void closeBrwoser() {
	TakesScreenshot screenshot = (TakesScreenshot) driver;
	File file = screenshot.getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(file, new File(
				"C:\\Users\\vaishnavi.reddy\\eclipse-workspace\\RedBusBookingWebsite\\ScreenShots\\failedtestcase.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 //driver.quit();	
    }
}
