package parentPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AbstractObjectPage;

import java.time.Duration;

public class SubmitNewApplicationPage extends AbstractObjectPage {

    // input fields (additional parent info)
    @FindBy(id = "txtAdditionalName")
    public WebElement secondParentName;

    @FindBy(id = "txtAdditionalSurname")
    public WebElement secondParentSurname;

    @FindBy(id = "txtAdditionalPersonalCode")
    public WebElement secondParentPersonalCode;

    @FindBy(id = "txtAdditionalPhone")
    public WebElement secondParentPhoneNumber;

    @FindBy(id = "txtAdditionalEmail")
    public WebElement secondParentEmail;

    @FindBy(id = "txtAdditionalAddress")
    public WebElement secondParentAddress;

    // input fields (child info)
    @FindBy(id = "txtChildName")
    public WebElement childName;

    @FindBy(id = "txtChildSurname")
    public WebElement childSurname;

    @FindBy(id = "txtChildPersonalCode")
    public WebElement childPersonalCode;

    @FindBy(xpath = "//*//div[5]/div[1]/div/input")
    public WebElement childDateOfBirth;

    // checkbox (child priorities)
    @FindBy(id = "chkLivesInVilnius")
    public WebElement priorityOne;

    @FindBy(id = "chkChildIsAdopted")
    public WebElement priorityTwo;

    @FindBy(id = "chkFamilyHasThreeOrMoreChildrenInSchools")
    public WebElement priorityThree;

    @FindBy(id = "chkGuardianInSchool")
    public WebElement priorityFour;

    @FindBy(id = "chkGuardianDisability")
    public WebElement priorityFive;

    @FindBy(id = "chkLivesMoreThanTwoYears")
    public WebElement prioritySix;

    // buttons
    @FindBy(id = "btnEnableAdditionalGuardian")
    public WebElement addAdditionalGuardianButton;

    @FindBy(id = "submitApplicationButton")
    public WebElement buttonSubmitApplication;

    // dropdown
    @FindBy(xpath = "//*[@id='selKindergartenId1']/input")
    public WebElement dropdownElement;

    // choose kindergarten priorities
    @FindBy(id = "selKindergartenId1")
    public WebElement kindergartenPriorityOne;


    public void clickAddAdditionalGuardianButton() {
        addAdditionalGuardianButton.click();
    }

    public void waitToClickStopRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement stopRegistation = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("btnStopRegistration")));
        stopRegistation.click();
    }

    public void waitToFormQueue() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement formQueue = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("btnFormQueue")));
        formQueue.click();
    }

    public void waitToConfirmQueue() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement confirmQueue = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("btnConfirmQueue")));
        confirmQueue.click();
    }

    public void inputSecondParentName(String value) {
        secondParentName.sendKeys(value);
    }

    public void inputSecondParentSurname(String value) {
        secondParentSurname.sendKeys(value);
    }

    public void inputSecondParentPersonalCode(String value) {
        secondParentPersonalCode.sendKeys(value);
    }

    public void inputSecondParentPhoneNumber(String value) {
        secondParentPhoneNumber.sendKeys(value);
    }

    public void inputSecondParentEmail(String value) {
        secondParentEmail.sendKeys(value);
    }

    public void inputSecondParentAddress(String value) {
        secondParentAddress.sendKeys(value);
    }

    public void inputChildName(String value) {
        childName.sendKeys(value);
    }

    public void inputChildSurname(String value) {
        childSurname.sendKeys(value);
    }

    public void inputChildPersonalCode(String value) {
        childPersonalCode.sendKeys(value);
    }

    public void inputChildDateOfBirth(String value) {
        childDateOfBirth.click();
        // delete default date value manually
        for (int i = 0; i < 10; i++) {
            childDateOfBirth.sendKeys(Keys.BACK_SPACE);
        }
        // input date of birth
        childDateOfBirth.sendKeys(value);
        childDateOfBirth.sendKeys(Keys.ENTER);
    }

    public void clickPriorityOne() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", priorityOne);
        js.executeScript("arguments[0].click();", priorityOne);
    }

    public void clickPriorityTwo() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", priorityTwo);
        js.executeScript("arguments[0].click();", priorityTwo);
    }

    public void clickPriorityThree() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", priorityThree);
        js.executeScript("arguments[0].click();", priorityThree);

    }

    public void clickPriorityFour() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", priorityFour);
        js.executeScript("arguments[0].click();", priorityFour);

    }

    public void clickPriorityFive() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", priorityFive);
        js.executeScript("arguments[0].click();", priorityFive);

    }

    public void clickPrioritySix() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        js.executeScript("arguments[0].scrollIntoView()", prioritySix);
        js.executeScript("arguments[0].click();", prioritySix);
    }


    public void openKindergartenListDropdownPriorityOne() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);
        Thread.sleep(160);
        kindergartenPriorityOne.click();
        WebElement drpDnPrioOne = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("react-select-2-option-0")));
        drpDnPrioOne.click();
    }


    public void clickButtonSubmitApplication() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        js.executeScript("arguments[0].scrollIntoView()", buttonSubmitApplication);
        js.executeScript("arguments[0].click();", buttonSubmitApplication);
    }

    // constructor
    public SubmitNewApplicationPage(WebDriver driver) {
        super(driver);
    }

}
