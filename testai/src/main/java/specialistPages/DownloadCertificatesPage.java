package specialistPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractObjectPage;

import java.time.Duration;

public class DownloadCertificatesPage extends AbstractObjectPage {

    @FindBy(id = "navManagerDocuments")
    WebElement navPrasymuPazymos;

    @FindBy(xpath = "//*/div/div/div/div/div/table/tbody/tr/td[6]/button")
    WebElement buttonDownload;

    @FindBy(id = "searchBox")
    WebElement searchBox;


    public void clickNavButtonPrasymuPazymos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("navManagerDocuments")));
        navPrasymuPazymos.click();
    }

    public void clickButtonDownloadCertificate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
         wait.until(ExpectedConditions.textToBe
                (By.xpath("//*[@id='root']/div/div/div/div/div/table/tbody/tr/td[4]/span"), "Jonaitis"));
        buttonDownload.click();
    }

    public Boolean assertThatSearchedCertificateIsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.textToBe
                (By.xpath("//*[@id=\"root\"]/div/div/div/div/div/table/tbody/tr/td[5]/span"), "Testas.pdf"));
    }

    public void clickSearchBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("searchBox")));
        searchBox.click();
    }

    public void enterSurnameToSearchBox(String value) {
        searchBox.sendKeys(value);
    }

    public DownloadCertificatesPage(WebDriver driver) {
        super(driver);
    }
}
