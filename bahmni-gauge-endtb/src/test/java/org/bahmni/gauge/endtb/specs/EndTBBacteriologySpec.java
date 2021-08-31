package org.bahmni.gauge.endtb.specs;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.clinical.DashboardPage;
import org.bahmni.gauge.common.clinical.domain.Specimen;
import org.bahmni.gauge.common.login.LoginPage;
import org.bahmni.gauge.common.program.domain.PatientProgram;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.endtb.clinical.EndTBBacteriologyPage;
import org.bahmni.gauge.endtb.clinical.domain.EndTBSpecimen;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.TableTransformer;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

public class EndTBBacteriologySpec {


    public EndTBBacteriologyPage bacteriologyPage;
    PageFactory pageFactory;


    public EndTBBacteriologySpec(){
        bacteriologyPage = EndTBPageFactorySpec.getEndTbBacteriologyPage();

    }

    @Step("Create a bacteriology specimen smear result <table>")
    public void createBacteriologySmearResult(Table table){
        LoginPage page = pageFactory.get(LoginPage.class);
        //BacteriologyPage page = pageFactory.get(BacteriologyPage.class);
        EndTBSpecimen specimen = TableTransformer.asEntity(table,EndTBSpecimen.class);
        System.out.println("Text Divya:" +specimen);
        specimen.setTypeOfVisitUuid(getConceptAnswerUuidForConceptName("Bacteriology, Type of visit", specimen.getTypeOfVisit()));
        specimen.setLaboratoryNameUuid(getConceptAnswerUuidForConceptName("Bacteriology, Laboratory Name", specimen.getLaboratoryName()));
        //specimen.setSmearResultUuid(getConceptAnswerUuidForConceptName("Bacteriology, Smear result", specimen.getSmearResult()));

        Patient patient = page.getPatientFromSpecStore();
        PatientProgram patientProgram = page.getPatientProgramFromSpecStore();

        BahmniRestClient.get().createBacteriologySpecimen("bacteriology_smear_create.ftl",specimen,patient,patientProgram);
    }

    private String getConceptAnswerUuidForConceptName(String conceptName, String answerName){
        Map<String,String> answers = BahmniRestClient.get().getConceptAnswersForConceptName(conceptName);
        // System.out.println("Text:" +answers);
        if(!answers.containsKey(answerName)){
          //  System.out.println("Text:" +answers);
            throw new IllegalArgumentException("The answer ["+ answerName+"] is invalid for the concept ["+conceptName+"]");

        }

        return answers.get(answerName);
    }

    @Step("Create a bacteriology specimen <table> ")
    public void createBacteriologySpecimen(Table table) {
      //  BacteriologyPage bacteriologyPage1 = PageFactory.get(BacteriologyPage.class);
        List<Specimen> specimens = TableTransformer.asEntityList(table, Specimen.class);
        bacteriologyPage.addSamples(specimens);
        bacteriologyPage.getPatientFromSpecStore().setSpecimens(specimens);
    }

    @Step("click specimenId on dashboard, and verify displayed dialog has the following content <table>")
    public void verifyDialogContent(Table table) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        bacteriologyPage.clickOnElementByID();
        String displayControlText = dashboardPage.getDialogText(By.cssSelector("span.value-text-only"));
        bacteriologyPage.verifyBacteriologySamples(displayControlText,table);
    }

}
