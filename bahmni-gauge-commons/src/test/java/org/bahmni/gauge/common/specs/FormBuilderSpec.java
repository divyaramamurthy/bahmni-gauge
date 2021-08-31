package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.formBuilder.FormBuilderPage;
import org.bahmni.gauge.common.formBuilder.domain.Form;
import org.bahmni.gauge.data.StoreHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;

public class FormBuilderSpec {
    private final WebDriver driver;
    private FormBuilderPage formBuilderPage;

    public FormBuilderSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(driver);
    }

    @Step("Click create a form")
    public void goToCreateFormDialog() {
        formBuilderPage = PageFactorySpec.getFormBuilderPage();
        formBuilderPage.clickCreateForm();
    }

    @Step("Create a form with name <formName>")
    public void enterFormName(String formName) {
        formBuilderPage = PageFactorySpec.getFormBuilderPage();
        formBuilderPage.enterName(formName);
        String uuid = getUuid();
        Form form = new Form();
        form.setUuid(uuid);
        StoreHelper.store(Form.class, form);
    }

    @Step("Enter version <versionNumber> of <formName> form details")
    public void enterFormDetail(String versionNumber, String formName) {
        formBuilderPage = PageFactorySpec.getFormBuilderPage();
        formBuilderPage.clickOnAction(versionNumber, formName);
    }

    @Step("Verify version <versionNum> of <formName> form <formProperty> is <value> on form dashboard")
    public void verifyFormPropertyOnFormDashboard(String versionNum, String formName, String formProperty, String value) {
        formBuilderPage = PageFactorySpec.getFormBuilderPage();
        List<WebElement> allFormProperty = formBuilderPage.findFormByNameAndVersion(versionNum, formName).findElements(By.cssSelector("td"));

        verifyFormProperty(formProperty, value, allFormProperty);
    }

    @Step("Verify version <versionNum> of <formName> form is created On today on form dashboard")
    public void verifyFormCreatedOnToday(String versionNum, String formName) {
        formBuilderPage = PageFactorySpec.getFormBuilderPage();
        List<WebElement> allFormProperty = formBuilderPage.findFormByNameAndVersion(versionNum, formName).findElements(By.cssSelector("td"));
        String today = String.format("%1$td %1$tb %1$ty", new Date());
        Assert.assertTrue("The created date of this form is not " + today, allFormProperty.get(2).getText().equals(today));
    }

    private void verifyFormProperty(String formProperty, String value, List<WebElement> allFormProperty) {
        if(formProperty.equalsIgnoreCase("version")) {
            Assert.assertTrue("The version of this form is not " + value, allFormProperty.get(1).getText().equals(value));
        }
        if(formProperty.equalsIgnoreCase("Status")) {
            Assert.assertTrue("The status of this form is not " + value, allFormProperty.get(3).getText().equals(value));
        }
        if (formProperty.equalsIgnoreCase("action")){
            if(value.equalsIgnoreCase("edit")) {
                allFormProperty.get(4).findElement(By.cssSelector(".fa-pencil"));
            }
            if (value.equalsIgnoreCase("published")) {
                allFormProperty.get(4).findElement(By.cssSelector(".fa-eye"));
            }
        }
    }

    private String getUuid() {
        String[] splitedString = driver.getCurrentUrl().split("/");
        return splitedString[splitedString.length - 1];
    }
}
