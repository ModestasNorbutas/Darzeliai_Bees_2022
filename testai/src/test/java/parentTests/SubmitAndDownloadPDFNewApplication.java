package parentTests;

import generalMethods.GeneralMethods;
import org.testng.annotations.Test;
import pages.LoginPage;
import parentPages.SubmitAndDownloadPDFNewApplicationPage;
import specialistPages.CreateAndDeleteNewKindergartenPage;

import java.io.IOException;

public class SubmitAndDownloadPDFNewApplication extends GeneralMethods {
    /**
     *
     * Test scenario:
     * Fill in and submit application to a kindergarten.
     * Fill in a new application with all priority points and confirm that the first child (who had more priority
     * points) got the place and second child is in the waiting list.
     * Delete the submitted application afterwards.
     *
     * Test steps:
     * 1. Login as kindergarten specialist, create a new kindergarten for this test.
     * 2. Kindergarten specialist checks if registration is open. If it's closed, user opens it for the test. Logout.
     * 3. Login as admin. New user (parent) is created for the test. Logout.
     * 4. Login as the newly created user.
     * 5. User fills in application. User information is stored in parentAndChildDetails.txt file
     * 6. Manager logs in. Manager closes registration, forms queue and sends info messages.
     * 7. Parent logs in to download agreement.
     * 8. Parent logs in to delete the application.
     * 9. The kindergarten and user that were used for this test are deleted.
     * @throws IOException
     */

    @Test(groups = "regression", priority = 1)
    public void successfullySubmitNewApplication() throws IOException, InterruptedException {

        //log in as a manager and create new kindergarten
        successfullyCreateNewKindergarten();
        doLogout();

        // wait for login page to load
        waitForLoginToLoad();
        LoginPage loginPage = new LoginPage(driver);

        //log in as an admin
        loginPage.enterUsername(adminLogins);
        loginPage.enterPassword(adminLogins);

        userNotLoggedInPopUp();

        // create a new user (parent) for this test
        createNewParent(2);
        doLogout();

        //log in as a manager to open registration if needed and log out
        doLogin(specialistLogins, specialistLogins);
        openRegistrationIfNeeded ();

        //log in as a new user (parent)
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        // fill in the application and submit it

        fillInTheApplication();
        applicationSuccessful();
        clickOkButton();
        doLogout();
    }

    @Test (groups = "regression", priority = 2)
    public void confirmQueue () throws InterruptedException {

        // wait for login page to load
        waitForLoginToLoad();

        //log in as a manager
        doLogin(specialistLogins, specialistLogins);
        SubmitAndDownloadPDFNewApplicationPage download = new SubmitAndDownloadPDFNewApplicationPage(driver);
        download.clickApplicationsForRegistration();
        download.verifyIfApplicationPageIsPresent();
        download.clickStopRegistration();
        download.clickFormQueue();
        download.clickSwalPopUp1();
        download.clickConfirmQueue();
        download.clickSwalPopUp2();
        download.clickSwalPopUp1();
        doLogout();
    }

    @Test (groups = "regression", priority = 3)
    public void printPDFAgreement () {

        // wait for login page to load
        waitForLoginToLoad();

        //log in as a parent
        doLogin(createNewUserParentEmail, createNewUserParentEmail);
        SubmitAndDownloadPDFNewApplicationPage download = new SubmitAndDownloadPDFNewApplicationPage(driver);
        download.verifyIfUserApplicationsArePresent();
        download.clickReviewContract();
        download.clickButtonDownload();
        doLogout();
    }

    @Test (groups = "regression", priority = 4)
    public void deleteApplication () throws InterruptedException {

        // wait for login page to load
        waitForLoginToLoad();

        //log in as a parent
        doLogin(createNewUserParentEmail, createNewUserParentEmail);
        SubmitAndDownloadPDFNewApplicationPage download = new SubmitAndDownloadPDFNewApplicationPage(driver);
        download.verifyIfUserIsLoggedIn();

        // delete application for kindergarten

        clickDeleteApplication();
        waitToAgreePopUp();
        clickOkButton();
        doLogout();

        // delete the kindergarten that was created for the test
        doLogin(specialistLogins, specialistLogins);
        CreateAndDeleteNewKindergartenPage createNewKindergarten = new CreateAndDeleteNewKindergartenPage(driver);
        createNewKindergarten.searchForTheNewlyCreatedKindergarten("123 Testinis");
        deleteNewKindergarten();
        doLogout();

        // delete test user
        doLoginAsAdmin();
        verifyIfAdminIsLoggedIn();
        deleteNewUser();
    }
}
