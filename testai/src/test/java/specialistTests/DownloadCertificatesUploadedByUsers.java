package specialistTests;
import generalMethods.GeneralMethods;
import org.testng.annotations.Test;
import parentPages.UploadMedicalDocumentPDFPage;
import specialistPages.DownloadCertificatesPage;
import java.time.Duration;


public class DownloadCertificatesUploadedByUsers extends GeneralMethods {

    /**
     * Test Scenario:
     * Login as user (parent) and upload a certificate.
     * Login as manager (kindergarten specialist) and download the certificate uploaded by the user.
     * Delete the uploaded certificate afterwards.
     *
     * Test steps:
     * 1. Login as admin.
     * 2. Create a new user (parent) for the test. Logout.
     * 3. Login as the newly created user.
     * 4. Go to "Mano pazymos" page and upload a certificate (in .pdf format). Log out.
     * 5. Login as kindergarten specialist.
     * 6. Go to "Prasymu pazymos" page and download a certificate. Logout.
     * 7. Login as the test user.
     * 8. Delete the certificate. Logout.
     * 9. Login as admin.
     * 10. Delete the test user. Logout.
     */

    @Test(groups = "regression", priority = 1)
    public void successfullyUploadPDF() {
        // create test user (parent)
        doLoginAsAdmin();
        createNewParent(2);
        doLogout();
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        // go to "Mano pazymos" page
        clickNavButtonMyDocumentsParent();

        // assert page
        assertThatMyDocumentsPageLoaded();

        // upload document
        uploadPDF();

        // logout
        doLogout();
    }

    @Test(groups = "regression", priority = 2)
    public void downloadPDFUploaded() {
        // login as kindergarten specialist
        doLogin(specialistLogins, specialistLogins);

        // go to "Prasymu pazymos" page
        DownloadCertificatesPage certificatesPage = new DownloadCertificatesPage(driver);
        certificatesPage.clickNavButtonPrasymuPazymos();

        // enter surname to search box
        certificatesPage.clickSearchBox();
        certificatesPage.enterSurnameToSearchBox("Jonaitis");

        // download certificate
        certificatesPage.clickButtonDownloadCertificate();

        // logout
        doLogout();
    }

    @Test(groups = "regression", priority = 3)
    public void deletePDFUploaded() {
        // login as newly created user
        doLogin(createNewUserParentEmail, createNewUserParentEmail);

        // go to "Mano pazymos" page
        clickNavButtonMyDocumentsParent();

        // assert page
        assertThatMyDocumentsPageLoaded();

        // delete certificate uploaded
        deletePDF();

        // logout
        doLogout();

        // delete the user created for this test
        doLoginAsAdmin();
        deleteNewUser();
    }
}
