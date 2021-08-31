package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;

import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.clinical.ConsultationTabPage;
import org.bahmni.gauge.common.clinical.domain.Disposition;
import org.bahmni.gauge.data.StoreHelper;
import org.bahmni.gauge.util.StringUtil;
import org.junit.Assert;

/**
 * Created by atmaramn on 25/10/2016.
 */
public class ConsultationTabPageSpec {

    ConsultationTabPage consultationTabPage;

    public ConsultationTabPageSpec() {
        consultationTabPage = PageFactorySpec.getConsultationTabPage();
    }

    @BeforeClassSteps
    public void waitForAppReady(){ BahmniPage.waitForSpinner(DriverFactory.getDriver());}

    @Step("Verify Disposition details on consultation tab")
    public void verifyDisposition() {
        Disposition disposition = StoreHelper.getLatest(Disposition.class);
        consultationTabPage.verifyDisposition(disposition);
    }


    @Step("Verify display control with Caption <displayControlCaption> on <tab name> tab, has the following details <table>")
    public void verifyDisplayControlOnTabWithCaption(String displayControlCaption, String tab, Table table) {
        ConsultationTabPage consultationTabPage = PageFactorySpec.get(ConsultationTabPage.class);
        String displayControlText = consultationTabPage.getDisplayControlTextWithCaption(displayControlCaption);
        for (String data : table.getTableRows().get(0).getCellValues()) {
            data = StringUtil.transformPatternToData(data);
            Assert.assertTrue("String " + data + " does not exist. Actual String :" + displayControlText, displayControlText.contains(data));
        }
    }


}
