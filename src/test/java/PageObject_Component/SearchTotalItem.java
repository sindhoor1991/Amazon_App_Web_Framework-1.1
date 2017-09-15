package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Component.BaseComponent;

public class SearchTotalItem extends BaseComponent{

	@FindBy(id="twotabsearchtextbox")
	public WebElement search_textbox;
	
	@FindBy(xpath="//input[@class='nav-input'][@value='Go']")
	public WebElement search_icon;
	
	@FindBy(id="noResultsTitle")
	public WebElement text_InvalidItems;
	
	@FindBy(id="s-result-count")
	public WebElement text_ValidItems;
	
	public SearchTotalItem(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String searchItem(String text)
	{
		webdriverwait(search_textbox, 10);
		search_textbox.clear();
		webdriverwait(search_textbox, 10);
		search_textbox.sendKeys(text);
		
		search_icon.click();
		String text2 = search_textbox.getAttribute("value");
		
		if((text2.contains("#")) || (text2.contains("@")))
		{
			webdriverwait(text_InvalidItems, 10);
			return text_InvalidItems.getText();
		}
		else
			webdriverwait(text_ValidItems, 10);
			return text_ValidItems.getText();
			
	}
}
