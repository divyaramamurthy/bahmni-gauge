package org.bahmni.gauge.amman.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.amman.SpecStoreHelper;
import org.bahmni.gauge.amman.registration.AmmanRegistrationPage;
import org.bahmni.gauge.amman.registration.domain.AmmanPatient;
import org.bahmni.gauge.amman.registration.domain.Fields;
import org.bahmni.gauge.amman.registration.domain.PatientAttribute;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.registration.RegistrationVisitDetailsPage;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSpec {
    private AmmanPatient ammanPatient = new AmmanPatient();
    private AmmanRegistrationPage registrationPage = (AmmanRegistrationPage) PageFactorySpec.get(AmmanRegistrationPage.class);

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(DriverFactory.getDriver());
    }

    @Step({"Enter Patient Details <table>", "Enter Legal Rep Details <table>", "Enter Caretaker Details <table>", "Enter ID Document Details <table>"})
    public void enterPatientDetails(Table table) throws Exception {
        List<PatientAttribute> patientAttributes = transformTableToPatientAttributes(table);
        registrationPage.fillAttributes(patientAttributes);
    }

    @Step("Save Patient and refresh page")
    public void savePatient() {
        registrationPage.clickSave();
        waitForAppReady();
        DriverFactory.getDriver().navigate().refresh();
    }

    @Step("Enter Clinical Details")
    public void enterClinicalDetails() {
        registrationPage.enterVisitDetailsPage();
    }

    @Step("Start <visitType> visit and navigate to Programs page")
    public void startVisitNavigateProgram(String visitType) {
        registrationPage.showAllVisitTypeOptions();
        registrationPage.findVisit(visitType).click();
        waitForAppReady();
        RegistrationVisitDetailsPage registrationVisitPage = PageFactorySpec.get(RegistrationVisitDetailsPage.class);
        registrationVisitPage.saveButton.click();
    }

    @Step("Verify Legal Rep Values for autocomplete")
    public void verifyLegalRepValues() {
        registrationPage.verifyLegalRepValues();
    }


    private ArrayList<PatientAttribute> transformTableToPatientAttributes(Table table) throws Exception {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();

        ArrayList<PatientAttribute> patientAttributes = new ArrayList<PatientAttribute>();
        for (String columnName : columnNames) {
            PatientAttribute patientAttribute = Fields.getPatientAttribute(columnName);
            if (patientAttribute != null) {
                patientAttribute.setValue(rows.get(0).getCell(columnName));
                patientAttributes.add(patientAttribute);
                ammanPatient.addAttribute(patientAttribute);
            }
        }

        return patientAttributes;
    }

    @Step("Create patient <Name> using API with <visit Type> visit")
    public void implementation1(String name, String visitType) throws Exception {
        PatientAttribute patientAttribute = Fields.getPatientAttribute("firstName");
        patientAttribute.setValue(name);
        ammanPatient.addAttribute(patientAttribute);
        registrationPage.createPatientUsingApi(ammanPatient);
        SpecStoreHelper.store(AmmanPatient.class,ammanPatient);
        ammanPatient.setVisitType(visitType);
        registrationPage.startVisitUsingApi(ammanPatient);
    }

    @Step("End visit for previously created patient using API")
    public void implementation2() {
        registrationPage.closeVisitUsingApi(ammanPatient);
    }

    @Step("Start <visitType> visit using API")
    public void implementation3(String visitType) {
        ammanPatient.setVisitType(visitType);
        registrationPage.startVisitUsingApi(ammanPatient);
    }

    @Step("Verify Legal Rep Details after Save")
    public void implementation4() {
        registrationPage.verifyLegalRepAfterSave();
    }

    @Step("Verify caretaker details after save")
    public void implementation5() {
        registrationPage.verifyCaretakerAfterSave();
    }

    @Step("Verify patient id documents after save")
    public void implementation6() {
        registrationPage.verifyIDDocumentsAfterSave();
    }
}
