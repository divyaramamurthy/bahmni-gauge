package org.bahmni.gauge.possible.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.clinical.ObservationsPage;
import org.bahmni.gauge.possible.clinical.domain.ObservationForm;
import org.openqa.selenium.WebElement;

public class PossibleObservationSpec {
    private final ObservationsPage page;

    public PossibleObservationSpec() {
        this.page = PageFactory.get(ObservationsPage.class);
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        page.waitForSpinner();
    }

    @Step("In <templateName> form, fill up the following details <table>")
    public void enterDataInPatientVitalsForm(String templateId, Table table) {
        WebElement template = page.expandObservationTemplate(templateId);
        ObservationForm observationForm = new ObservationForm(template);
        observationForm.fillUp(table);
    }

}
