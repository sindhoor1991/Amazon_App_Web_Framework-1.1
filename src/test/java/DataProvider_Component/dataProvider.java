package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class dataProvider {

	@DataProvider(name="dp_ValidSearch")
	public static Iterator<Object []> getvalid_searchData() throws IOException
	{
		return get_commons_method_data("Search_Scenario", "Valid");
		
	}
	
	@DataProvider(name="dp_ValidSearchItem")
	public static Iterator<Object []> getvalid_searchDataItem() throws IOException
	{
		return get_commons_method_data("Search_Item", "Valid");
		
	}
	
	@DataProvider(name="dp_InvalidSearch")
	public static Iterator<Object []> getInvalid_searchData() throws IOException
	{
		return get_commons_method_data("Search_Scenario", "Invalid");
		
	}
	
	public static Iterator<Object []> get_commons_method_data(String sheetName,String scriptName) throws IOException
	{
		
		ExcelReadWrite xl = new ExcelReadWrite("./ExcelSheet/Amazon_Framework1.1.xls");
		HSSFSheet sheet = xl.Setsheet(sheetName);
		
		int Rowcount = xl.getrowcount(sheet);
		int Colcount = xl.getcolcount(sheet, 0);
		
		List<Object[]> li = new ArrayList<Object[]>();
		
		for(int i=1;i<=Rowcount;i++)
		{
			String execute_flag = xl.Readvalue(sheet, i, "Execute_Flag");
			String script_name = xl.Readvalue(sheet, i, "Script_Name");
			
			if(execute_flag.equalsIgnoreCase("Y") && script_name.equalsIgnoreCase(scriptName))
			{
				Object[] obj = new Object[1];
				Map<String, String> hmap =  new HashMap<String, String>();
				
				for(int j=0;j<Colcount;j++)
				{
					String sKey = xl.Readvalue(sheet, 0, j);
					String sValue = xl.Readvalue(sheet, i, j);
					
					hmap.put(sKey, sValue);	
				}
				
				obj[0] = hmap;
				li.add(obj);
			}
		}
		
		return li.iterator();
				
	}
	
}
