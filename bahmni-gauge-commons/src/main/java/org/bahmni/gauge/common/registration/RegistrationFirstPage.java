package org.bahmni.gauge.common.registration;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.TestSpecException;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class RegistrationFirstPage extends BahmniPage {

	public static final String URL = BASE_URL.concat("/registration/#/patient/new");

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

	@FindBy(how = How.CSS, using = ".right.confirm")
	public WebElement enterVisitDetails;

	@FindBy(how = How.CSS, using = "#address1")
	public WebElement addressLine;

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

	@FindBy(how= How.CSS, using = "i.fa-power-off")
	public WebElement logout;

	@FindBy(how= How.CSS, using = ".ngdialog-content #modal-refill-button")
	public WebElement sequenceConfirm;


	public void clickSave() {
		save.click();
	}

	public void registerPatient(Patient patient) throws InterruptedException {
		waitForSpinner();
		if(enterID_checkbox.isDisplayed() & patient.getIdNumber()!= null) {
			enterID_checkbox.click();
			txtRegistrationNumber.sendKeys(patient.getIdNumber());
			//+new Random().nextInt()
		}

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
		village.sendKeys(patient.getVillage());
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
		return transform(row,patient,headers);
	}

	public Patient transform(TableRow row,Patient patient,List<String> headers) throws Exception {
		for (String header : headers) {
			String value = row.getCell(header);
			try {
				if(propertyExists(patient,header)){
					BeanUtils.setProperty(patient, header, value);
				} else {
					throw new Exception("Property :"+header+" not found in "+patient.getClass());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		return patient;
	}

	private static boolean propertyExists (Object object, String property) {
		return PropertyUtils.isReadable(object, property) && PropertyUtils.isWriteable(object, property);
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
		List<TableRow> rows = table.getTableRows();
		List<String> columnNames = table.getColumnNames();

		if (rows.size() != 1) {
			throw new TestSpecException("Only one patient should be provided in the table");
		}

		Patient patient = transformTableRowToPatient(rows.get(0), columnNames);
        BahmniRestClient.get().createPatient(patient,"patient_create.ftl");
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

	public Patient transformTableToPatient(Table table) throws Exception {
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
}
