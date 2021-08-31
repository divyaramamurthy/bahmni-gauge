package org.bahmni.gauge.endtb.specs;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.clinical.DashboardPage;
import org.bahmni.gauge.common.clinical.displaycontrol.ObsDisplayControl;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EndTBDashboardPageSpec extends DashboardPage {

	@Step("Ensure <Treatment-Information> display control with title <Treatment Information> with <01 Jul 16> as Start Date <table>")
	public void validateContentInDisplayControl(String id, String title, String treatmentStartDate, Table table)
			throws ParseException {
		DashboardPage dashboardPage = PageFactory.get(DashboardPage.class);
		dashboardPage.waitForElementOnPage("#"+id);

		WebElement displayControl = dashboardPage.findElementById(id);
		assertNotNull("The display control with id [" + id + "] not found", displayControl);

		ObsDisplayControl obsDisplayControl = new ObsDisplayControl(displayControl);
		assertEquals("The display control with title [" + title + "] is incorrect", title, obsDisplayControl.getTitle());

		Map<String, String> keyValues = obsDisplayControl.getKeyValues();
		List<String> columnNames = table.getColumnNames();

		String currentMonthOfTreatment = dashboardPage.calculateCurrentMonthOfTreatment(treatmentStartDate);
		table.addRow(Arrays.asList(currentMonthOfTreatment, treatmentStartDate));

		for (String columnName : columnNames) {
			/*assertEquals("The column [" + columnName + "] has incorrect data for the patient [" + dashboardPage
							.getPatientFromSpecStore().getIdNumber() + "]", table.getTableRows().get(0).getCell(columnName),
					keyValues.get(columnName));*/
			assertEquals("The column [" + columnName + "] has incorrect data for the patient [" + dashboardPage.getPatientFromSpecStore()
							.getIdNumber() + "]", table.getTableRows().get(0).getCell(columnName),
					keyValues.get(columnName));
		}
	}

}
