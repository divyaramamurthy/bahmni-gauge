package org.bahmni.gauge.endtb.clinical.domain;

import org.bahmni.gauge.common.clinical.domain.ObservationForm;

public class BaselineForm extends ObservationForm {

    private String dateBaselineAssessment;
    private String maritalStatus;
    private String otherMaritalStatus;
    private String homeless;
    private String employed;
    private String otherEmployment;
    private String hasThePatientEverBeenTreatedForTBInThePast;
    private String yearFirstTBTreatment;

    public BaselineForm(String dateBaselineAssessment,String maritalStatus,String otherMaritalStatus,
                    String homeless, String employed, String otherEmployment,String hasThePatientEverBeenTreatedForTBInThePast, String yearFirstTBTreatment){
        this.dateBaselineAssessment = dateBaselineAssessment;
        this.maritalStatus= maritalStatus;
        this.otherMaritalStatus = otherMaritalStatus;
        this.homeless= homeless;
        this.employed = employed ;
        this.otherEmployment = otherEmployment;
        this.hasThePatientEverBeenTreatedForTBInThePast = hasThePatientEverBeenTreatedForTBInThePast;
        this.yearFirstTBTreatment = yearFirstTBTreatment;

    }

    public String getDateBaselineAssessment(){return this.dateBaselineAssessment; }
    public String getMaritalStatus(){return this.maritalStatus; }
    public String getOtherMaritalStatus(){return this.otherMaritalStatus; }
    public String getHomeless(){return this.homeless; }
    public String getEmployed(){return this.employed; }
    public String getOtherEmployment(){return this.otherEmployment; }
    public String getHasThePatientEverBeenTreatedForTBInThePast(){return this.hasThePatientEverBeenTreatedForTBInThePast; }
    public String getyearFirstTBTreatment(){return this.yearFirstTBTreatment; }
}
