package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.registration.RegistrationFirstPage;
import org.bahmni.gauge.common.registration.RegistrationSearch;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.data.StoreHelper;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.TableTransformer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationFirstPageSpec {

	private final WebDriver driver;

	public RegistrationFirstPage registrationFirstPage;

	public RegistrationFirstPageSpec() {
		this.driver = DriverFactory.getDriver();
		this.registrationFirstPage = PageFactorySpec.getRegistrationFirstPage();
	}

	@BeforeClassSteps
	public void waitForAppReady() {
		BahmniPage.waitForSpinner(driver);
	}

	@Step("On the new patient creation page")
	public void navigateToPatientCreatePage() {
		driver.get(RegistrationFirstPage.URL);
	}

	@Step("Create the following patient <table>")
	public void createPatients(Table table) throws Exception {
		registrationFirstPage.createPatients(table);
	}
	@Step("Try creating patient with same identifier <table>")
	public void createPatientWithSameID(Table table) {
		Patient patient=TableTransformer.asEntity(table,Patient.class);
		Patient patientFromSpecStore=registrationFirstPage.getPatientFromSpecStore();
		patient.setIdNumber(patientFromSpecStore.getIdNumber());

		try {
			registrationFirstPage.registerPatient(patient);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Step("Verify Identifier Error popup with message <message> is displayed")
	public void verifyErrorOnPageWithMessage(String message){
		registrationFirstPage.waitForElementOnPage(By.cssSelector(".error-message-container"));
		Assert.assertEquals("Error popup message dont match",String.format(message,registrationFirstPage.getPatientFromSpecStore().getIdentifier()),registrationFirstPage.findElement(By.cssSelector("#view-content .msg")).getText());
	}
	@Step("Create patient with manual id <table>")
	public void createPatientWithId(Table table) throws Exception {
		registrationFirstPage.createPatientWithId(table);
	}

	@Step("Create the following patient with ID as recently created Patient <table>")
	public void createPatientsWithExistingID(Table table) throws Exception {
        RegistrationSearch registrationSearch = PageFactorySpec.get(RegistrationSearch.class);
		String recentlyCreatedPatientID = registrationSearch.getPatientFromSpecStore().getIdNumber();
		table.getTableRows().get(0).addCell("idNumber", recentlyCreatedPatientID);
		table.getColumnNames().add(1,"idNumber");
		registrationFirstPage.createPatientWithId(table);
	}

	@Step("Validate that the patient edit page is opened for previously created patient")
	public void validateThePatientPageIsOpened() {
		registrationFirstPage.verifyPatientWithIdentifierAndName();
	}

	@Step("Ensure that the patient edit page is opened for previously created patient")
	public void ensureThePatientPageIsOpened() {
		registrationFirstPage.verifyPatientWithIdentifierAndName();
	}

	@Step("Start a visit <visit>")
	public void startPatientVisit(String visit) throws InterruptedException {
		registrationFirstPage.showAllVisitTypeOptions();
		registrationFirstPage.findVisit(visit).click();
	}

	@Step("Select check to enter patient ID manually")
	public void selectCheckToEnterPatientID() {
		registrationFirstPage.selectEnterPatientID();
	}

	@Step("Enter Visit Details Page")
	public void enterVisitDetailsPage() {
		registrationFirstPage.enterVisitDetailsPage();
	}

	@Step("Enter Visit Details for Admitted Patient")
	public void enterVisitDetailsForAdmittedPatient() {
		registrationFirstPage.enterVisitDetailButton.click();
	}

	@Step("Enter visit details from table <table>")
	public void enterVisitDetailsTable(Table table) {
		registrationFirstPage.enterVisitDetailsFromTable(table);
	}

	@Step("Verify the details on <displayControl> display control <table>")
	public void verifyDisplayControl(String displayControl, Table table) {
		waitForAppReady();
		String displayControlText = registrationFirstPage.getDisplayControlText(displayControl);
		for (String value : table.getColumnValues("details"))
            Assert.assertTrue(value+" is not displayed",displayControlText.contains(value));
	}

	@Step("Create the following patient using api <table>")
	public void createPatientThroughAPI(Table table) throws Exception {
		registrationFirstPage.createPatientUsingApi(table);
	}

	@Step("Verify the patient creation fails")
	public void verifyPatientCreationWithSameID() {
		new BahmniPage().validateSystemException(driver);
	}

	@Step("Verify patient details")
	public void verifyPatientDetails(){
		registrationFirstPage.verifyPatientDetails(registrationFirstPage.getPatientFromSpecStore());
	}
	@Step("Edit previous patient details as follow <table>")
	public void editPatientDetails(Table table) throws Exception {
		registrationFirstPage.createPatients(table);
	}
	@Step("Open <type> visit at <location> for previous patient using api")
	public void openVisitThroughApi(String visitType, String location){
		StoreHelper.getLatest(Patient.class).setLocation(location);
		StoreHelper.getLatest(Patient.class).setVisitType(visitType);
		BahmniRestClient.get().create(StoreHelper.getLatest(Patient.class),"visit");
	}

	@Step("Verify <buttonText> button is <displayOption>")
	public void verifyButtonDisplayed(String buttonText, String displayOption){
		if (displayOption.equalsIgnoreCase("displayed"))
			Assert.assertTrue(buttonText+" button is not displayed",registrationFirstPage.findButtonByText(buttonText).isDisplayed());
		else
			Assert.assertTrue(buttonText+" button is displayed",registrationFirstPage.findButtonByText(buttonText).isDisplayed());

	}
}
