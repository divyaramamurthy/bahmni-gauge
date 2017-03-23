package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.clinical.DashboardPage;
import org.bahmni.gauge.common.clinical.ObservationsPage;
import org.bahmni.gauge.common.clinical.domain.DrugOrder;
import org.bahmni.gauge.common.program.domain.PatientProgram;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.StringUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservationSpec extends BaseSpec {
    private final WebDriver driver;

    public ObservationSpec() {
        driver = DriverFactory.getDriver();
    }

    @BeforeClassSteps
    public void waitForAppReady() {
        BahmniPage.waitForSpinner(DriverFactory.getDriver());
    }


    @Step("Select the template <template> from on the observation page")
    public void clickOnTreatmentEnrollment(String template) throws InterruptedException {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.selectTemplate(template);
        waitForAppReady();
    }

    @Step("Create a <formName> form with following data <table>")
    public void createForm(String formName, Table table) {
        Patient patient = new BahmniPage().getPatientFromSpecStore();
        PatientProgram patientProgram = new BahmniPage().getPatientProgramFromSpecStore();

        Map<String, Object> formAttributes = new HashMap<>();
        formAttributes.put("patientUuid", patient.getUuid());
        formAttributes.put("patientProgramUuid", patientProgram.getPatientProgramUuid());
        formAttributes.putAll(transformTableToMap(table));

        BahmniRestClient.get().createForm(formName + ".ftl", formAttributes);
    }

    @Step("Create a <formName> form using <formModelName> with <conceptName>")
    public void createForm(String formName, String formModelName, String conceptName) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.createAndPublishFormByAPI(formName, formModelName, conceptName);
    }

    private Map<String, String> transformTableToMap(Table table) {

        Map<String, String> formVariables = new HashMap<>();
        List<String> columnNames = table.getColumnNames();

        for (String columnName : columnNames) {
            formVariables.put(columnName, table.getTableRows().get(0).getCell(columnName));
        }

        return formVariables;
    }

    @Step("Click on expand all")
    public void expandAll() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickOnExpand();
    }

    @Step("Click on collapse all")
    public void collapseAll() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickOnCollapse();
    }

    @Step("Verify observations recorded under <formName>")
    public void verifyObservationsOnDashboard(String formName) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        dashboardPage.validateObservationDisplayControl(formName);
    }

    @Step("Verify prescribed drugs on the dashboard page")
    public void verifyDrugsOnDashboard() {
        List<DrugOrder> drugOrder = (List<DrugOrder>) new BahmniPage().getDrugOrderFromSpecStore();
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        for (DrugOrder drug : drugOrder)
            dashboardPage.validateDrugOrderDisplayControl(drug, "All active TB Drugs");
    }

    @Step("Verify display control <displayControlId> on dashboard, has the following details <table>")
    public void verifyDisplayControlContent(String displayControlId, Table table) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        waitForAppReady();
        String displayControlText = dashboardPage.getDisplayControlText(displayControlId.replace(" ", "-"));
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue(StringUtil.stringDoesNotExist(drugOrder, displayControlText), displayControlText.contains(drugOrder));
        }
    }

    @Step("Verify display control with Caption <controlCaption> on dashboard, has the following details <table>")
    public void verifyDisplayControlContentWithCaption(String controlCaption, Table table) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        String displayControlText = dashboardPage.getDisplayControlTextWithCaption(controlCaption);
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue(StringUtil.stringDoesNotExist(drugOrder, displayControlText), displayControlText.contains(drugOrder));
        }
    }

    @Step("Expand all sections from display control with Caption <controlText>")
    public void expandControl(String controlText) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        dashboardPage.expandControlWithCaption(controlText);
    }

    @Step("Click on add new obs form")
    public void showAllObsForm() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickOnAddNewObsForm();
    }

    @Step("Click on <strongText> stronged text")
    public void clickOnLabel(String strongText) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickOnLabel(observationsPage.findStrongByText(strongText));
    }

    @Step("Search <formName> obs form")
    public void showSearchedObsForm(String formName) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.searchObsForm(formName);
    }

    @Step("Verify <formName> is disabled to add")
    public void verifyFormDisabled(String formName) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        Boolean isDisabled = observationsPage.getDisabledValue(formName);
        Assert.assertTrue(isDisabled);
    }

    @Step("Choose <selectedObsForm> obs form")
    public void showChoosedObsForm(String selectedObsForm) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickOnSelectedObsForm(selectedObsForm);
    }

    @Step("Expand <formName> obs form")
    public void expandObsForm(String formName) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.expandObservationTemplate(formName);
    }

    @Step("click  <displayControlId> on dashboard, and verify displayed dialog has the following details <table>")
    public void verifyDialogContent(String displayControlId, Table table) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        dashboardPage.clickDisplayControlHeader(displayControlId);
        String displayControlText = dashboardPage.getDialogText(By.cssSelector(".ngdialog-content"));
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue(StringUtil.stringDoesNotExist(drugOrder, displayControlText), displayControlText.contains(drugOrder));
        }
        dashboardPage.closeDialog();
    }

    @Step("Verify the <template> concept set is <displayType>")
    public void verifyObservationFormContent(String template, String displayType) {
        DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
        if (displayType.equalsIgnoreCase("not displayed"))
            Assert.assertFalse("Element " + template + " is displayed", dashboardPage.hasElement(By.cssSelector("#concept-set-4")));
        else
            Assert.assertTrue("Element " + template + " is not displayed", dashboardPage.hasElement(By.cssSelector("#concept-set-4")));
    }

    @Step("Verify <formName> is added to the left pane")
    public void verifyObservationFormAddedToLeftPane(String formName) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        WebElement leftPane = observationsPage.getLeftPane();
        List<WebElement> allTabs = leftPane.findElements(By.cssSelector("span"));
        WebElement tab = findWebElementByText(allTabs, formName);

        Assert.assertTrue("form is not added to pane", tab != null);
    }

    private WebElement findWebElementByText(List<WebElement> allElement, String text) {
        for(WebElement element : allElement) {
            if(element.getText().equals(text)) {
                return element;
            }
        }
        return null;
    }

    @Step("Close the app")
    public void closeApplication() {
        new BahmniPage().closeApp(driver);
    }

    @Step("Fill <template> template with following observation details <table>")
    public void enterObservations(String template, Table table) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.enterObservations(template, table);
    }

    @Step("Enter <template> template with following observation details <table>")
    public void enterObsGroupValue(String template, Table table) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.enterObsGroup(template, table);
    }

    @Step("Enter <template> template with all observation details <table>")
    public void enterAllValue(String template, Table table) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.enterAll(template, table);
    }

    @Step("Enter <template> template with all observation details with hide label <table>")
    public void enterAllValueWithHideLabel(String template, Table table) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.enterAllWithHideLabel(template, table);
    }

    @Step("Click the error message button")
    public void clickOkButton() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickOk();
    }

    @Step("Click add Note button")
    public void clickAddNoteButton() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clickAddNote();
    }

    @Step("Fill Tuberculosis - Followup template with following observation details <table>")
    public void enterTuberculosis(Table table) throws InterruptedException {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.expandObservationTemplate("Tuberculosis - Followup");
        List<TableRow> row = table.getTableRows();
        String[] values = row.get(0).getCell("Adverse Effects").split(":");
        for (String value : values) {
            observationsPage.adverseEffects.sendKeys(value);
            observationsPage.waitForElementOnPage(observationsPage.adverseEffects);
            Thread.sleep(1000);
            observationsPage.selectSuggestion();
        }
    }

    @Step("Navigate back to program dashboard")
    public void navigateBackToDashboard() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.navigateToDashboard();
        waitForAppReady();
    }

    @Step("Enter <value> into control")
    public void enterValueToControl(String value) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.enterValueToFirstInput(value);
    }

    @Step("Clear the value in controls")
    public void clearValueOfControl() {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.clearValueToFirstInput();
    }

    @Step("Remove Adverse effect from <template> <table>")
    public void removeAdverseEffect(String template, Table data) throws InterruptedException {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.removeAdverseEffect(template, data);
    }

    @Step("Add more observation")
    public void addmoreobservation(){
        ObservationsPage observationsPage=PageFactory.get(ObservationsPage.class);
        observationsPage.addMoreObservation();
        waitForAppReady();
    }

    @Step("Add chief complaint on \"History and Examination\" template <table>")
    public void addMoreChiefComplaint(String template,Table table){
        ObservationsPage observationsPage=PageFactory.get(ObservationsPage.class);
        observationsPage.addChiefComplaints(template,table);

    }

    @Step("Remove chief complaint on \"History and Examination\" template <table>")
    public void removeChiefComplaint(String template,Table data){
        ObservationsPage observationsPage=PageFactory.get(ObservationsPage.class);
        observationsPage.removeChiefComplaints(template,data);
    }

    @Step("Upload consultation images with Notes on \"History and Examinations\" <table>")
    public void uploadConsultationImage(String template,Table table) throws InterruptedException, IOException, AWTException {
        ObservationsPage observationsPage=PageFactory.get(ObservationsPage.class);
        observationsPage.uploadConsultationImageAndAddComment(template,table);
    }


    @Step("Remove image <imageNumber> from <History and Examinations> on Consultation page")
    public void removeConsultationImage(Integer imageNumber, String template) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.expandObservationTemplate(template);
        observationsPage.removeImage(imageNumber);
        waitForAppReady();
    }

    @Step("Verify error message <message> is displayed")
    public void verifyErrorMessage(String message){
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        observationsPage.waitForElementOnPage(By.cssSelector(".error-message-container"));
        Assert.assertEquals("Error popup message dont match", message, observationsPage.findElement(By.cssSelector(".msg")).getText());
    }

    @Step("Verify <elementName> in <controlName> is disabled")
    public void verifyControlIsDisabled(String elementName, String controlName) {
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        WebElement controlLabel = observationsPage.findLabelByText(controlName);
        WebElement controlSuper = controlLabel.findElement(By.xpath("../.."));
        String cssStyle = "div.obs-comment-section-wrap >" + elementName;
        WebElement controlTextArea = controlSuper.findElement(By.cssSelector(cssStyle));
        String result = controlTextArea.getAttribute("disabled");
        Assert.assertTrue(result != null);
    }

    @Step("Verify <controlName> is <property>")
    public void verifyControlHasProperty(String controlName, String property){
        String cssStyle = parsePropertyToCss(property);
        Assert.assertTrue("Property doesn't exist.", (cssStyle != null));
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        WebElement controlLabel = observationsPage.findLabelByText(controlName);
        WebElement controlSuper = controlLabel.findElement(By.xpath("../.."));
        controlSuper.findElement(By.cssSelector(cssStyle));
    }

    @Step("Verify <controlName> set is <expandedOrCollapsed>")
    public void verifyExpandOrCollapsed(String controlName, String status){
        ObservationsPage observationsPage = PageFactory.get(ObservationsPage.class);
        WebElement controlLabel = observationsPage.findStrongByText(controlName);
        WebElement controlSuper = controlLabel.findElement(By.xpath("../.."));
        if (status.equalsIgnoreCase("expanded")){
            controlSuper.findElement(By.cssSelector(".active-group-controls"));
        }
        else {
            controlSuper.findElement(By.cssSelector(".closing-group-controls"));
        }
    }

    private String parsePropertyToCss(String property) {
        if (property.equalsIgnoreCase("autocomplete")) { return ".is-searchable"; }
        if (property.equalsIgnoreCase("multiselect")) { return ".Select--multi"; }
        if (property.equalsIgnoreCase("dropdown")) { return ".Select"; }

        return null;
    }
}
