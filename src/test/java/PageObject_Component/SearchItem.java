package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.javascript.host.media.rtc.webkitRTCPeerConnection;

public class SearchItem {

	@FindBy(id="twotabsearchtextbox")
	public WebElement search_textbox;
	
	@FindBy(xpath="//input[@class='nav-input'][@value='Go']")
	public WebElement search_icon;
	
	@FindBy(id="noResultsTitle")
	public WebElement result_text;
	
	@FindBy(id="s-result-count")
	public WebElement text_ValidItems;
	
	public SearchItem(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String searchItem(String text)
	{
		search_textbox.sendKeys(text);
		//wait
		search_icon.click();
		
		String text2 = search_textbox.getText();
		if(text2.contains("Reebok"))
		{
			return text_ValidItems.getText();
		}
		else if(text2.contains("Reebok"))
		{
			return text_ValidItems.getText();
		}
		else if(text2.contains("Water"))
		{
			return text_ValidItems.getText();
		}
		else
			return result_text.getText();
	}
	
	
}
