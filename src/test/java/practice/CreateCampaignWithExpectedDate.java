package practice;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import genericutility.ExcelFileUtility;
import genericutility.JavaUtility;
import genericutility.PropertyFileUtility;
import genericutility.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithExpectedDate {
    public static void main(String[] args) throws Throwable {
        // =========================
        // 1. READ CONFIG FROM PROPERTY FILE
        // =========================
        PropertyFileUtility pp = new PropertyFileUtility();                   // Utility to read values from property file
        String BROWSER = pp.readDataFromPropertyFile("Browser");             // Browser name (used to decide driver)
        String URL = pp.readDataFromPropertyFile("URL");                     // App URL
        String USERNAME = pp.readDataFromPropertyFile("Username");           // Login username
        String PASSWORD = pp.readDataFromPropertyFile("Password");           // Login password
    
        // =========================
        // 2. READ TEST DATA FROM EXCEL
        // =========================
        ExcelFileUtility ex = new ExcelFileUtility();
        String CampaignName = ex.readDataFromExcelFile("Campaign", 4, 2);     // Campaign name from excel
        String TargetSize = ex.readDataFromExcelFile("Campaign", 4, 3);       // Target size
        String Status = ex.readDataFromExcelFile("Campaign", 4, 4);           // Campaign status
        
        // =========================
        // 3. SETUP WEB DRIVER BASED ON BROWSER PROPERTY
        // =========================
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        
        if (BROWSER.equalsIgnoreCase("chrome")) {
            // Preferences to disable Chrome password manager popups and leak detection
            Map<String, Object> prefs = new HashMap<>();
    		prefs.put("credentials_enable_service", false);         // Disable Chrome credential service
    		prefs.put("profile.password_manager_enabled", false);   // Disable password manager popup
    		prefs.put("profile.password_manager_leak_detection", false); // Disable "leak detection" warnings
    		options.setExperimentalOption("prefs", prefs);          // Apply prefs
            
            driver = new ChromeDriver(options);                     // Initialize Chrome driver with options
        } else {
            // If property doesn't match, fallback to default ChromeDriver (explicit message)
            System.out.println("Invalid Browser! Defaulting to Chrome.");
            driver = new ChromeDriver();
        }

        // =========================
        // 4. INITIALIZE POMS & UTILITIES, OPEN URL
        // =========================
        driver.manage().window().maximize();                         // Maximize browser window
                                                    
        
        HomePage hp = new HomePage(driver);                          // Home page object
        WebDriverUtility wb = new WebDriverUtility();                // WebDriver utilities (waits etc.)
        CampaignsPage cp = new CampaignsPage(driver);                // Campaigns page object
        LoginPage lp = new LoginPage(driver);                        // Login page object
        JavaUtility jp = new JavaUtility();                          // Utility for Java helpers (e.g., date generation)
        CreateCampaignPage ccp = new CreateCampaignPage(driver);     // Create Campaign page object
        
        wb.implicitlyWait(driver);                                   // Set implicit wait
        lp.loginToApp(USERNAME, PASSWORD,URL);                           // Login to application
        
        // =========================
        // 5. NAVIGATE TO CREATE CAMPAIGN
        // =========================
        cp.getCamaignLink().click();                                 // Click Campaign link (to reach campaigns section)
        cp.getAddCreateCampaignBTN().click();                        // Click on "Add/Create Campaign" button
     
        // =========================
        // 6. FILL CAMPAIGN FIELDS (INCLUDING EXPECTED CLOSE DATE)
        // =========================
        ccp.getCampaignName().sendKeys(CampaignName);                // Enter Campaign Name
        ccp.getCampaignStatus().sendKeys(Status);                    // Enter Campaign Status
        
        ccp.getTargetSize().clear();                                 // Clear Target Size field
        ccp.getTargetSize().sendKeys(TargetSize);                    // Enter Target Size
        
        ccp.getExpectedCloseDate().sendKeys(jp.getRequireDate(30));  // Set Expected Close Date: current date + 30 days (JavaUtility handles date)
       
        ccp.getSubmitBTN().click();                                  // Submit to create campaign
       
        // =========================
        // 7. VERIFY TOAST & CLEANUP
        // =========================
        Thread.sleep(2000);                                          // Short pause to let toast appear (explicit sleep used here)
        
        hp.getToastMSG();                                            // Access toast element
        String Msg = hp.getToastMSG().getText();                     // Get toast text
        System.out.println("Toast Message: " + Msg);                 // Print toast for verification
        hp.getCloseToastMSG().click();                               // Close toast
        
        hp.logOut();                                                 // Logout
        driver.quit();                                               // Quit browser
    }
}
