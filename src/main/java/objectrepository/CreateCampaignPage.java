package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {

	WebDriver driver;
	
	public CreateCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//FindAll work like OR condtion if anyone is true then it will be executed
	//name = 'camName' we take wrong to see the results
	@FindAll({@FindBy(name = "campaName"),@FindBy(name = "campaignName")})
	private WebElement campaignName;
	
	//It's working like AND operator if both are right thn it will execute either gives no such element exception
	//name = 'camName' we take wrong to see the results
	//@FindBys({@FindBy(name = "campaName"),@FindBy(name = "campaignName")})
	//private WebElement campaignName;
	
	@FindBy(name = "campaignStatus")
	private WebElement campaignStatus;
	
	@FindBy(name = "targetSize")
	private WebElement targetSize;
	
	@FindBy (name = "expectedCloseDate")
	private WebElement expectedCloseDate;
	
	@FindBy (name = "targetAudience")
	private WebElement targetAudiance;
	
	@FindBy (name = "description")
	private WebElement description;
	
	@FindBy (xpath = "//button[text()='Create Campaign']")
	private WebElement submitBTN;

	public WebElement getCampaignName() {
		return campaignName;
	}

	public WebElement getCampaignStatus() {
		return campaignStatus;
	}

	public WebElement getTargetSize() {
		return targetSize;
	}

	public WebElement getExpectedCloseDate() {
		return expectedCloseDate;
	}

	public WebElement getTargetAudiance() {
		return targetAudiance;
	}

	public WebElement getDescription() {
		return description;
	}

	public WebElement getSubmitBTN() {
		return submitBTN;
	}

	
	
}
