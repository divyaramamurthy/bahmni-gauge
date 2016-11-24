package org.bahmni.gauge.common.clinical.domain;

public class Specimen {
    private String dateOfSampleCollection;

    private String identifier;

    private String typeOfSample;

    private String note;
    private String smearResult;
    private String labIdNumber;
    private String otherSymptom;
    private String aFBDate;
    private String aFBTime;
    private String xpertTestDate;
    private String xpertTestId;
    private String xpertResult;
    private String cultureResults;
    private String MICInterpretation;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSmearResult() {
        return smearResult;
    }

    public void setSmearResult(String smearResult) {
        this.smearResult = smearResult;
    }

    public String getLabIdNumber() {
        return labIdNumber;
    }

    public void setLabIdNumber(String labIdNumber) {
        this.labIdNumber = labIdNumber;
    }

    public String getOtherSymptom() {
        return otherSymptom;
    }

    public void setOtherSymptom(String otherSymptom) {
        this.otherSymptom = otherSymptom;
    }

    public String getaFBDate() {
        return aFBDate;
    }

    public void setaFBDate(String aFBDate) {
        this.aFBDate = aFBDate;
    }

    public String getaFBTime() {
        return aFBTime;
    }

    public void setaFBTime(String aFBTime) {
        this.aFBTime = aFBTime;
    }

    public String getXpertTestDate() {
        return xpertTestDate;
    }

    public void setXpertTestDate(String xpertTestDate) {
        this.xpertTestDate = xpertTestDate;
    }

    public String getXpertTestId() {
        return xpertTestId;
    }

    public void setXpertTestId(String xpertTestId) {
        this.xpertTestId = xpertTestId;
    }

    public String getXpertResult() {
        return xpertResult;
    }

    public void setXpertResult(String xpertResult) {
        this.xpertResult = xpertResult;
    }

    public String getDateOfSampleCollection() {
        return dateOfSampleCollection;
    }

    public void setDateOfSampleCollection(String dateOfSampleCollection) {
        this.dateOfSampleCollection = dateOfSampleCollection;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTypeOfSample() {
        return typeOfSample;
    }

    public void setTypeOfSample(String typeOfSample) {
        this.typeOfSample = typeOfSample;
    }

    public String getCultureResults() {
        return cultureResults;
    }

    public void setCultureResults(String cultureResults) {
        this.cultureResults = cultureResults;
    }

    public String getMICInterpretation() {
        return MICInterpretation;
    }

    public void setMICInterpretation(String MICInterpretation) {
        this.MICInterpretation = MICInterpretation;
    }


}
