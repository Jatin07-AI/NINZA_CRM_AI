package campaigntest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import baseClass.BasePage;
import genericutility.ExcelFileUtility;
import genericutility.JavaUtility;
import genericutility.PropertyFileUtility;
import genericutility.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;


  //Test class to validate Campaign Creation module
  //Extends BasePage to inherit browser setup, login & logout-from app

@Listeners(genericutility.ListenerImplementation.class)
public class CreateCampaignTest extends BasePage {
	

	
	 // Create Campaign with Expected Close Date!!-Jatin
	 
	@Test(groups = {"smoke","regression"})
	public void CreateCampaignWithExpectedCloseDatetest() throws Throwable {
		
		// =========================
		// 1. READ TEST DATA FROM EXCEL!!
		// =========================
		String CampaignName = elib.readDataFromExcelFile("Campaign", 4, 2);  // Campaign name
		String TargetSize = elib.readDataFromExcelFile("Campaign", 4, 3);    // Target size
		String Status = elib.readDataFromExcelFile("Campaign", 4, 4);        // Campaign status
	   
		// =========================
		// 2. PAGE OBJECT INITIALIZATION
		// =========================
		HomePage hp = new HomePage(driver);
		CampaignsPage cp = new CampaignsPage(driver);
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		
		// =========================
		// 3. NAVIGATE TO CREATE CAMPAIGN
		// =========================
		cp.getCamaignLink().click();  // Click Campaign link
		wlib.safeClick(driver, cp.getAddCreateCampaignBTN());
		
		// =========================
		// 4. FILL CAMPAIGN FIELDS
		// =========================
		ccp.getCampaignName().sendKeys(CampaignName);
		ccp.getCampaignStatus().sendKeys(Status);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(TargetSize);

		// Expected Close Date = current date + 30 days
		ccp.getExpectedCloseDate().sendKeys(jlib.getRequireDate(30));
	   
		// Submit campaign form
		ccp.getSubmitBTN().click();
	   
		// =========================
		// 5. VALIDATE TOAST MESSAGE
		// =========================
		wlib.waitForVisibilityOfWebElement(driver, hp.getToastMSG());
		Assert.assertTrue(hp.getToastMSG().getText().contains(CampaignName));
		wlib.waitForVisibilityOfWebElement(driver, hp.getCloseToastMSG());
		hp.getCloseToastMSG().click();
	}

	/**
	 * Create Campaign with only mandatory fields
	 */
	@Test(groups = "regression")
	public void CreateCampaignWithMandatoryFieldstest() throws Throwable {	
		
		// Read data from Excel
		String CampaignName = elib.readDataFromExcelFile("Campaign", 1, 2);
		String TargetSize = elib.readDataFromExcelFile("Campaign", 1, 3);

		// Navigate to campaign creation
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBTN().click();
		
		// Fill form
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(CampaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(TargetSize);
		ccp.getSubmitBTN().click();
		
		// Validate toast message
		HomePage hp = new HomePage(driver);
		wlib.waitForVisibilityOfWebElement(driver, hp.getToastMSG());
		Assert.assertTrue(hp.getToastMSG().getText().contains(CampaignName));
		wlib.waitForVisibilityOfWebElement(driver, hp.getCloseToastMSG());
		hp.getCloseToastMSG().click();
	}
		
	/**
	 * Create Campaign with Status field validation
	 */
	@Test(groups = "regression")
	public void CreateCampaignWithStatustest() throws Throwable {
		
		// Read data from Excel
		String CampaignName = elib.readDataFromExcelFile("Campaign", 4, 2);
		String TargetSize = elib.readDataFromExcelFile("Campaign", 4, 3);
		String Status = elib.readDataFromExcelFile("Campaign", 4, 4);

		// Navigate to campaign section
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCamaignLink().click();
		cp.getAddCreateCampaignBTN().click();

		// Fill form
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(CampaignName);
		ccp.getCampaignStatus().sendKeys(Status);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(TargetSize);
		ccp.getSubmitBTN().click();

		// Validate toast message
		HomePage hp = new HomePage(driver);
		wlib.waitForVisibilityOfWebElement(driver, hp.getToastMSG());
		Assert.assertTrue(hp.getToastMSG().getText().contains(CampaignName));
		wlib.waitForVisibilityOfWebElement(driver, hp.getCloseToastMSG());
		hp.getCloseToastMSG().click();
	}
}
