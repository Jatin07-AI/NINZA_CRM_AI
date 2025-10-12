package testNGPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestNG {

	//Priority means which one executing first.
	//Invocation count means how many times test case need to be executed.
	//Threadpoolsize means how many parallel execution need to do.
	//enabled means test case need to be execute or not (enabled=true,enabled=false)
	
	@Test(priority = 1,invocationCount = 4,threadPoolSize = 2,enabled = true)
	public void CreateCampaignWithMandatoryFields() {
		WebDriver driver = new ChromeDriver();
		System.out.println("CreateCampaignWithMandatoryFields");
	}
	
	@Test(priority = -1,invocationCount = 4,threadPoolSize = 4,enabled = false)
	public void CreateCampaignWithStatus() {
		WebDriver driver = new ChromeDriver();
		System.out.println("CreateCampaignWithStatus");
	}
	
	@Test(priority = 2,invocationCount = 5,threadPoolSize = 3)
	public void CreateCampaignWithCloseDate() {
		WebDriver driver = new ChromeDriver();
		System.out.println("CreateCampaignWithCloseDate");
	}
}
