package Scenario_Driver_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.BaseComponent;
import PageObject_Component.SearchTotalItem;

public class Search_an_item extends BaseComponent{

	public static Logger log = Logger.getLogger(Search_an_item.class);
	
	@Test(dataProvider="dp_InvalidSearch", dataProviderClass=DataProvider_Component.dataProvider.class, groups={"InValid"})
	public void Invalid_Search_Item(Map<String, String> hmap) throws IOException
	{
		String TC_ID = hmap.get("TC_ID");
		String Order = hmap.get("Order");
		String Search_Item = hmap.get("Search_Item");
		String Exp_Result = hmap.get("Expected_Result");
		
		extent_test=extent_report.startTest(TC_ID);
		log.info("Executing the test case "+TC_ID+" With Order "+Order+" For search Item "+Search_Item);
		
		//SoftAssert sAsset = new SoftAssert();
		
		SearchTotalItem si = new SearchTotalItem(driver);
		String Actual_Result = si.searchItem(Search_Item);
		
		if(Actual_Result.contains(Exp_Result))
		{
			log.info("Passed as Actual Result "+Actual_Result+" with Search Item "+Search_Item);
			
			extent_test.log(LogStatus.PASS, "Passed as Actual Result "+Actual_Result+" with Search Item "+Search_Item,extent_test.addScreenCapture(screenshot1(TC_ID, Order)));
		}
		else
		{
			log.info("Failed as Actual Result "+Actual_Result+" with Search Item "+Search_Item);
			extent_test.log(LogStatus.FAIL, "Failed as Actual Result "+Actual_Result+" with Search Item "+Search_Item,extent_test.addScreenCapture(screenshot1(TC_ID, Order)));
			sAsset.fail("Failed as Actual Result "+Actual_Result+" with Search Item "+Search_Item);
		}
	}
	
	@Test(dataProvider="dp_ValidSearch", dataProviderClass=DataProvider_Component.dataProvider.class, groups={"Valid"})
	public void Valid_Search_Item(Map<String, String> hmap) throws IOException
	{
		String TC_ID = hmap.get("TC_ID");
		String Order = hmap.get("Order");
		String Search_Item = hmap.get("Search_Item");
		String Exp_Result = hmap.get("Expected_Result");
		
		extent_test=extent_report.startTest(TC_ID);
		log.info("Executing the test case "+TC_ID+" With Order "+Order+" For search Item "+Search_Item);
		
		//SoftAssert sAsset = new SoftAssert();
		
		SearchTotalItem si = new SearchTotalItem(driver);
		String Actual_Result = si.searchItem(Search_Item);
		
		if(Actual_Result.contains(Exp_Result))
		{
			log.info("Passed as Actual Result "+Actual_Result+" with Search Item "+Search_Item);
			
			extent_test.log(LogStatus.PASS, "Passed as Actual Result "+Actual_Result+" with Search Item "+Search_Item,extent_test.addScreenCapture(screenshot1(TC_ID, Order)));
		}
		else
		{
			log.info("Failed as Actual Result "+Actual_Result+" with Search Item "+Search_Item);
			extent_test.log(LogStatus.FAIL, "Failed as Actual Result "+Actual_Result+" with Search Item "+Search_Item,extent_test.addScreenCapture(screenshot1(TC_ID, Order)));
			sAsset.fail("Failed as Actual Result "+Actual_Result+" with Search Item "+Search_Item);
		}
	}
	
}
