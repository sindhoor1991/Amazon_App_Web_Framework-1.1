package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseComponent {
	
	public SoftAssert sAsset = new SoftAssert();
	public WebDriver driver;
	
	public static ExtentReports extent_report;
	public static ExtentTest extent_test;
	
	
	@Parameters("browser")
	@BeforeClass
	public void launchBrowser(String browser) throws IOException
	{
		String URL= UtilityComponent.readProperties("URL");
		if(browser.equalsIgnoreCase("FF"))
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
		
		
	}
	
	
	@BeforeSuite
	public String screenshot1(String TC_ID, String Order) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
		String str = df.format(date)+".png";
		
		TakesScreenshot scrennshot = (TakesScreenshot)driver;
		File screenshotAs = scrennshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\Sindhoor_Automation\\1_Framework_AmazonApp\\Screenshots"+TC_ID+"-"+Order+"-"+str));
		
		String path = "D:\\Sindhoor_Automation\\1_Framework_AmazonApp\\Screenshots"+TC_ID+"-"+Order+"-"+str;
		return path;
	}
	
	public static void init_extentSetup()
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
		String report = df.format(date);
		extent_report = new ExtentReports("D:\\Sindhoor_Automation\\1_Framework_AmazonApp\\Reoprts"+"AmazonProject"+"-"+report+".html",false);
		
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
		sAsset.assertAll();
		extent_report.endTest(extent_test);
		extent_report.flush();
	}
	
	
}