package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeAndResetUserAccountFieldsAndPasswordPage extends AbstractObjectPage{

	// input fields
	@FindBy (id="txtName")
	public WebElement userName;
	
	@FindBy (id="txtSurname")
	public WebElement userSurname;
	
	@FindBy (id="txtEmail")
	public WebElement userEmail;

	@FindBy (id = "txtOldPassword")
	public WebElement oldPassword;
	
	@FindBy (id = "txtNewPassword")
	public WebElement newPassword;
	
	@FindBy (id = "txtNewPasswordRepeat")
	public WebElement repeatNewPassword;

	//	check buttons

	@FindBy (xpath = "//div[2]/button")
	public WebElement agreeToResetUserPasswordButton;

	@FindBy (xpath = "//div[4]/div/button")
	public WebElement okResetPasswordButton;

	@FindBy (xpath = "//div[2]/div/button")
	public WebElement okButtonPasswordIsReset;

	@FindBy (id="btnSubmit")
	public WebElement buttonUpdateUserDetails;
	
	@FindBy (xpath = "//div[2]/div/button")
	public WebElement okButtonUserDetailsUpdated;
	
	@FindBy (xpath = "//button[@class='btn btn-primary'][text()='Keisti']")
	public WebElement changeUserPasswordButton;
	
	@FindBy (xpath = "//*/div[5]/button")
	public WebElement buttonSaveChangedPassword;
		
	@FindBy (xpath = "//div[2]/div/button")
	public WebElement okButtonPasswordChanged;
	
	public void changeUserName (String value) {
		userName.clear();
		userName.sendKeys(value);
	}
	
	public void changeUserSurname (String value) {
		userSurname.clear();
		userSurname.sendKeys(value);
	}
	
	public void changeUserEmail (String value) {
		userEmail.clear();
		userEmail.sendKeys(value);
	}
	
	public void clickButtonUpdateUserDetails () {
		buttonUpdateUserDetails.click();
	}
	
	
	public void clickOkResetPasswordButton () {
		okResetPasswordButton.click();
	}
	
	public void clickAgreeToResetUserPasswordButton () {
		agreeToResetUserPasswordButton.click();
	}
	
	public void clickOkButtonPasswordIsReset () {
		okButtonPasswordIsReset.click();
	}
	
	public void clickChangeUserPasswordButton () {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", changeUserPasswordButton);
	}
	
	public void enterOldPassword (String value) {
		oldPassword.sendKeys(value);
	}
	
	public void enterNewPassword (String value) {
		newPassword.sendKeys(value);
	}
	
	public void enterRepeatedNewPassword (String value) {
		repeatNewPassword.sendKeys(value);
	}
	
	public void clickButtonSaveChangedPassword () {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		js.executeScript("arguments[0].scrollIntoView()", buttonSaveChangedPassword);
		js.executeScript("arguments[0].click();", buttonSaveChangedPassword);
	}
	
	public void clickOkButtonPasswordChanged () {
		okButtonPasswordChanged.click();
	}
	
	public Boolean assertThatUserInformationWasUpdated() {
		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  	return wait.until(ExpectedConditions.textToBe
		  			(By.xpath("//body/div[2]/div/div[1]"), "Naudotojo duomenys buvo sėkmingai atnaujinti"));
	}
	
	public void clickOkButtonUserDetailsUpdated () {
		okButtonUserDetailsUpdated.click();
	}
	
	// constructor
	public ChangeAndResetUserAccountFieldsAndPasswordPage(WebDriver driver) {
		super(driver);
	}

}
