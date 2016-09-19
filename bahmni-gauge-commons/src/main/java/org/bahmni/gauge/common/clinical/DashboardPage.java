package org.bahmni.gauge.common.clinical;

import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.clinical.domain.DrugOrder;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DashboardPage extends BahmniPage {

	public static final String URL = BASE_URL.concat("/bahmni/clinical/#/default/patient/%s/dashboard?enrollment=%s");

	@FindBy(how = How.CSS, using = ".btn--success")
	public WebElement clinical;

	@FindBy(how = How.CSS, using = ".dashboard-section")
	public List<WebElement> displayControls;

	@FindBy(how = How.CSS, using = ".tab-item")
	public List<WebElement> tabItems;

	@FindBy(how = How.CSS, using = ".patient-dashboard")
	public WebElement dashboardSections;

	@FindBy(how = How.CSS, using = ".bacteriology-dashboard")
	public WebElement bacteriology_results;

	public void selectDashboard(String name) {
		for (WebElement dashboard : tabItems) {
			WebElement dashboardLink = dashboard.findElement(By.tagName("a"));
			if (dashboardLink != null && dashboardLink.getText().trim().equals(name)) {
				dashboardLink.click();
			}
		}
	}

	public void clickEnterData() {
		clinical.click();
	}

	public WebElement findElementById(String id) {
		return dashboardSections.findElement(By.id(id));
	}

	public void validateObservationDisplayControl(String displayControl) {
		for (WebElement dispControls : displayControls) {
			if (dispControls.getText().contains(displayControl))
				Assert.assertTrue(dispControls.getText().contains(displayControl));
		}
	}

	public void validateDrugOrderDisplayControl(DrugOrder drugOrder, String displayControl) {
		for (WebElement dispControls : displayControls) {
			if (dispControls.getText().contains(displayControl))
				Assert.assertTrue(dispControls.getText().contains(drugOrder.getDrugName()));
		}
	}

	public String calculateCurrentMonthOfTreatment(String treatmentStartDate) throws ParseException {
		Date startDate = new SimpleDateFormat("dd MMM yy").parse(treatmentStartDate);
		Date today = new Date();

		DateTime startDateTime = new DateTime(startDate);
		DateTime endDateTime = new DateTime(today);
		Days days = Days.daysBetween(startDateTime, endDateTime);
		return String.format("%.1f", days.getDays() / 30.0F);
	}

	public String getDisplayControlText(String displayControlName) {
		WebElement displayControl = findElement(By.id(displayControlName));
        waitForSpinner();
		return displayControl.getText().replace("\n","");
    }
}
