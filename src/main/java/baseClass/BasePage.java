package baseClass;

import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import genericutility.ExcelFileUtility;
import genericutility.JavaUtility;
import genericutility.PropertyFileUtility;
import genericutility.WebDriverUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BasePage {
	public WebDriver driver = null;
	public PropertyFileUtility plib = new PropertyFileUtility();
	public ExcelFileUtility elib = new ExcelFileUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public JavaUtility jlib = new JavaUtility();
	public static WebDriver sdriver = null;
	
	// =========================
	// SUITE LEVEL
	// =========================
	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuite() {
		System.out.println("Connect to the database");
	}

	@AfterSuite(groups = {"smoke","regressions"})
	public void afterSuite() {
		System.out.println("Disconnect to the database");
	}
	  
	// =========================
	// TEST LEVEL
	// =========================
	@BeforeTest(groups = {"smoke","regression"})
	public void beforeTest() {
		System.out.println("Pre conditions for parallel execution");
	}

	@AfterTest(groups = {"smoke","regression"})
	public void afterTest() {
		System.out.println("Post Condition for parallel execution");
	}

	// =========================
	// CLASS LEVEL
	// =========================
	@Parameters("Browser")
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClass(@Optional("chrome") String BROWSER) throws Throwable {
		System.out.println("Launch the browser");

		// Launch the browser based on property file
		//String BROWSER = plib.readDataFromPropertyFile("Browser");
		
		//Take Browser from maven command line at that time we use this line either we do comment it
		//String BROWSER = System.getProperty("Browser");
		
		ChromeOptions options = new ChromeOptions();

		if (BROWSER.equalsIgnoreCase("chrome")) {
			// Disable Chrome's default password manager & popups
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
		} else if (BROWSER.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", "C:\\Drivers\\edgedriver_win64\\msedgedriver.exe");
                driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		sdriver=driver;
		driver.manage().deleteAllCookies();

		// Maximize and apply implicit wait
		driver.manage().window().maximize();
		wlib.implicitlyWait(driver);
	}

	@AfterClass(groups = {"smoke","regression"})
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();
	}

	// =========================
	// METHOD LEVEL
	// =========================
	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethod() throws Throwable {
		System.out.println("Login");

		// Fetch application details from property file
		String URL = plib.readDataFromPropertyFile("URL");
		String USERNAME = plib.readDataFromPropertyFile("Username");
		String PASSWORD = plib.readDataFromPropertyFile("Password");
		
		//Take it data from maven command line
//		String URL = System.getProperty("URL");
//		String USERNAME = System.getProperty("Username");
//		String PASSWORD = System.getProperty("Password");

		// Perform login using Page Object
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD, URL);
	}

	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethod() {
		System.out.println("Logout");

		// Perform logout using Page Object
		HomePage hp = new HomePage(driver);
		hp.logOut();
		
	}
}
