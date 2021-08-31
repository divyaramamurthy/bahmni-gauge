package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import java.text.SimpleDateFormat;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.clinical.ConsultationPage;

import java.util.Date;

public class ConsultationPageSpec {

    private ConsultationPage consultationPage;

    public ConsultationPageSpec() {
        consultationPage = PageFactorySpec.getConsultationPage();

    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(DriverFactory.getDriver());
    }

    @Step("Save the consultation")
    public void saveConsultation() {
        consultationPage.saveConsultation();
        waitForAppReady();
    }

    @Step("Navigate to patient dashboard")
    public void clickOnDashboard() {
        consultationPage.clickPatientProfile();
        waitForAppReady();
    }

    @Step("Go to <tab> tab")
    public void goToTab(String tab) {
        consultationPage.clickOnTab(tab);
        waitForAppReady();
    }

    @Step("Set retrospective date to <date>")
    public void setRetrospectiveDate(String date) {
        if (date.equalsIgnoreCase("current date")) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date currentDate = new Date();
            String formattedDate = formatter.format(currentDate.getTime());
            consultationPage.openRegistrationDeskAndSet("", "", formattedDate);
        } else {
            consultationPage.openRegistrationDeskAndSet("", "", date);
        }
    }

    @Step("Verify only the following tab is available <table>")
    public void verifyTabPresent(Table tabs)
    {
        consultationPage.verifyTabsPresent(tabs);
    }
}
