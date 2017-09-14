package Generic_Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityComponent {

	public static String readProperties(String sKey) throws IOException
	{
		FileInputStream fis = new FileInputStream("./loginDetails.properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(sKey);
	}
}
