package parentTests;

import generalMethods.GeneralMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.LoginPage;
import parentPages.SubmitNewCompensationApplicationPage;
import specialistPages.CreateAndDeleteNewKindergartenPage;

import java.io.IOException;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SubmitNewCompensationsApplication extends GeneralMethods {

    /**
     * Test Scenario:
     * Fill in and submit application for a compensation.
     * Review and edit application for a compensation.
     * Delete the submitted application afterwards.
     *
     * Test steps:
     * 1. Login as admin. New user (parent) is created for the test. Logout.
     * 2. Login as the newly created user.
     * 3. User fills in application. All information needed is stored in parentAndChildDetails.txt file
     * 4. Parent logs in again to review and edit the application.
     * 5. Parent logs in again to delete the application.
     * 6. The user that was used for this test is deleted.
     */

    @Test(groups = "regression", priority = 1)
    public void successfullySubmitNewCompensationsApplication() throws IOException {

        SubmitNewCompensationApplicationPage compensationApplication = new SubmitNewCompensationApplicationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // wait for the login page to load
        waitForLoginToLoad();

        //login with admin details
        loginPage.enterUsername(adminLogins);
        loginPage.enterPassword(adminLogins);

        loginPage.clickLoginButton();

        // create a new user (parent) for this test
        createNewParent(2);
        doLogout();
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        // fill in the compensation's application and submit it
        clickNavButtonNewApplication();
        compensationApplication.fillInTheCompensationApplication();
        compensationApplication.compensationsApplicationSuccessful();
        clickOkButton();
        doLogout();
    }

    @Test(groups = "regression", priority = 2)
    public void reviewAndEditCompensationsApplication() throws InterruptedException {
        SubmitNewCompensationApplicationPage compensationApplication = new SubmitNewCompensationApplicationPage(driver);

        // parent logs in
        waitForLoginToLoad();
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        compensationApplication.verifyIfCompensationsApplicationsListNameIsShowen();

        //compensations application reviewed
        compensationApplication.clickButtonReviewCompensationApplication();
        compensationApplication.verifyIfApplicationIsShowen();

        doLogout();
    }


    @Test(groups = "regression", priority = 3)
    public void deleteCompensationsApplication() {
       //login as parent user
        SubmitNewCompensationApplicationPage compensationApplication = new SubmitNewCompensationApplicationPage(driver);
        waitForLoginToLoad();
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        compensationApplication.clickDeleteCompensationsApplication();
        waitToAgreePopUp();
        waitAndClickOkButton();
        doLogout();

        //login as admin user
        doLoginAsAdmin();
        verifyIfAdminIsLoggedIn();
        deleteNewUser();


    }
}
