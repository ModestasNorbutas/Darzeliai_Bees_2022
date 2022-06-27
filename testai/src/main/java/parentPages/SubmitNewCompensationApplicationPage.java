package parentPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractObjectPage;
import utilities.FileReaderUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;



public class SubmitNewCompensationApplicationPage extends AbstractObjectPage {

    //buttons
    @FindBy(id = "chooseCompensationFormButton")
    public WebElement buttonApplicationForCompensation;

    @FindBy(id = "submitCompensationButton")
    public WebElement buttonSubmitCompensationsApplication;

    @FindBy (id = "btnReviewCompensationUser")
    public WebElement buttonReviewCompensation;

    @FindBy (id = "CompensationReviewEdit")
    public WebElement buttonEditCompensationsApplication;

    @FindBy (id = "editCompensationButton")
    public WebElement buttonEditCompensationButton;

    //input fields (private kindergarten's info)
    @FindBy(id = "kindergartenName")
    public WebElement privateKindergartensName;

    @FindBy(id = "kindergartenCode")
    public WebElement privateKindergartensCode;

    @FindBy(id = "kindergartenPhone")
    public WebElement privateKindergartensPhone;

    @FindBy(id = "kindergartenEmail")
    public WebElement privateKindergartensEmail;

    @FindBy(id = "kindergartenAddress")
    public WebElement privateKindergartensAddress;

    @FindBy(id = "kindergartenAccount")
    public WebElement privateKindergartensBankAcc;

    @FindBy(id = "kindergartenBankCode")
    public WebElement privateKindergartensBankCode;

    @FindBy(id = "kindergartenBankName")
    public WebElement privateKindergartensBankName;

    // input fields (child info)
    @FindBy(id = "txtChildPersonalCode")
    public WebElement compensationChildPersonalCode;

    @FindBy(id = "txtChildSurname")
    public WebElement compensationChildSurname;

    //elements
    @FindBy (xpath = "//h6[text()='Mano prašymai dėl kompensacijos'][@class='ps-2 pt-3']")
    public WebElement compensationsApplicationsListName;


    //input fields

    public void inputPrivateKindergartensName(String value) {
        privateKindergartensName.sendKeys(value);
    }

    public void inputPrivateKindergartensCode(String value) {
        privateKindergartensCode.sendKeys(value);
    }

    public void inputPrivateKindergartensPhone(String value) {
        privateKindergartensPhone.sendKeys(value);
    }

    public void inputPrivateKindergartensEmail(String value) {
        privateKindergartensEmail.sendKeys(value);
    }

    public void inputPrivateKindergartensAddress(String value) {
        privateKindergartensAddress.sendKeys(value);
    }

    public void inputPrivateKindergartensBankAcc(String value) {
        privateKindergartensBankAcc.sendKeys(value);
    }

    public void inputPrivateKindergartensBankCode(String value) {
        privateKindergartensBankCode.sendKeys(value);
    }

    public void inputPrivateKindergartensBankName(String value) {
        privateKindergartensBankName.sendKeys(value);
    }

    public void inputChildPersonalCode(String value) {
        compensationChildPersonalCode.sendKeys(value);
    }

    public void inputChildSurname(String value) {
        compensationChildSurname.sendKeys(value);
    }

    public String verifyCompensationsApplicationsListName() throws InterruptedException {
        Thread.sleep(160);
       return compensationsApplicationsListName.getText();
    }

    //click buttons

    public void clickNavButtonApplicationForCompensation() {
        buttonApplicationForCompensation.click();
    }

    public void clickButtonReviewCompensationApplication() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);
        Thread.sleep(200);
        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnReviewCompensationUser")));
        button.click();
    }

    public void clickButtonEditCompenstaionsApplication(){
        buttonEditCompensationsApplication.click();
    }

    public void clickButtonEditCompensationButton(){
        buttonEditCompensationButton.click();
    }

    public void clickButtonSubmitApplication() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", buttonSubmitCompensationsApplication);
        js.executeScript("arguments[0].click();", buttonSubmitCompensationsApplication);
    }

    public void clickNavButtonNewCompensApplication() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement navNewApplication = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("chooseCompensationFormButton")));
        navNewApplication.click();
    }

    public void compensatApplicFormKindergartensDetails() throws IOException {
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/parentAndChildDetails.txt");
        String privateKindergatensName = formData.get(10);
        String privateKindergartensCode = formData.get(11);
        String privateKindergartensPhone = formData.get(12);
        String privateKindergartensEmail = formData.get(13);
        String privateKindergartensAddress = formData.get(14);
        String privateKindergartensBankAcc = formData.get(15);
        String privateKindergartensBankCode = formData.get(16);
        String privateKindergartensBankName = formData.get(17);
        inputPrivateKindergartensName(privateKindergatensName);
        inputPrivateKindergartensCode(privateKindergartensCode);
        inputPrivateKindergartensPhone(privateKindergartensPhone);
        inputPrivateKindergartensEmail(privateKindergartensEmail);
        inputPrivateKindergartensAddress(privateKindergartensAddress);
        inputPrivateKindergartensBankAcc(privateKindergartensBankAcc);
        inputPrivateKindergartensBankCode(privateKindergartensBankCode);
        inputPrivateKindergartensBankName(privateKindergartensBankName);
    }

    public void compensatApplicFormChildDetails() throws IOException {
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/parentAndChildDetails.txt");
        String childSurname = formData.get(7);
        String childPersonalCode = formData.get(8);
        inputChildPersonalCode(childPersonalCode);
        inputChildSurname(childSurname);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("txtChildName"),"Sandijus"));
    }

    public void fillInTheCompensationApplication() throws IOException {
        clickNavButtonNewCompensApplication();
        compensatApplicFormKindergartensDetails();
        compensatApplicFormChildDetails();
        clickButtonSubmitApplication();

    }

    public void clickDeleteCompensationsApplication () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement delete = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("btnDeleteCompensation")));
        delete.click();
    }

    public Boolean compensationsApplicationSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.xpath("/html/body/div[2]/div/div[1]"), "Kompensacijos " +
                "prašymas sukurtas sėkmingai"));
    }

    public Boolean verifyIfCompensationsApplicationsListNameIsShowen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.xpath("//h6[text()='Mano prašymai dėl " +
                "kompensacijos'][@class='ps-2 pt-3']"), "Mano prašymai dėl kompensacijos"));
    }


    public Boolean verifyIfApplicationIsShowen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='root']/div/div/div/div/div[1]/div[1]/h6[1]"), "Prašymas skirti kompensaciją privačiam darželiui"));
    }
    public SubmitNewCompensationApplicationPage(WebDriver driver) {
        super(driver);
    }
}
