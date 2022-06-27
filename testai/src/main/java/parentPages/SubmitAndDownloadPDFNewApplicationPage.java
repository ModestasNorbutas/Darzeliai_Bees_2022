package parentPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractObjectPage;

import java.time.Duration;

public class SubmitAndDownloadPDFNewApplicationPage extends AbstractObjectPage {

    //buttons

    @FindBy(id = "btnStopRegistration")
    public WebElement stopRegistrationButton;

    @FindBy(id = "btnFormQueue")
    public WebElement formQueue;

    @FindBy(id = "btnConfirmQueue")
    public WebElement confirmQueue;

    @FindBy(id = "btnReviewContractUser")
    public WebElement reviewContract;

    @FindBy(id = "CompensationReviewDownloadPDF")
    public WebElement downloadPDF;

    @FindBy(id = "CompensationReviewReturn")
    public WebElement returnButton;

    @FindBy(id = "navManagerApplicationQueue")
    public WebElement applicationsForRegistrationButton;

    @FindBy(id = "btnLogout")
    public WebElement logoutButton;


    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void clickStopRegistration() {
        stopRegistrationButton.click();
    }


    public void clickApplicationsForRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement applicationsForRegistrationButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("navManagerApplicationQueue")));
        applicationsForRegistrationButton.click();
    }

    public void clickFormQueue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement formQueue = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("btnFormQueue")));
        formQueue.click();
    }

    public void clickOKPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popUpClickOK = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/div/button")));
        popUpClickOK.click();
    }


    public void clickSwalPopUp1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement popUpClickOK = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm']")));
        popUpClickOK.click();
    }

    public void clickSwalPopUp2() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement popUpClickOK = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm swal-button--danger']")));
        popUpClickOK.click();
    }

    public void clickReviewContract() {
        reviewContract.click();
    }

    public void clickConfirmQueue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmQueueOK = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnConfirmQueue")));
        confirmQueueOK.click();
    }

    public void clickButtonDownload() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2030)");
        js.executeScript("arguments[0].scrollIntoView()", downloadPDF);
        js.executeScript("arguments[0].click();", downloadPDF);
    }

    public void clickReturnButton() {
        returnButton.click();
    }

    public Boolean verifyIfApplicationPageIsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.xpath("//h6[text()='Prašymai registruotis į valstybinius " +
                "darželius'][@class='ps-2 pt-3']"), "Prašymai registruotis į valstybinius darželius"));
    }

    public Boolean verifyIfUserApplicationsArePresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.xpath("//h6[text()='Mano prašymai į valstybinius " +
                "darželius']"), "Mano prašymai į valstybinius darželius"));
    }

    public Boolean verifyIfUserIsLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe(By.id("navUserMyApplications"), "Mano prašymai"));
    }

    //constructor
    public SubmitAndDownloadPDFNewApplicationPage(WebDriver driver) {
        super(driver);
    }
}
