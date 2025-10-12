package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		//Create Java representation object of physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/Commondata.properties");
		
		//Create object of properties class
		Properties prop = new Properties();
		
		//Load the keys from java representation object
		prop.load(fis);
		
		String Browser = prop.getProperty("Browser");
		
		System.out.println(Browser);
		
		
	}

}
