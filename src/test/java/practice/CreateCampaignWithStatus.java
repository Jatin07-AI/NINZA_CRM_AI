package practice;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import genericutility.ExcelFileUtility;
import genericutility.PropertyFileUtility;
import genericutility.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithStatus {

	public static void main(String[] args) throws Throwable {

		// =========================================
		// 1. HANDLE CHROME OPTIONS (Disable Popups)
		// =========================================
		ChromeOptions options = new ChromeOptions();

		// Create a preferences map to disable unwanted Chrome popups
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);        // Disable Chrome credential service
		prefs.put("profile.password_manager_enabled", false);  // Disable "Save password" popup
		prefs.put("profile.password_manager_leak_detection", false); // Disable leak detection warnings

		// Apply preferences to Chrome options
		options.setExperimentalOption("prefs", prefs);

		// =========================================
		// 2. LAUNCH CHROME BROWSER
		// =========================================
		WebDriver driver = new ChromeDriver(options); // Launch browser with custom settings
		driver.manage().window().maximize();          // Maximize window for better visibility

		// Create utility & POM class objects
		WebDriverUtility wb = new WebDriverUtility();
		HomePage hp = new HomePage(driver);
		CreateCampaignPage ccp = new CreateCampaignPage(driver);

		// Apply implicit wait (global wait for elements)
		wb.implicitlyWait(driver);

		// =========================================
		// 3. READ DATA FROM PROPERTY FILE
		// =========================================
		PropertyFileUtility pp = new PropertyFileUtility();
		String BROWSER = pp.readDataFromPropertyFile("Browser");     // Browser name
		String URL = pp.readDataFromPropertyFile("URL");             // Application URL
		String USERNAME = pp.readDataFromPropertyFile("Username");   // Username
		String PASSWORD = pp.readDataFromPropertyFile("Password");   // Password

		// Open application URL in browser
		driver.get(URL);

		// =========================================
		// 4. LOGIN TO APPLICATION
		// =========================================
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD,URL);

		// =========================================
		// 5. READ DATA FROM EXCEL FILE
		// =========================================
		ExcelFileUtility ex = new ExcelFileUtility();
		String CampaignName = ex.readDataFromExcelFile("Campaign", 4, 2); // Campaign Name
		String TargetSize = ex.readDataFromExcelFile("Campaign", 4, 3);   // Target Size
		String Status = ex.readDataFromExcelFile("Campaign", 4, 4);       // Status

		// =========================================
		// 6. NAVIGATE TO CAMPAIGN MODULE
		// =========================================
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCamaignLink().click();           // Click on "Campaign" link
		cp.getAddCreateCampaignBTN().click();  // Click on "Create Campaign" button

		// =========================================
		// 7. FILL CAMPAIGN DETAILS
		// =========================================
		ccp.getCampaignName().sendKeys(CampaignName); // Enter campaign name
		ccp.getCampaignStatus().sendKeys(Status);     // Enter campaign status
		ccp.getTargetSize().clear();                  // Clear target size field
		ccp.getTargetSize().sendKeys(TargetSize);     // Enter new target size
		ccp.getSubmitBTN().click();                   // Submit campaign form

		// =========================================
		// 8. VALIDATE TOAST MESSAGE (Confirmation)
		// =========================================
		wb.waitForVisibilityOfWebElement(driver, hp.getToastMSG()); // Wait until toast appears

		if (hp.getToastMSG().getText().contains("test")) {
			System.out.println("Campaign Created ✅"); // Success message
		} else {
			System.out.println("Campaign Not Created ❌"); // Failure message
		}

		// Close toast message popup
		hp.getCloseToastMSG().click();

		// =========================================
		// 9. LOGOUT AND QUIT BROWSER
		// =========================================
		hp.logOut();   // Logout from application
		driver.quit(); // Close browser completely
	}
}
