package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.*;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.clinical.PatientListingPage;
import org.bahmni.gauge.common.home.HomePage;
import org.bahmni.gauge.data.StoreHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class HomeSpec {
    private final WebDriver driver;
    private HomePage homePage;

    public HomeSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(driver);
    }


    @Step("Click on registration app")
    public void goToRegistrationPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickRegistrationApp();
        BahmniPage.waitForSpinner(driver);
    }

    @Step("Click on programs app")
    public void goToProgramsPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickProgramsApp();
    }

    @Step("Click on clinical app")
    public void goToClinicalPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickClinicalApp();
    }

    @Step("Click on implementer interface app")
    public void goToImplementerInterfacePage() {
        homePage = PageFactory.getHomePage();
        homePage.clickImplementerInterfaceApp();
    }

    @ContinueOnFailure
    @Step("Verify page load time for <app> app is less than <time> seconds")
    public void verifyPageLoadForClinical(String app, long loadTime) {
        homePage = PageFactory.getHomePage();
        Date beforeTime = new Date();
        switch (app) {
            case "Clinical"    :homePage.clickClinicalApp();
                                break;
            case "Programs"    :homePage.clickProgramsApp();
                                break;
            case "ADT"         :homePage.clickInpatientApp();
                                break;
            case "Registration":homePage.clickRegistrationApp();
                                break;
                        default:break;
        }
        BahmniPage.waitForSpinner(driver);
        Date afterTime = new Date();
        long difference = (afterTime.getTime()-beforeTime.getTime())/1000;
        Assert.assertTrue("Actual Time to load " + app + " app is " + Long.toString(difference) +" seconds", difference <= loadTime);
    }

    @Step("Click on inpatient app")
    public void goToInpatientPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickInpatientApp();
        waitForAppReady();
        PatientListingPage patientListingPage= PageFactory.get(PatientListingPage.class);
        if(StoreHelper.getAppGlobal().getBedCount()==0) {
            StoreHelper.getAppGlobal().setBedCount(patientListingPage.getAvailableBedCount());
        }
    }

    @Step("Click on admin app")
    public void goToAdminPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickAdminApp();
    }

    @Step("Click on orders app")
    public void goToOrdersPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickOrdersApp();
    }

    @Step("Click on <apps> app")
    public void goToApp(String app) {
        homePage = PageFactory.getHomePage();
        switch (app.toLowerCase()) {
            case "registration":
                homePage.clickRegistrationApp();
                break;

            case "programs":
                homePage.clickProgramsApp();
                break;

            case "clinical":
                homePage.clickClinicalApp();
                break;

            case "inpatient":
                homePage.clickInpatientApp();
                break;

            case "radiology upload":
                homePage.clickRadiologyUploadApp();
                break;

            case "patient documents":
                Gauge.writeMessage(app + " not implemented");
                break;

            case "admin":
                homePage.clickAdminApp();
                break;

            case "reports":
                Gauge.writeMessage(app + " not implemented");
                break;

            case "orders":
                homePage.clickOrdersApp();
                break;

            case "implementor interface":
                Gauge.writeMessage(app + " not implemented");
                break;

            default:
                Gauge.writeMessage(app + " not implemented");
                break;
        }
    }

    @Step("Click on radiology upload app")
    public void goToRadiologyUploadPage() {
        homePage = PageFactory.getHomePage();
        homePage.clickRadiologyUploadApp();
    }

    @Step("Logout the user")
    public void logout() {
        new BahmniPage().waitForElementOnPage(".i.icon-bahmni-org.bahmi.gauge.possible.registration");
        homePage = PageFactory.getHomePage();
        homePage.logout();
    }

    @Step("Verify Login Page")
    public void validateLogin() {
        homePage = PageFactory.getHomePage();
        Assert.assertTrue(homePage.registration.isDisplayed());
        Assert.assertTrue(homePage.admin.isDisplayed());
        Assert.assertTrue(homePage.exports.isDisplayed());
    }


    @Step("Verify only the following application is available <table>")
    public void verifyAppPresent(Table application){
        homePage = PageFactory.getHomePage();
        homePage.verifyAppPresent(application);

    }
}
