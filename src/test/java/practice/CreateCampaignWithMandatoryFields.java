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

public class CreateCampaignWithMandatoryFields {

	public static void main(String[] args) throws Throwable{	
		
		// =========================
		// 1. READ CONFIG FROM PROPERTY FILE
		// =========================
		PropertyFileUtility pp = new PropertyFileUtility();                       // Utility to read property file
		String BROWSER = pp.readDataFromPropertyFile("Browser");                 // Browser name from properties (not used further here but kept for consistency)
		String URL = pp.readDataFromPropertyFile("URL");                         // Application URL
		String USERNAME = pp.readDataFromPropertyFile("Username");               // Login username
		String PASSWORD = pp.readDataFromPropertyFile("Password");               // Login password
		
		// =========================
		// 2. READ TEST DATA FROM EXCEL
		// =========================
		ExcelFileUtility ex = new ExcelFileUtility();                           
		String CampaignName = ex.readDataFromExcelFile("Campaign", 1, 2);        // Campaign name from Excel sheet "Campaign" row 1 col 2
		String TargetSize = ex.readDataFromExcelFile("Campaign", 1, 3);          // Target size from Excel sheet "Campaign" row 1 col 3	
		
		// =========================
		// 3. SET CHROME OPTIONS (disable password manager popups)
		// =========================
		ChromeOptions options = new ChromeOptions();
		
		// Create preferences map to disable password manager
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);         // Disable Chrome credential service
		prefs.put("profile.password_manager_enabled", false);   // Disable password manager popup
		prefs.put("profile.password_manager_leak_detection", false); // Disable "leak detection" warnings
		options.setExperimentalOption("prefs", prefs);          // Apply prefs to ChromeOptions
		
		
		// =========================
		// 4. LAUNCH BROWSER & INIT PAGE OBJECTS / UTILITIES
		// =========================
		WebDriver driver = new ChromeDriver(options);           // Start Chrome with custom options
		driver.manage().window().maximize();                    // Maximize browser window for visibility
		
		HomePage hp = new HomePage(driver);                     // Page object for Home page
		WebDriverUtility wb = new WebDriverUtility();          // Generic webdriver utility (implicit/explicit waits etc.)
		CampaignsPage cp = new CampaignsPage(driver);           // Page object for Campaigns listing
		CreateCampaignPage ccp = new CreateCampaignPage(driver); // Page object for Create Campaign page
		
		wb.implicitlyWait(driver);                              // Set implicit wait (global)
		
		// =========================
		// 5. NAVIGATE TO APPLICATION & LOGIN
		// =========================
		                                      
		LoginPage lp = new LoginPage(driver);                   // Login page object
		lp.loginToApp(USERNAME, PASSWORD,URL);                      // Perform login using credentials
		
		// =========================
		// 6. NAVIGATE TO CREATE CAMPAIGN & FILL MANDATORY FIELDS
		// =========================
		cp.getAddCreateCampaignBTN().click();                   // Click "Create Campaign" button (assumes already on campaigns)
		ccp.getCampaignName().sendKeys(CampaignName);           // Enter Campaign Name into text field
		
		// Clear then set Target Size value
		ccp.getTargetSize().clear();                            // Clear any pre-filled value in target size field
		ccp.getTargetSize().sendKeys(TargetSize);               // Enter Target Size value
		
		// Submit the campaign form
		ccp.getSubmitBTN().click();                             // Click Submit button to create campaign
		
		
		// =========================
		// 7. VALIDATE CREATION VIA TOAST MESSAGE
		// =========================
		wb.waitForVisibilityOfWebElement(driver, hp.getToastMSG()); // Wait until toast notification is visible
		
		// Check the toast message contains expected text (simple verification)
		if (hp.getToastMSG().getText().contains("test")) {      // If toast contains 'test' string
			System.out.println("Campaign Created ✅");          // Print success
		} else {
			System.out.println("Campaign Not Created ❌");      // Print failure
		}
		
		hp.getCloseToastMSG().click();                          // Close the toast notification popup
		
		// =========================
		// 8. LOGOUT & CLEANUP
		// =========================
		hp.logOut();                                            // Perform logout (HomePage POM handles it)
		driver.quit();                                          // Close browser and end session
	}
}
