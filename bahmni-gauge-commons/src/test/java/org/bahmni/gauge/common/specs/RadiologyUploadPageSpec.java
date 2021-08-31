package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.radiology.RadiologyUploadPage;

public class RadiologyUploadPageSpec {
    private RadiologyUploadPage radiologyUploadPage;

    public RadiologyUploadPageSpec(){
        radiologyUploadPage = PageFactorySpec.get(RadiologyUploadPage.class);
    }
    @BeforeClassSteps
    public void waitForAppReady(){
        radiologyUploadPage.waitForSpinner();
    }

    @Step("Upload following images in visit <visitNumber> <table>")
    public void uploadImage(int visitNumber, Table table){
        radiologyUploadPage.uploadImage(visitNumber,table);
    }

    @Step("Save radiology upload")
    public void save(){
        radiologyUploadPage.save();
    }

    @Step("Create new visit on radiology upload page <table>")
    public void createNewVisit(Table table) {
        radiologyUploadPage.createNewVisit(table);
    }

    @Step({"Remove image no <1> on current visit", "Undo Remove image no <1> on current visit"})
    public void removeImage(int imageNumber) {
        radiologyUploadPage.removeImage(imageNumber);
    }

    @Step("Expand current visit")
    public void expandCurrentVisit() {
        radiologyUploadPage.expandCurrentVisit();
    }
}
