package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;

import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.clinical.VisitPage;
import org.bahmni.gauge.common.registration.RegistrationVisitDetailsPage;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.StringUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationVisitPageSpec {
    private final WebDriver driver;

    public RegistrationVisitPageSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(driver);
    }

    @Step("Close visit")
    public void closeVisit() {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.closeVisit(driver);
    }

    @Step("Try close visit")
    public void tryCloseVisit() {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.tryCloseVisit();
    }

    @Step("Navigate to latest visit page")
    public void navigateToVisit() {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.openLastVisit();
        waitForAppReady();
    }

    @Step("Verify display control <displayControl> on visit page, has the following details <table>")
    public void verifyDisplayControlOnVisitPage(String displayControl, Table table) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        String displayControlText = registrationVisitPage.getDisplayControlText(displayControl);
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue(StringUtil.stringDoesNotExist(drugOrder, displayControlText), displayControlText.contains(drugOrder));
        }
    }

    @Step("Verify display control with Caption <displayControlCaption> on visit page, has the following details <table>")
    public void verifyDisplayControlOnVisitPageWithCaption(String displayControlCaption, Table table) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        String displayControlText = registrationVisitPage.getDisplayControlTextWithCaption(displayControlCaption);
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue("String " + drugOrder + " does not exist. Actual String :" + displayControlText, displayControlText.contains(drugOrder));
        }
    }

    @Step("Verify display control with Caption <displayControlCaption> on visit page, with labels and details <table>")
    public void verifyDisplayControlOnVisitPageWithCaptionAndLable(String displayControlCaption, Table table) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        String displayControlText = registrationVisitPage.getDisplayControlTextWithCaption(displayControlCaption);
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue("String " + drugOrder + " does not exist. Actual String :" + displayControlText, displayControlText.contains(drugOrder));
        }
    }

    @Step("Verify details on visit page <Disposition> display control")
    public void selectDisplayControl(String name) {
        VisitPage visitPage = PageFactorySpec.get(VisitPage.class);
        visitPage.selectDisplayControl(name);
    }

    @Step("Verify Error popup with message <message> is displayed")
    public void verifyErrorOnPageWithMessage(String message) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.waitForElementOnPage(By.cssSelector(".error-message-container"));
        Assert.assertEquals("Error popup message dont match", message, registrationVisitPage.findElement(By.cssSelector("#view-content .msg")).getText());
    }

    @Step("Open visit of type <visitType> in <visitLocation> location for previous patient using api")
    public void openVisitThroughApi(String visitType, String visitLocation) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.getPatientFromSpecStore().setLocation(System.getenv(visitLocation));
        registrationVisitPage.getPatientFromSpecStore().setVisitType(visitType);
        BahmniRestClient.get().create(registrationVisitPage.getPatientFromSpecStore(), "visit");
    }


    @Step("Enter visit details through API <table>")
    public void enterVisitDetailsThroughAPI(Table table) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.getPatientFromSpecStore().setHeight(table.getColumnValues("height").get(0));
        registrationVisitPage.getPatientFromSpecStore().setWeight(table.getColumnValues("weight").get(0));
        registrationVisitPage.getPatientFromSpecStore().setRegistrationFee(table.getColumnValues("registrationFee").get(0));
        BahmniRestClient.get().postBahmniEncounter(registrationVisitPage.getPatientFromSpecStore(), "visit_details_create.ftl");

    }


    @Step("Open <tabCaption> tab on visit page")
    public void openTab(String tabCaption) {
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.getRegistrationVisitPage();
        registrationVisitPage.openTab(tabCaption);
    }

}
