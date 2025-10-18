package contacttest;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseClass.BasePage;
import objectrepository.CampaignsPage;
import objectrepository.ContactsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.CreateContactPage;
import objectrepository.HomePage;
import objectrepository.SelectCampaignPage;

public class CreateContactTest extends BasePage {

	@Test(groups = {"smoke","regression"})
	public void createContactWithMandatoryFieldstest() throws Throwable {
		// Reading data from excel file
				String CAMPAIGN_NAME =  elib .readDataFromExcelFile("Contact", 1, 2);
				String TARGET_SIZE = elib.readDataFromExcelFile("Contact", 1, 3);
				String ORGANIZATION_NAME = elib.readDataFromExcelFile("Contact", 1, 4);
				String TITLE = elib.readDataFromExcelFile("Contact", 1, 5);
				String CONTACT_NAME = elib.readDataFromExcelFile("Contact", 1, 6);

				// Create Campaign with Mandatory Fields
				CampaignsPage cp = new CampaignsPage(driver);
				wlib.waitForVisibilityOfWebElement(driver, cp.getAddCreateCampaignBTN());
				cp.getAddCreateCampaignBTN().click();
	
				CreateCampaignPage ccp = new CreateCampaignPage(driver);
				ccp.getCampaignName().sendKeys(CAMPAIGN_NAME);
				ccp.getTargetSize().clear();
				ccp.getTargetSize().sendKeys(TARGET_SIZE);
				ccp.getSubmitBTN().click();

				HomePage hp = new HomePage(driver);
				WebElement toastMsg = hp.getToastMSG();
				wlib.waitForVisibilityOfWebElement(driver, toastMsg);
				hp.getCloseToastMSG().click();
				
				//CreateContact
				hp.getContactsLink().click();
				ContactsPage contactPage=new ContactsPage(driver);
				contactPage.getAddCreateContactBTN().click();
				CreateContactPage createContactPage=new CreateContactPage(driver);
				createContactPage.getOrganizationNameTF().sendKeys(ORGANIZATION_NAME);
				createContactPage.getTitleTF().sendKeys(TITLE);
				createContactPage.getContactNameTF().sendKeys(CONTACT_NAME);
				// Generate unique number using timestamp
				long timestamp = System.currentTimeMillis();
				String uniqueMobile = "900000" + String.valueOf(timestamp).substring(7); // last 6 digits
				createContactPage.getMobileTF().sendKeys(uniqueMobile);
				String parentId = driver.getWindowHandle();
				createContactPage.getPlusBtn().click();
				wlib.switchToWindowOnTitle(driver, "Select Campaign");
				SelectCampaignPage scp=new SelectCampaignPage(driver);
				wlib.select(scp.getCampaignDD(), "campaignName");
				scp.getSearchBar().sendKeys(CAMPAIGN_NAME);
				wlib.waitForVisibilityOfWebElement(driver, scp.getSelectBtn());
				scp.getSelectBtn().click();
				wlib.switchBackToParentId(driver, parentId);
				createContactPage.getCreateContactSubmitBtn().click();
				WebElement toastMsg1 = hp.getToastMSG();
				wlib.waitForVisibilityOfWebElement(driver, toastMsg1);
				Assert.assertTrue(toastMsg1.getText().contains(CONTACT_NAME));
				wlib.clickOnWebElement(driver, hp.getCloseToastMSG());
				//hp.getCloseToastMSG().click();
				
					
	}
}