package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.admin.AdminPage;

public class AdminSpec  {
    private AdminPage adminPage;

    public AdminSpec(){
        adminPage = PageFactorySpec.getAdminPage();
    }

    @BeforeClassSteps
    public void waitForAppReady(){
        adminPage.waitForSpinner();
    }

    @Step("Click on order set app")
    public void goToOrderSetPage(){
        adminPage.clickOrderSet();
        waitForAppReady();
    }
}
