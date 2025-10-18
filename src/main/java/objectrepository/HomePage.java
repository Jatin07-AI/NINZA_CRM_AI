package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutility.WebDriverUtility;

public class HomePage {
	
WebDriver driver;
WebDriverUtility wb = new WebDriverUtility();

public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy (xpath = "//div[@class='user-icon']")
	private WebElement userIcon;
	
	@FindBy (xpath = "//div[text()='Logout ']")
	private WebElement logoutBtn;
	
	@FindBy (xpath = "//div[@role='alert']")
	private WebElement toastMSG;
	
	@FindBy (xpath = "//button[@aria-label='close']")
	private WebElement closeToastMSG;
	
	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	public WebElement getToastMSG() {
		return toastMSG;
	}

	public WebElement getCloseToastMSG() {
		return closeToastMSG;
	}

	public void logOut() {
		wb.waitForVisibilityOfWebElement(driver, userIcon);
		wb.mouseHoverOnWebElement(driver, userIcon);
		wb.safeClick(driver, logoutBtn);
	}
	
}
