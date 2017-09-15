package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseComponent {
	
	public SoftAssert sAsset = new SoftAssert();
	public static WebDriver driver;
	
	public static ExtentReports extent_report;
	public static ExtentTest extent_test;
	
	
	//@Parameters("browser")
	@BeforeTest
	//public void launchBrowser(String browser) throws IOException
	public void launchBrowser() throws IOException
	{
		String URL= UtilityComponent.readProperties("URL");
		/*if(browser.equalsIgnoreCase("FF"))
		{
			System.setProperty("webdriver.gecko.driver", "./exeFiles/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(URL);
		}
		else if(browser.equalsIgnoreCase("GC"))
		{
			System.setProperty("webdriver.chrome.driver", "./exeFiles/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(URL);
		}
		
*/		
		System.setProperty("webdriver.chrome.driver", "./exeFiles/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
	}
	
	
	
	public String screenshot1(String TC_ID, String Order) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
		String str = df.format(date)+".png";
		
		TakesScreenshot scrennshot = (TakesScreenshot)driver;
		File screenshotAs = scrennshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("./Screenshots/"+TC_ID+"-"+Order+"-"+str));
		
		String path = "./Screenshots/"+TC_ID+"-"+Order+"-"+str;
		return path;
	}
	
	@BeforeSuite
	public static void init_extentSetup()
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
		String report = df.format(date);
		extent_report = new ExtentReports("./Reports/"+"AmazonProject"+"-"+report+".html",false);
		
	}
	
	public static void webdriverwait(WebElement ele,long t1)
	{
		WebDriverWait wait = new WebDriverWait(driver, t1);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	@AfterTest
	public void CloseBrowser()
	{
		driver.close();
		sAsset.assertAll();
		extent_report.endTest(extent_test);
		extent_report.flush();
	}
		
}
