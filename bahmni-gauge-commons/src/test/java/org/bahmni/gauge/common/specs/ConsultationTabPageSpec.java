package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.clinical.ConsultationTabPage;
import org.bahmni.gauge.common.clinical.domain.Disposition;
import org.bahmni.gauge.data.StoreHelper;

/**
 * Created by atmaramn on 25/10/2016.
 */
public class ConsultationTabPageSpec{

    ConsultationTabPage consultationTabPage;

    public ConsultationTabPageSpec(){
       consultationTabPage= PageFactory.get(ConsultationTabPage.class);
    }

    @BeforeClassSteps
    public void waitForAppReady(){
        consultationTabPage.waitForSpinner();
        consultationTabPage= PageFactory.get(ConsultationTabPage.class);
    }

    @Step("Verify Disposition details on consultation tab")
    public void verifyDisposition(){
        Disposition disposition= StoreHelper.getLatest(Disposition.class);
        consultationTabPage.verifyDisposition(disposition);
    }

}
