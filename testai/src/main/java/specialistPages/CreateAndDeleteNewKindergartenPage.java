package specialistPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AbstractObjectPage;

import java.time.Duration;
import java.util.function.Function;

public class CreateAndDeleteNewKindergartenPage extends AbstractObjectPage {

    //input fields
    @FindBy(id = "id")
    public WebElement kindergartenID;

    @FindBy(id = "name")
    public WebElement kindergartenName;

    @FindBy(id = "address")
    public WebElement kindergartenAddress;

    @FindBy(id = "managerName")
    public WebElement kindergartenManagerName;

    @FindBy(id = "managerSurname")
    public WebElement kindergartenManagerSurname;

    @FindBy(id = "capacityAgeGroup2to3")
    public WebElement capacityAgeGroup2to3;

    @FindBy(id = "capacityAgeGroup3to6")
    public WebElement capacityAgeGroup3to6;

    // update kindergarten input fields
    @FindBy(id = "txtKindergartenName")
    public WebElement newKindergartenName;

    @FindBy(id = "nmbCapacity3to6")
    public WebElement numberCapacity3to6;

    // search input field
    @FindBy(xpath = "//*/div/div[2]/input")
    public WebElement searchInput;

    //buttons
    @FindBy(id = "btnSaveKindergarten")
    public WebElement buttonSaveKindergarten;

    @FindBy(id = "btnUpdateKindergarten")
    public WebElement buttonUpdateKindergarten;

    @FindBy(id = "btnSaveUpdatedKindergarten")
    public WebElement saveUpdatedKindergarten;

    @FindBy(id = "btnDeleteKindergarten")
    public WebElement buttonDeleteKindergarten;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[2]/button")
    public WebElement buttonAgreeToDeleteKindergarten;

    @FindBy(id = "findCoordinatesBtn")
    public WebElement buttonSearchCoordinates;

    public void inputKindergartenID(String value) {
        kindergartenID.sendKeys(value);
    }

    public void inputkindergartenName(String value) {
        kindergartenName.sendKeys(value);
    }

    public void inputkindergartenAddress(String value) {
        kindergartenAddress.sendKeys(value);
    }

    public void inputKindergartenManagerName(String value) {
        kindergartenManagerName.sendKeys(value);
    }

    public void inputKindergartenmanagerSurname(String value) {
        kindergartenManagerSurname.sendKeys(value);
    }

    public void inputcapacityAgeGroup2to3(String value) {
        capacityAgeGroup2to3.sendKeys(value);
    }

    public void inputcapacityAgeGroup3to6(String value) {
        capacityAgeGroup3to6.sendKeys(value);
    }

    public void clickButtonSaveKindergarten() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", buttonSaveKindergarten);
    }

    public void clickButtonSearchCoordinates() {
        buttonSearchCoordinates.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("latitude"), "54.666212349999995"));
    }

    public void clickOKPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement popUpClickOK = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/div/button")));
        popUpClickOK.click();
    }

    public void searchForTheNewlyCreatedKindergarten(String value) {
        searchInput.sendKeys(value);
    }

    public Boolean newKindergartenSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.textToBe(By.xpath("//*//td[1]"), "123 Testinis"));
    }

    public void clickButtonUpdateKindergarten() {
        buttonUpdateKindergarten.click();
    }

    public void updateNewKindergartenName(String value) {
        newKindergartenName.clear();
        newKindergartenName.sendKeys(value);
    }

    public void updateKindergartenNumberCapacity3to6(String value) {
        numberCapacity3to6.clear();
        numberCapacity3to6.sendKeys(value);
    }

    public void clickSaveUpdatedKindergarten() {
        saveUpdatedKindergarten.click();
    }

    public void clickButtonDeleteKindergarten() {
        buttonDeleteKindergarten.click();
    }

    public void clickButtonAgreeToDeleteKindergarten() {
        buttonAgreeToDeleteKindergarten.click();
    }

    public Boolean assertKindergartenWasDeletedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.xpath("//body/div[2]/div/div[1]"), "Darželis ištrintas sėkmingai"));
    }

    // constructor
    public CreateAndDeleteNewKindergartenPage(WebDriver driver) {
        super(driver);
    }

}