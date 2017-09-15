package Scenario_Driver_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.BaseComponent;
import PageObject_Component.SearchTotalItem;
import PageObject_Component.getAnItem;

public class searchParticularItem extends BaseComponent{
	public static Logger log = Logger.getLogger(searchParticularItem.class);
		
	@Test(dataProvider="dp_ValidSearchItem", dataProviderClass=DataProvider_Component.dataProvider.class, groups={"Valid"})
	public void searchAParticularItem(Map<String, String> hmap) throws IOException
	{
		String TC_ID = hmap.get("TC_ID");
		String Order = hmap.get("Order");
		String Search_Item = hmap.get("Search_Item");
		String Exp_Result = hmap.get("Expected_Result");
		
		extent_test=extent_report.startTest(TC_ID);
		log.info("Executing the test case "+TC_ID+" With Order "+Order+" For search Item "+Search_Item);
		
		SearchTotalItem item = new SearchTotalItem(driver);
		String Actual_Result = item.searchItem(Search_Item);
		System.out.println(Actual_Result);
		
		getAnItem item1 = new getAnItem(driver);
		String Actual_Result1 = item1.getAllShoeValues();
		
		if(Actual_Result1.contains(Exp_Result))
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
