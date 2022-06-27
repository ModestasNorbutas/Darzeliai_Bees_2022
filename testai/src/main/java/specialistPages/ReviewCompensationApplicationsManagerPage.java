package specialistPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractObjectPage;

import java.time.Duration;

public class ReviewCompensationApplicationsManagerPage extends AbstractObjectPage {


    @FindBy(partialLinkText = "Kompensacij")
    WebElement navKompensacijuPrasymai;

    @FindBy(id = "searchBox")
    WebElement searchPersonalIdField;

    //buttons
    @FindBy(id = "btnReviewCompensationManager")
    WebElement buttonReviewCompensationApplication;

    @FindBy(id = "CompensationReviewReturn")
    WebElement buttonCompensationReviewReturn;

    @FindBy(id = "btnDeactivateCompensationManager")
    WebElement buttonRejectCompensation;

    @FindBy(id = "btnConfirmCompensationManager")
    WebElement buttonConfirmCompensation;

    // buttons in pop-up messages
    @FindBy(xpath = "//html/body/div[2]/div/div[2]/div[2]/button")
    WebElement buttonOkRejectCompensation;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[2]/button")
    WebElement buttonOkConfirmCompensation;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div/button")
    WebElement buttonOkStatusChanged;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[2]/button")
    WebElement buttonOkDeleteApplication;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div/button")
    WebElement buttonOkDeletedSuccessfully;


    public void clickNavButtonKompensacijuPrasymai() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("navManagerCompensationQueue")));
        navKompensacijuPrasymai.click();
    }

    public Boolean assertThatKompensacijuPrasymaiPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBe
                (By.xpath("//*[@id=\"root\"]/div/div/div/div/h6"), "Prašymai gauti kompensaciją"));
    }


    public void clickButtonReviewCompensationApplication() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBe
                (By.xpath("//*/div/div/div/div/div/table/tbody/tr/td[5]/span"), "Sandijus"));
        buttonReviewCompensationApplication.click();
    }

    public void clickButtonCompensationReviewReturn() {
        buttonCompensationReviewReturn.click();
    }

    public void clickButtonRejectCompensation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe
                (By.xpath("//*/div/div/div/div/div/table/tbody/tr/td[5]/span"), "Sandijus"));
        buttonRejectCompensation.click();
    }

    public void clickPersonalIdSearchField() {
        searchPersonalIdField.click();
    }

    public void enterPersonalIdForSearch() {
        searchPersonalIdField.clear();
        searchPersonalIdField.sendKeys("51609260014");
    }

    public void clickButtonOkRejectCompensation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement swalButtonConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("swal-button--confirm")));
        swalButtonConfirm.click();
    }

    public void clickButtonOkConfirmCompensation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[1]")));
        buttonOkConfirmCompensation.click();
    }

    public void clickButtonConfirmCompensation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe
                (By.xpath("//*/div/div/div/div/div/table/tbody/tr/td[5]/span"), "Sandijus"));
        buttonConfirmCompensation.click();
    }

    public void clickButtonOkStatusChanged() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement swalButtonConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("swal-button--confirm")));
                swalButtonConfirm.click();
    }

    public void clickButtonOkDeleteApplication() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[1]")));
        buttonOkDeleteApplication.click();
    }

    public void clickButtonOkDeletedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement swalButtonConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("swal-button--confirm")));
        swalButtonConfirm.click();
    }


    public ReviewCompensationApplicationsManagerPage(WebDriver driver) {
        super(driver);
    }
}
