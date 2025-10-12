package testNGPractice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutility.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class DataProviderTestNG {

	@Test(dataProvider = "loginDetails")
	public void Login(String username,String password) {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		String url = "http://49.249.28.218:809/";
		lp.loginToApp(username,password,url);
		HomePage hp = new HomePage(driver);
		hp.logOut();
		driver.quit();
	}
	
//	@DataProvider
//	public  Object[][] loginDetails(){
//		Object[][] objArr =  new  Object[4][2];
//		
//		objArr[0][0]="rmgyantra";
//		objArr[0][1]="rmgy@9999";
//		objArr[1][0]="rmgyantra1";
//		objArr[1][1]="rmgy@1122";
//		objArr[2][0]="rmgyantra2";
//		objArr[2][1]="rmgy@8899";
//		objArr[3][0]="rmgyantra3";
//		objArr[3][1]="rmgy@0011";
//		
//		return objArr;
//	}
	
	@DataProvider
	public  Object[][] loginDetails() throws Throwable{
		Object[][] objArr =  new  Object[5][2];
		
		ExcelFileUtility ex = new ExcelFileUtility();
		int rowCount = ex.getRowCount("DataProvider");
		
		for(int i=1;i<=rowCount;i++) {
			
			objArr[i-1][0]=ex.readDataFromExcelFile("DataProvider", i, 0);
			objArr[i-1][1]=ex.readDataFromExcelFile("DataProvider", i, 1);
		}
		
		return objArr;
}
}
