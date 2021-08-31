package org.bahmni.gauge.amman.specs;


import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.amman.clinical.ObservationsPage;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;

public class ObservationsSpec {
    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(DriverFactory.getDriver());
    }

    @Step("Select template <template> from observation page and fill details <table>")
    public void clickOnTreatmentEnrollment(String template, Table table){
        ObservationsPage observationsPage = PageFactorySpec.get(ObservationsPage.class);
        observationsPage.selectTemplate(template);
        waitForAppReady();
        observationsPage.fillTemplateData(table);
    }
}
