package adminPages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractObjectPage;

public class CreateAndDeleteNewUserPage extends AbstractObjectPage {

	//input fields	
	@FindBy (id= "txtEmail")
	public WebElement inputEmail;
	
	@FindBy (id = "txtName")
	public WebElement inputName;
	
	@FindBy (id="txtSurname")
	public WebElement inputSurname;
	
	@FindBy (id = "txtPersonalCode")
	public WebElement inputPersonalCode;
	
	@FindBy (id = "txtTelNo")
	public WebElement inputPhoneNumber;
	
	@FindBy (id = "txtAddress")
	public WebElement inputAddress;
	
	//buttons
//	@FindBy (id = "btnCreate")
	@FindBy (xpath = "//*[@id='btnCreate'][text()='Sukurti']")
	public WebElement createButton;
	
	@FindBy (xpath = "//div[2]/div/button")
//	button[@class='swal-button swal-button--confirm']
	public WebElement okButtonUserIsCreated;
	
	@FindBy (xpath = "/html/body/div[2]/div/div[2]/div/button")
	public WebElement userNotLoggedInButton;

	public void clickCreateButton() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", createButton);
	}
	
	public void enterEmail (String value) {
		inputEmail.sendKeys(value);
	}
	
	public void enterName (String value) {
		inputName.sendKeys(value);
	}
	
	public void enterSurname (String value) {
		inputSurname.sendKeys(value);
	}
	
	public void enterPersonalCode (String value) {
		inputPersonalCode.sendKeys(value);
	}
	
	public void enterPhoneNumber (String value) {
		inputPhoneNumber.sendKeys(value);
	}
	
	public void enterAddress (String value) {
		inputAddress.sendKeys(value);
	}
	
//	public void clickCreateButton () {
//		createButton.click();
//	}
	
	public void clickOKButtonUserIsCreated () {
		okButtonUserIsCreated.click();
	}
	
	public void clickOkUserNotLoggedInButton () {
		userNotLoggedInButton.click();
	}
		
	// constructor
	public CreateAndDeleteNewUserPage(WebDriver driver) {
		super(driver);
	}
}
