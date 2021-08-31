package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.formBuilder.ImplementerInterfacePage;
import org.openqa.selenium.WebDriver;

public class ImplementerInterfaceSpec {
    private final WebDriver driver;
    private ImplementerInterfacePage implementerInterfacePage;

    public ImplementerInterfaceSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(driver);
    }

    @Step("Click on form builder app")
    public void goToFormBuilderPage() {
        implementerInterfacePage = PageFactorySpec.getImplementerInterfacePage();
        implementerInterfacePage.clickFormBuilderApp();
    }
}
