package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.clinical.DispositionPage;

public class DispositionPageSpec extends BahmniPage {
    private DispositionPage dispositionPage;

    public DispositionPageSpec() {
        dispositionPage = PageFactorySpec.get(DispositionPage.class);
    }

    @BeforeClassSteps
    public void waitForAppReady(){ BahmniPage.waitForSpinner(DriverFactory.getDriver());}

    @Step("Select <Disposition Type> disposition")
    public void captureDataForDisposition(String dispositionType){
        dispositionPage.captureDataForDisposition(dispositionType);
    }

    @Step("Select <Disposition Type> disposition with notes <notes>")
    public void captureDataForDisposition(String dispositionType, String notes){
        dispositionPage.captureDataForDisposition(dispositionType,notes);
    }
}
