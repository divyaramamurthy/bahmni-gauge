package org.bahmni.gauge.endtb.clinical.domain;

import org.bahmni.gauge.common.clinical.domain.Specimen;

public class EndTBSpecimen extends Specimen{

    private String typeOfVisit;

    private String typeOfVisitUuid;

    private String laboratoryName;

    private String laboratoryNameUuid;

    private String smearDateOfTest;

    private String smearTestId;

    private String smearResult;

    private String smearResultUuid;

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getSmearDateOfTest() {
        return smearDateOfTest;
    }

    public void setSmearDateOfTest(String smearDateOfTest) {
        this.smearDateOfTest = smearDateOfTest;
    }

    public String getSmearTestId() {
        return smearTestId;
    }

    public void setSmearTestId(String smearTestId) {
        this.smearTestId = smearTestId;
    }

    public String getSmearResult() {
        return smearResult;
    }

    public void setSmearResult(String smearResult) {
        this.smearResult = smearResult;
    }

    public String getTypeOfVisit() {
        return typeOfVisit;
    }

    public void setTypeOfVisit(String typeOfVisit) {
        this.typeOfVisit = typeOfVisit;
    }

    public String getTypeOfVisitUuid() {
        return typeOfVisitUuid;
    }

    public void setTypeOfVisitUuid(String typeOfVisitUuid) {
        this.typeOfVisitUuid = typeOfVisitUuid;
    }

    public String getLaboratoryNameUuid() {
        return laboratoryNameUuid;
    }

    public void setLaboratoryNameUuid(String laboratoryNameUuid) {
        this.laboratoryNameUuid = laboratoryNameUuid;
    }

   public String getSmearResultUuid() {
        return smearResultUuid;
    }
    public void setSmearResultUuid(String smearResultUuid) {
        this.smearResultUuid = smearResultUuid;
    }
}
