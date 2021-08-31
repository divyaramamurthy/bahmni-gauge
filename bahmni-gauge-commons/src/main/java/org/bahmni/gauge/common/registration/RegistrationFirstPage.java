package org.bahmni.gauge.common.registration;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.TestSpecException;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.TableTransformer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class RegistrationFirstPage extends BahmniPage {

	public static final String URL = BASE_URL.concat("/bahmni/registration/#/patient/new");

	@FindBy(how = How.CSS, using = "#givenName")
	public WebElement txtPatientName;

	@FindBy(how = How.CSS, using = "#registrationNumber")
	public WebElement txtRegistrationNumber;

	@FindBy(how = How.CSS, using = "#familyName")
	public WebElement familyName;

	@FindBy(how = How.CSS, using = "#gender")
	public WebElement gender;

	@FindBy(how = How.CSS, using = "#address4")
	public WebElement purusava;

	@FindBy(how = How.CSS, using = "#ageYears")
	public WebElement ageYears;

	@FindBy(how = How.CSS, using = "#cityVillage")
	public WebElement village;

	@FindBy(how = How.CSS, using = ".submit-btn")
	public WebElement save;

	@FindBy(how = How.CSS, using = "div[option-click=\"visitControl.startVisit\"] li.primaryOption  button")
	public WebElement enterVisitDetails;

	@FindBy(how = How.CSS, using = "button[ng-click=\"setSubmitSource('enterVisitDetails')\"]")
	public WebElement enterVisitDetailButton;

	@FindBy(how = How.CSS, using = "#address1")
	public WebElement addressLine;

	@FindBy(how = How.CSS, using = "#address2")
	public WebElement gramPanchayat;

	//release-0.85
	@FindBy(how= How.ID, using = "patientIdentifierValue")
	public WebElement patientIdentifierValue;

	//support for release-0.84
	@Deprecated
	@FindBy(how = How.CSS, using = ".registraion_legend strong > span")
	public WebElement _patientIdentifierValue;

	@FindBy(how = How.CSS, using = ".buttonClass")
	public List<WebElement> visitTypeOptions;

	@FindBy(how= How.CSS, using = ".submit-btn-container .toggle-button")
	public WebElement visitTypeOptionsBtn;

	@FindBy(how = How.CSS, using = ".fa-search")
	public WebElement searchLink;

	@FindBy(how = How.CSS, using = "#hasOldIdentifier")
	public WebElement enterID_checkbox;

	@FindBy(how = How.CSS, using = "#registrationNumber")
	public WebElement enterID;

	@FindBy(how = How.CSS, using = ".fa-home")
	public WebElement homepage;

	@FindBy(how= How.CSS, using = ".btn-user-info")
	public WebElement user_info;

	@FindBy(how= How.CSS, using = ".fa-power-off")
	public WebElement logout;

	@FindBy(how= How.CSS, using = ".ngdialog-content #modal-refill-button")
	public WebElement sequenceConfirm;

	@FindBy(how = How.CSS, using = ".capture-photo-btn button")
	public WebElement capturePhotoButton;

	public void clickSave() {
		save.click();
	}

	public void registerPatient(Patient patient) throws InterruptedException {
		waitForSpinner();
		try{
			if(enterID_checkbox.isDisplayed() & patient.getIdNumber()!= null) {
				enterID_checkbox.click();
				txtRegistrationNumber.sendKeys(patient.getIdNumber());
			}
		} catch (NoSuchElementException ex){

		}
		txtPatientName.clear();
		txtPatientName.sendKeys(patient.getFirstName());

		familyName.clear();
		familyName.sendKeys(patient.getLastName());

		new Select(gender).selectByVisibleText(patient.getGender());

		ageYears.clear();
		ageYears.sendKeys(patient.getAge());

		doActions(patient);
		clickSave();

		try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-refill-button")));

            List<WebElement> elements = driver.findElements(By.cssSelector("#modal-refill-button"));

            if (elements.size() != 0) {
                elements.get(0).click();
            }
        } catch (Exception e) {
        }
	}

	public void registerPatientWithID(Patient patient) throws InterruptedException {
		enterID_checkbox.click();
		txtRegistrationNumber.sendKeys(patient.getIdNumber());
		txtPatientName.sendKeys(patient.getFirstName());
		familyName.sendKeys(patient.getLastName());
		new Select(gender).selectByVisibleText(patient.getGender());
		ageYears.sendKeys(patient.getAge());
		doActions(patient);
		clickSave();

		try {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-refill-button")));

			List<WebElement> elements = driver.findElements(By.cssSelector("#modal-refill-button"));

			if (elements.size() != 0) {
				elements.get(0).click();
			}
		} catch (Exception e) {
		}
	}

	protected void doActions(Patient patient) {
		if(patient.getVillage()!=null) {

			village.clear();
			village.sendKeys(patient.getVillage());
		}
		if (patient.getPhoto()!=null && patient.getPhoto().equalsIgnoreCase("true")){
			capturePhotoButton.click();

			waitForElementOnPage(By.id("click")).click();

			waitForElementOnPage(By.cssSelector(".confirmImage.confirm")).click();
		}
		if(patient.getGramPanchayat()!=null) {

			gramPanchayat.clear();
			gramPanchayat.sendKeys(patient.getGramPanchayat());
		}
	}

	public void navigateToSearchPage() {
		searchLink.click();
	}

	public void verifyPatientWithIdentifierAndName() {
//		Patient patient = getPatientFromSpecStore();
		//TODO: Find a way to identify the identifier in edit mode with correct selector
		//TODO: write a correct assertion
//		Assert.assertTrue(familyName.getText().equals(lastName) && txtPatientName.getText().equals(firstName));
	}

	public void selectEnterPatientID(){
		enterID_checkbox.click();
	}

	public Patient transformTableRowToPatient(TableRow row, List<String> headers) throws Exception {
		Patient patient = new Patient();
		return (Patient)transform(row,patient,headers);
	}

	public WebElement findVisit(String visit) {
		return find(visitTypeOptions,visit);
	}

	public void logout(){
		user_info.click();
		logout.click();
	}

	public void enterVisitDetailsPage() {
		enterVisitDetails.click();
	}
    public void createPatientUsingApi(Table table) throws Exception {
		Patient patient = TableTransformer.asEntity(table,Patient.class);
        BahmniRestClient.get().createPatient(patient,"patient_create.ftl");
		patient.setIdNumber(patient.getIdentifier().substring(3));
		patient.setPrefix(patient.getIdentifier().substring(0,3));
        storePatientInSpecStore(patient);
    }

	public void createPatients(Table table) throws Exception {
		Patient patient = transformTableToPatient(table);
		registerPatient(patient);

		waitForSpinner();
		String path = driver.getCurrentUrl();
		String uuid = path.substring(path.lastIndexOf('/') + 1);
		if (!Objects.equals(uuid, "new")) {
			patient.setUuid(uuid);
			patient.setIdentifier(_patientIdentifierValue.getText());
			storePatientInSpecStore(patient);
		}
	}

	public void createPatientWithId(Table table) throws Exception {
		Patient patient = transformTableToPatient(table);
		registerPatientWithID(patient);

		waitForSpinner();
		String path = driver.getCurrentUrl();
		String uuid = path.substring(path.lastIndexOf('/') + 1);
		if (!Objects.equals(uuid, "new")) {
			patient.setUuid(uuid);
			patient.setIdentifier(_patientIdentifierValue.getText());
			storePatientInSpecStore(patient);
		}
	}

	protected Patient transformTableToPatient(Table table) throws Exception {
		List<TableRow> rows = table.getTableRows();
		List<String> columnNames = table.getColumnNames();

		if (rows.size() != 1) {
			throw new TestSpecException("Only one patient should be provided in the table");
		}

		return transformTableRowToPatient(rows.get(0), columnNames);
	}

	public void showAllVisitTypeOptions(){
		visitTypeOptionsBtn.click();
	}


	public void enterVisitDetailsFromTable(Table table) {
        waitForSpinner();
		List<WebElement> webElements = driver.findElements(By.cssSelector(".field-value>div>div>input"));
		WebElement webElement = driver.findElement(By.cssSelector(".field-value>div>div>textarea"));
		webElement.clear();
		for (WebElement element:webElements)
			element.clear();

		webElements.get(0).sendKeys(table.getColumnValues("height").get(0));
		webElements.get(1).sendKeys(table.getColumnValues("weight").get(0));
		webElements.get(2).sendKeys(table.getColumnValues("fees").get(0));
		if(table.getColumnNames().contains("comments"))
			webElement.sendKeys(table.getColumnValues("comments").get(0));

		driver.findElement(By.cssSelector(".confirm")).click();

	}

	public String getDisplayControlText(String displayControl) {
		if(displayControl.toLowerCase().contains("bmi"))
			return driver.findElement(By.cssSelector(".concept-set-container-view")).getText().trim().replace("\n", "");
		return null;
	}

	public void verifyPatientDetails(Patient patient) {
		try{
		if(enterID_checkbox.isDisplayed() && patient.getIdNumber()!= null) {
			Assert.assertEquals("Identifier dont match",patient.getIdentifier(),txtRegistrationNumber.getAttribute("value"));
		}} catch (NoSuchElementException ex){

		}

		Assert.assertEquals("First Name dont match",patient.getFirstName(),txtPatientName.getAttribute("value"));
		Assert.assertEquals("Last Name dont match",patient.getLastName(),familyName.getAttribute("value"));

		Assert.assertEquals("Gender dont match",patient.getGender(),new Select(gender).getFirstSelectedOption().getText());
		Assert.assertEquals("Age dont match",patient.getAge(),ageYears.getAttribute("value"));

		verifyActions(patient);
	}

	private void verifyActions(Patient patient) {
		if(patient.getVillage()!=null)
			Assert.assertEquals("Village dont match",patient.getVillage(),village.getAttribute("value"));

	}
}
