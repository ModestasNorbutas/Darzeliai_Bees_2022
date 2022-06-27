package parentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractObjectPage;

public class ReviewCompensationApplicationParentPage  extends AbstractObjectPage {

    @FindBy(id = "btnReviewCompensationUser")
    WebElement buttonReviewCompensationApplication;

    @FindBy(id = "CompensationReviewReturn")
    WebElement buttonCompensationReviewReturn;

    public void clickButtonCompensationReviewReturn() {
        buttonCompensationReviewReturn.click();
    }

    public ReviewCompensationApplicationParentPage(WebDriver driver) {
        super(driver);
    }
}
