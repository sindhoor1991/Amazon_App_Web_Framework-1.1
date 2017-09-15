package PageObject_Component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Component.BaseComponent;

public class getAnItem extends BaseComponent{
	public RemoteWebDriver r = (RemoteWebDriver)driver;
	
	@FindBy(linkText="Reebok Men's Running Shoes")
	public WebElement ReebokShoeItem;
	
	@FindBy(linkText="HP 15-BU008TX 2017 15.6-inch Laptop (6th Gen Core i3-6006U/4GB/1TB/Free DOS/2GB Graphics), Sparkling Black")
	public WebElement HP_Laptop;
	
	@FindBy(id="twotabsearchtextbox")
	public WebElement searchBox;
	public getAnItem(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getAllShoeValues()
	{
		if((searchBox.getAttribute("value")).contains("Reebok"))
		{
			webdriverwait(ReebokShoeItem, 10);
		}
		else
		{
			webdriverwait(HP_Laptop, 10);
		}
		
		List<WebElement> allTagNameItems = driver.findElements(By.tagName("a"));
				
		int flag=0;
		String text = null;
		int size = allTagNameItems.size();
		for(int i=0;i<size;i++)
		{
			if((allTagNameItems.get(i).getText().contains("Reebok Men's Edge Quick 2.0 Running Shoes")) || (allTagNameItems.get(i).getText().contains("HP 15-BU008TX 2017 15.6-inch Laptop")))
			{
				
				flag=1;
				WebElement ele = allTagNameItems.get(i);
				text = ele.getText();
				Point p = ele.getLocation();
				int y= p.getY();
				r.executeScript("window.scrollTo(0,"+y+")");
				webdriverwait(ele, 10);
				break;
			}
			else
				flag=0;
		}
		if(flag==1)
		{
			return text;
		}
		else
			return null;
		
	}
}
