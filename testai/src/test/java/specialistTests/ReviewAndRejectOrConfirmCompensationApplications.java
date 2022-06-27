package specialistTests;
import generalMethods.GeneralMethods;
import org.testng.annotations.Test;
import pages.LoginPage;
import parentPages.ReviewCompensationApplicationParentPage;
import parentPages.SubmitNewCompensationApplicationPage;
import specialistPages.ReviewCompensationApplicationsManagerPage;
import java.io.IOException;


public class ReviewAndRejectOrConfirmCompensationApplications extends GeneralMethods {

    /**
     * Test scenario:
     * Parent submits a new compensation application
     * Manager reviews the compensation application and rejects it
     * Parent reviews previously submitted application, deletes it and submits new one
     * Manager reviews the compensation application and confirms it
     * Parent reviews the confirmed application and deletes it
     *
     * Preconditions:
     * admin@admin.lt is already created. New user user123@parent.lt is created during the test
     *
     * Test steps:
     * 1. Login as admin.
     * 2. Create new parent.
     * 3. Logout.
     * 4. Login as the newly created parent.
     * 5. Fill in all required fields and submit new compensation application.
     * 6. Logout.
     * 7. Login as kindergarten specialist.
     * 8. In the search field enter test users' child personal code.
     * 9. Review the compensation application submitted by the test user.
     * 10. Go back to the applications list.
     * 11. In the search field enter test users' child personal code again.
     * 12. Reject the compensation application.
     * 13. Logout.
     * 14. Login as the test parent again.
     * 15. Review rejected compensation.
     * 16. Delete rejected compensation.
     * 17. Fill in all required fields and submit new compensation application.
     * 18. Logout.
     * 19. Login as kindergarten specialist.
     * 20. In the search field enter test users' child personal code.
     * 21. Review the compensation application submitted by the test user.
     * 22. Go back to the applications list.
     * 23. In the search field enter test users' child personal code.
     * 24. Confirm the compensation application.
     * 25. Logout.
     * 26. Login as the test parent again.
     * 27. Review confirmed compensation.
     * 28. Delete confirmed compensation.
     * 29. Logout
     * 30. Login as admin
     * 31. Delete the test user
     */


    @Test(groups = "regression", priority = 1)
    public void successfullySubmitNewCompensationsApplication() throws IOException {

        SubmitNewCompensationApplicationPage compensationApplication = new SubmitNewCompensationApplicationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // wait for the login page to load
        waitForLoginToLoad();

        // login with admin details
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
    public void reviewAndRejectCompensationApplication() throws InterruptedException {

        // login as kindergarten specialist
        doLogin(specialistLogins, specialistLogins);

        // go to "Kompensaciju prasymai" page
        ReviewCompensationApplicationsManagerPage compensationApplications = new ReviewCompensationApplicationsManagerPage(driver);
        compensationApplications.clickNavButtonKompensacijuPrasymai();

        // assert page
        compensationApplications.assertThatKompensacijuPrasymaiPageLoaded();

        // find application compensation submitted by test user
        compensationApplications.clickPersonalIdSearchField();
        compensationApplications.enterPersonalIdForSearch();

        // assert page
        compensationApplications.assertThatKompensacijuPrasymaiPageLoaded();

        // review compensation application
        compensationApplications.clickButtonReviewCompensationApplication();

        // return to compensation application list
        compensationApplications.clickButtonCompensationReviewReturn();

        // assert page
        compensationApplications.assertThatKompensacijuPrasymaiPageLoaded();

        // reject compensation application
        compensationApplications.clickPersonalIdSearchField();
        compensationApplications.enterPersonalIdForSearch();
        compensationApplications.clickButtonRejectCompensation();
        compensationApplications.clickButtonOkRejectCompensation();
        compensationApplications.clickButtonOkStatusChanged();

        // logout
        doLogout();
    }

    @Test(groups = "regression", priority = 3)
    public void reviewDeleteAndCreateNewCompensationApplication() throws InterruptedException, IOException {

        SubmitNewCompensationApplicationPage submitCompensationApplications = new SubmitNewCompensationApplicationPage(driver);
        ReviewCompensationApplicationParentPage parentCompensationApplications = new ReviewCompensationApplicationParentPage(driver);

        // wait fo login page to load
        waitForLoginToLoad();

        // login as user (parent)
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        // review compensation application
        submitCompensationApplications.clickButtonReviewCompensationApplication();
        parentCompensationApplications.clickButtonCompensationReviewReturn();

        // delete compensation application
        submitCompensationApplications.clickDeleteCompensationsApplication();
        waitToAgreePopUp();
        waitAndClickOkButton();

        // fill in the compensation's application and submit it
        clickNavButtonNewApplication();
        submitCompensationApplications.fillInTheCompensationApplication();
        submitCompensationApplications.compensationsApplicationSuccessful();
        clickOkButton();
        doLogout();
    }

    @Test(groups = "regression", priority = 4)
    public void reviewAndConfirmApplication () {

        ReviewCompensationApplicationsManagerPage compensationApplications = new ReviewCompensationApplicationsManagerPage(driver);

        // login as kindergarten specialist
        doLogin(specialistLogins, specialistLogins);

        // go to "Kompensaciju prasymai" page
        compensationApplications.clickNavButtonKompensacijuPrasymai();

        // assert page
        compensationApplications.assertThatKompensacijuPrasymaiPageLoaded();

        // find application compensation submitted by test user
        compensationApplications.clickPersonalIdSearchField();
        compensationApplications.enterPersonalIdForSearch();

        // review compensation application
        compensationApplications.clickButtonReviewCompensationApplication();

        // return to compensation application list
        compensationApplications.clickButtonCompensationReviewReturn();

        // assert page
        compensationApplications.assertThatKompensacijuPrasymaiPageLoaded();

        // confirm compensation application
        compensationApplications.clickPersonalIdSearchField();
        compensationApplications.enterPersonalIdForSearch();
        //compensationApplications.assertThatKompensacijuPrasymaiPageLoaded();
        compensationApplications.clickButtonConfirmCompensation();
        compensationApplications.clickButtonOkConfirmCompensation();
        compensationApplications.clickButtonOkStatusChanged();

        // logout
        doLogout();
    }

    @Test(groups = "regression", priority = 5)
    public void deleteCompensationApplicationAndTestUser () throws IOException, InterruptedException {

        waitForLoginToLoad();

        // login as user (parent)
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        // review compensation application
        SubmitNewCompensationApplicationPage submitCompensationApplications = new SubmitNewCompensationApplicationPage(driver);
        ReviewCompensationApplicationsManagerPage managerApplications = new ReviewCompensationApplicationsManagerPage(driver);
        ReviewCompensationApplicationParentPage parentApplications = new ReviewCompensationApplicationParentPage(driver);
        submitCompensationApplications.clickButtonReviewCompensationApplication();
        managerApplications.clickButtonCompensationReviewReturn();

        // delete compensation application
        submitCompensationApplications.clickDeleteCompensationsApplication();
        managerApplications.clickButtonOkDeleteApplication();
        managerApplications.clickButtonOkDeletedSuccessfully();
        doLogout();

        // delete test user
        doLoginAsAdmin();
        verifyIfAdminIsLoggedIn();
        deleteNewUser();
    }

}
