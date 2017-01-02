package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.radiologyUpload.RadiologyUploadPage;

/**
 * Created by atmaramn on 17/11/2016.
 */
public class RadiologyUploadPageSpec {
    private RadiologyUploadPage radiologyUploadPage;

    public RadiologyUploadPageSpec(){
        radiologyUploadPage = PageFactory.get(RadiologyUploadPage.class);
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
    public void createVisitRadiology(Table table){
        radiologyUploadPage.createNewVisit(table);
    }

}
