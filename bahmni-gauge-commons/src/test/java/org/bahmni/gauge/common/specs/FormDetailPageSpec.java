package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.formBuilder.FormDetailPage;
import org.bahmni.gauge.common.formBuilder.domain.Form;
import org.bahmni.gauge.data.StoreHelper;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FormDetailPageSpec {
    private final WebDriver driver;
    private FormDetailPage formDetailPage;

    public FormDetailPageSpec() {
        this.driver = DriverFactory.getDriver();
        formDetailPage = PageFactorySpec.get(FormDetailPage.class);
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(driver);
    }

    @Step("Click on Edit")
    public void showConfirmEdit() {
        formDetailPage.clickOnEdit();
    }

    @Step("Confirm edit")
    public void goToEditModal() {
        formDetailPage.clickOnOK();
    }

    @Step("Navigate to form list")
    public void goToFormList() {
        formDetailPage.clickOnFormBuilder();
    }

    @Step("Click on publish")
    public void publishForm() {
        String uuid = getUuid();
        Form form = new Form();
        form.setUuid(uuid);
        StoreHelper.store(Form.class, form);
        formDetailPage.clickOnPublish();
    }

    @Step("Click on save")
    public void saveForm() {
        formDetailPage.clickOnSave();
    }

    @Step("Select <propertyType> property for <controlName>")
    public void setPropertyForControl(String propertyType, String controlName) {
        List<WebElement> labelList = formDetailPage.getCanvasBodyLabelList();
        WebElement control = findControl(labelList, controlName);

        Assert.assertTrue("No " + controlName + "in canvas", control != null);

        formDetailPage.clickOnControl(control);
        formDetailPage.clickOnProperty(propertyType);
    }

    private WebElement findControl(List<WebElement> labelList, String controlName) {
        for (int i = 0; i < labelList.size(); i++) {
            if(labelList.get(i).getText().equals(controlName)) {
                return labelList.get(i);
            }
        }
        return null;
    }

    @Step("Save <formName> form using <formModleName> by API")
    public void saveFormByAPI(String formName, String formModelName) {
        String uuid = getUuid();
        Map<String, Object> formAttributes = new HashMap<>();
        formAttributes.put("name", formName);
        formAttributes.put("uuid", uuid);
        BahmniRestClient.get().saveFormUsingAPI(("form_" + formModelName + "_save.ftl"), formAttributes);
    }

    @Step("Change the <labelName> label name to <name>")
    public void changeLabelName(String labelName, String name) {
        formDetailPage.changeName(labelName, name);
    }


    @Step("Verify the form is non-editable")
    public void verifyFormIsNoneditable() {
        WebElement title = formDetailPage.findCanvasTitle();
        formDetailPage.doubleClickOnLabel(title.findElement(By.cssSelector("label")));
        Assert.assertEquals(0,title.findElements(By.cssSelector("input")).size());
    }

    @Step("Verify form is <version> version and <statusValue> status")
    public void verifyFormVersionAndStatus(String version, String statusValue) {
        String[] parsedFormInfo = parseVersionAndStatus(formDetailPage.getFormInfo());
        Assert.assertTrue("Version or status is not correct",
                (parsedFormInfo[0].equals(version) && parsedFormInfo[1].equals(statusValue)));
    }

    @Step("Verify canvas has <labelName> label")
    public void verifyFormHasLabel(String labelName) {
        List<WebElement> labelList = formDetailPage.getCanvasBodyLabelList();
        Assert.assertTrue("Canvas don't have " + labelName,
                hasLabel(labelList, labelName));
    }

    @Step("Verify <message> showed up")
    public void verifyMessageShowUp(String message) {
        Assert.assertTrue(message + "doesn't show up",
                driver.findElement(By.cssSelector(".message")).getText().equals(message));
    }

    @Step("Verify <sectionName> section has <controlName>")
    public void verifySectionOwnElement(String sectionName, String controlName) {
        WebElement sectionLabel = formDetailPage.findElementByText("label", sectionName);
        WebElement section = sectionLabel.findElement(By.xpath("../.."));
        List<WebElement> labels = section.findElements(By.cssSelector("label"));
        Assert.assertTrue(sectionName + "has no " + controlName, hasLabel(labels, controlName));
    }

    @Step("Verify <sectionName> section has no <controlName>")
    public void verifySectionNotOwnElement(String sectionName, String controlName) {
        WebElement sectionLabel = formDetailPage.findElementByText("label", sectionName);
        WebElement section = sectionLabel.findElement(By.xpath("../.."));
        List<WebElement> labels = section.findElements(By.cssSelector("label"));
        Assert.assertTrue(sectionName + "has no " + controlName, !hasLabel(labels, controlName));
    }

    @Step("Verify the form has edit button and notice message")
    public void verifyFormhasEditButton() {
        WebElement editButton = formDetailPage.getEditButton();
        WebElement buttonParent = editButton.findElement(By.xpath(".."));
        Assert.assertTrue("edit button doesn't show up on the page", editButton != null);
        Assert.assertEquals("This Form is a Published version. For editing click on",
                buttonParent.findElement(By.cssSelector(".info-message")).getText());
    }

    @Step("Verify the form doesn't has <buttonName> button")
    public void verifyButtonNotAppearOnForm(String buttonName) {
        List<WebElement> buttons = formDetailPage.getAllButton();
        Assert.assertTrue(buttonName + " shows up on the page", (findButtonByName(buttons, buttonName) == null));
    }

    private WebElement findButtonByName(List<WebElement> buttons, String buttonName) {
        for (WebElement button : buttons) {
            if(button.getText().equals(buttonName)) {
                return button;
            }
        }
        return null;
    }

    @Step("Verify <controlName> checked <propertyType> property")
    public void verifyControlCheckedProperty(String controlName, String propertyType) {
        List<WebElement> labelList = formDetailPage.getCanvasBodyLabelList();
        WebElement control = findControl(labelList, controlName);

        formDetailPage.clickOnControl(control);

        Assert.assertTrue(propertyType + " is not checked.", formDetailPage.isPropertyChecked(propertyType));
    }

    @Step("Verify <controlName> has notes icon")
    public void verifyControlHasNoteIcon(String controlName) {
        List<WebElement> labelList = formDetailPage.getCanvasBodyLabelList();
        WebElement control = findControl(labelList, controlName);
        WebElement controlSuper = control.findElement(By.xpath("../../.."));

        controlSuper.findElement(By.cssSelector(".form-builder-comment-toggle"));
    }

    @Step("Verify <controlName> is displayed by drop down style")
    public void verifyControlIsDropDown(String controlName) {
        List<WebElement> labelList = formDetailPage.getCanvasBodyLabelList();
        WebElement control = findControl(labelList, controlName);
        WebElement controlSuper = control.findElement(By.xpath("../../.."));

        controlSuper.findElement(By.cssSelector(".obs-control-select-wrapper"));
    }

    @Step("Verify <controlName> has asterisk mark")
    public void verifyControlHasAsteriskMark(String controlName) {
        List<WebElement> labelList = formDetailPage.getCanvasBodyLabelList();
        WebElement control = findControl(labelList, controlName);
        WebElement controlSuper = control.findElement(By.xpath("../../.."));

        controlSuper.findElement(By.cssSelector(".form-builder-asterisk"));
    }

    @Step("Verify confirmation message for form builder when click on edit button")
    public void verifyConfirmMessageShowUp() {
        Assert.assertTrue("Confirm message doesn't show up", formDetailPage.hasElement(By.cssSelector(".dialog--no-header")));

        WebElement confirmBox = formDetailPage.getEditConfirmBox();
        String message = confirmBox.findElement(By.cssSelector(".dialog--container")).getText();

        Assert.assertEquals("Edit of the form will allow you to create a new version of form. Do you want to proceed?", message);
    }

    @Step("Verify <buttonText> button is <enableOption> on form builder")
    public void verifyButtonDisplayed(String buttonText, String enableOption){
        if (enableOption.equalsIgnoreCase("enable"))
            Assert.assertTrue(buttonText+" button is disable",formDetailPage.findButtonByText(buttonText).isDisplayed());
        else
            Assert.assertTrue(buttonText+" button is enable",formDetailPage.findButtonByText(buttonText).isDisplayed());
    }

    private boolean hasLabel(List<WebElement> labelList, String labelName) {
        for(int i = 0; i < labelList.size(); i++) {
            if(labelList.get(i).getText().equals(labelName)) {
                return true;
            }
        }
        return false;
    }

    private String[] parseVersionAndStatus(String formInfo) {
        String[] splitInfo = formInfo.split(" - ");
        String[] splitFirstHarf = splitInfo[0].split(" ");
        splitInfo[0] = splitFirstHarf[splitFirstHarf.length - 1];
        return splitInfo;
    }

    private String getUuid() {
        String[] splitedString = driver.getCurrentUrl().split("/");
        return splitedString[splitedString.length - 1];
    }
}
