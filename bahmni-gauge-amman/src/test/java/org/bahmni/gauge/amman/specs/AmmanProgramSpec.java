package org.bahmni.gauge.amman.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.amman.SpecStoreHelper;
import org.bahmni.gauge.amman.clinical.AmmanProgramPage;
import org.bahmni.gauge.amman.registration.domain.AmmanPatient;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.program.domain.PatientProgram;
import org.bahmni.gauge.common.program.domain.Program;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.TableTransformer;

public class AmmanProgramSpec {
    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(DriverFactory.getDriver());
    }

    @Step("Enroll patient to the following program <programDetails>")
    public void enrollPatientToProgram(Table programDetails) {
        AmmanProgramPage ammanProgramPage = PageFactorySpec.get(AmmanProgramPage.class);
        Program treatment = TableTransformer.asEntity(programDetails, Program.class);
        ammanProgramPage.enrollToProgram(treatment);
        waitForAppReady();
    }


    @Step("Edit <Reconstructive Surgery> Program with following details <table>")
    public void editProgram(String programName, Table updatedProgramTable) {
        Program updatedProgram=TableTransformer.asEntity(updatedProgramTable, Program.class);
        AmmanProgramPage ammanProgramPage = PageFactorySpec.get(AmmanProgramPage.class);
        ammanProgramPage.editProgram(programName,updatedProgram);
    }

    @Step("Navigate to queues")
    public void navigateToQueues(){
        AmmanProgramPage ammanProgramPage = PageFactorySpec.get(AmmanProgramPage.class);
        ammanProgramPage.clickBackButton();
    }

    @Step("Enroll patient to reconstructive surgery program using API")
    public void enrollPatientToProgramAPI(){
        PatientProgram patientProgram = new PatientProgram();
        patientProgram.setPatient(SpecStoreHelper.getLatest(AmmanPatient.class));
        BahmniRestClient.get().enrollToProgram(patientProgram);
    }
}
