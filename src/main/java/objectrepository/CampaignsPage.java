package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {

	WebDriver driver;
	
	public CampaignsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Campaigns")
	private WebElement camaignLink;
	
	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement addCreateCampaignBTN;

	public WebElement getAddCreateCampaignBTN() {
		return addCreateCampaignBTN;
	}

	public WebElement getCamaignLink() {
		return camaignLink;
	}
	
	
	
	
}
