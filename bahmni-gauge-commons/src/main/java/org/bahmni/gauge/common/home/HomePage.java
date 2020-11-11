package org.bahmni.gauge.common.home;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.BahmniPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage extends BahmniPage {

	public static final String URL = BASE_URL.concat("/home#/dashboard");

	@FindBy(how= How.ID, using = "bahmni.registration")
    public WebElement registration;

	@FindBy(how = How.ID, using = "bahmni.adt")
	public WebElement inpatient;
	
	@FindBy(how= How.CSS, using = "i.icon-bahmni-documents")
    public WebElement patientDocuments;

	@FindBy(how= How.CSS, using = "i.icon-bahmni-admin")
    public WebElement admin;

	@FindBy(how= How.CSS, using = "i.icon-bahmni-reports")
    public WebElement exports;

	@FindBy(how= How.CSS, using = "i.icon-bahmni-program")
    public WebElement programs;

	@FindBy(how = How.CSS, using = "i.fa-caret-down")
	public WebElement user_info;

	@FindBy(how= How.CSS, using = "i.fa-power-off")
	public WebElement logout;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Clinical")
	public WebElement clinical;

	@FindBy(how = How.CSS, using = "#bahmni\\.orders")
	public WebElement orders;

	@FindBy(how = How.ID, using = "bahmni.implementer.interface")
	public WebElement implementerInterface;

	@FindBy(how = How.CSS, using = "#bahmni\\.radiology\\.document\\.upload")
	public WebElement radiologyUpload;

	@FindBy(how = How.CSS, using = "i.fa-user-secret")
	public WebElement formBuilder;

	public void clickRegistrationApp(){
    	waitForElementOnPage(registration).click();
    }

    public void clickClinicalApp() {
    	clinical.click();
    }


	public void clickAdminApp() {
    	admin.click();
    }

	public void logout() {
		waitForElementOnPage(user_info).click();
		waitForElementOnPage(logout).click();
	}

    public void clickExportsApp(){
    	exports.click();
    }

    public void clickProgramsApp(){
    	programs.click();
    }
	public void clickInpatientApp(){
		inpatient.click();
	}

	public void clickOrdersApp() {
		orders.click();
	}

	public void clickImplementerInterfaceApp(){
		implementerInterface.click();
	}

	public void clickRadiologyUploadApp() {
		radiologyUpload.click();
	}

	public void verifyAppPresent(Table application) {
		List<TableRow> rows = application.getTableRows();
		List<String> columnName = application.getColumnNames();
		String value;
		for (TableRow row : rows) {
			value = row.getCell(columnName.get(0)).toLowerCase();

			if(row.getCell(columnName.get(1)).equals("true")) {
				Assert.assertTrue(isElementPresent(value));
			} else {
				Assert.assertFalse(isElementPresent(value));
			}
		}
	}

	private boolean isElementPresent(String value) {
		return driver.findElements(By.xpath(".//*[contains(@id,'"+ value +"')]")).size() != 0;
	}
}
