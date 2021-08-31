package org.bahmni.gauge.common.clinical;

import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.clinical.displaycontrol.DispositionDisplayControl;
import org.bahmni.gauge.common.clinical.displaycontrol.ObsDisplayControl;
import org.bahmni.gauge.common.clinical.displaycontrol.ProgramsDisplayControl;
import org.bahmni.gauge.common.clinical.domain.Disposition;
import org.bahmni.gauge.common.clinical.domain.DrugOrder;
import org.bahmni.gauge.common.program.domain.Program;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.data.StoreHelper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;

import static java.util.Calendar.*;

public class DashboardPage extends BahmniPage {

    public static final String URL = BASE_URL.concat("/bahmni/clinical/#/default/patient/%s/dashboard?enrollment=%s");

    @FindBy(how = How.CSS, using = ".btn--success")
    public WebElement clinical;

    @FindBy(how = How.CSS, using = "a[title=\"Go to IPD Dashboard\"]")
    public WebElement adtButton;

    @FindBy(how = How.ID, using = "addDashboardButton")
    public WebElement addDashboardButton;


    @FindBy(how = How.CSS, using = ".dashboard-section")
    public List<WebElement> displayControls;

    @FindBy(how = How.CSS, using = ".tab-item")
    public List<WebElement> tabItems;

    @FindBy(how = How.CSS, using = ".patient-dashboard")
    public WebElement dashboardSections;

    @FindBy(how = How.CSS, using = ".bacteriology-dashboard")
    public WebElement bacteriology_results;

    @FindBy(how = How.CSS, using = ".tab-selected")
    public WebElement selectedTab;

    @FindBy(how = How.CSS, using = ".patient-value .patient-name")
    public WebElement patientNameAndID;

    @FindBy(how = How.CSS, using = ".patient-gender-age")
    public WebElement patientGenderAndAge;

    public void selectDashboard(String name) {
        for (WebElement dashboardLink : driver.findElements(By.cssSelector(".tab-item a"))) {
            if (dashboardLink != null && dashboardLink.getText().trim().equals(name)) {
                dashboardLink.click();
            }
        }
    }

    public boolean isEnterDataPresent() {
        try {
            return clinical.isDisplayed();

        } catch (Exception ex) {
            return false;
        }
    }

    public void clickEnterData() {
        waitForElementOnPage(clinical).click();
    }

    public WebElement findElementById(String id) {
        return dashboardSections.findElement(By.id(id));
    }

    public void validateObservationDisplayControl(String displayControl) {
        for (WebElement dispControls : displayControls) {
            if (dispControls.getText().contains(displayControl)) {
                Assert.assertTrue(dispControls.getText().contains(displayControl));
            }
        }
    }

    public void validateVitalsObservationDisplayControl() {
        for (WebElement dispControls : displayControls) {
            if (dispControls.getText().contains("Vitals")) {
                Assert.assertTrue(dispControls.getText().contains("Vitals"));
                ObsDisplayControl obsDisplayControl = new ObsDisplayControl(dispControls);
                Map<String, String> actualData = obsDisplayControl.getKeyValues();
                Map<String, String> expectedData = getObservationFormInSpecStore().getData();
                Assert.assertEquals("Data count for Vitals display control dont match", expectedData.size(), actualData.size());
                for (String key : expectedData.keySet()) {
                    Assert.assertTrue(key + " Field not found in actual data", expectedData.containsKey(key));
                    if (actualData.containsKey(key))
                        Assert.assertTrue(key + " Field value didnt match", actualData.get(key).contains(expectedData.get(key)));
                }
                break;

            }
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

    public String getDisplayControlText(String displayControlId) {
        WebElement displayControl = findElement(By.id(displayControlId));
        waitForSpinnerOnDisplayControl();
        return displayControl.getText().replace("\n", "");
    }

    public String getDialogText(By parent) {
        WebElement dialog = findElement(parent);
        waitForSpinner();
        return dialog.getText().replace("\n", "");
    }

    public void validateProgramsDisplayControl(Program program) {
        for (WebElement dispControls : displayControls) {
            if (dispControls.getText().contains("Programs")) {
                waitForSpinnerOnDisplayControl();
                (new ProgramsDisplayControl(dispControls)).validateActiveProgram(program);
            }
        }
    }

    public void selectDisplayControl(String name) {
        waitForSpinnerOnDisplayControl();
        if (name.toLowerCase().contains("program"))
            new ProgramsDisplayControl(findElementById("Programs")).validateActiveProgram(this.getProgramFromSpecStore());
        else if (name.toLowerCase().contains("disposition")) {
            new DispositionDisplayControl(findElementById("Disposition")).validate(StoreHelper.getLatest(Disposition.class));
        }

    }

    public void verifyNoValuesDisplayControl(String displayControl, String value) {
        waitForSpinnerOnDisplayControl();
        Assert.assertTrue("Display control has data", hasElement(By.xpath("//*[@id='" + displayControl + "'][contains(.,'" + value + "') or text()='" + value + "']")));
    }

    public void openCurrentVisit() {
        By currentVisit = By.xpath("//i[@id='currentVisitIcon']/parent::a[@class='visit']");
        waitForElementOnPage(currentVisit).click();
    }

    public int getVisitsCount() {
        try {
            List<WebElement> visits = driver.findElements(By.cssSelector("a.visit"));
            return visits.size();
        } catch (NoSuchElementException ex) {
            return 0;
        }
    }

    public void clickDisplayControlHeader(String displayControlId) {
        findElementById(displayControlId).findElement(By.cssSelector("h2")).click();
    }

    public void closeDialog() {
        findElement(By.cssSelector(".ngdialog-close")).click();
    }

    public void expandControlWithCaption(String controlCaption) {
        WebElement displayControl = findElement(By.xpath(".//h2[contains(text(),\"" + controlCaption + "\")]/ancestor::*[1]"));
        for (WebElement expand : displayControl.findElements(By.cssSelector(".fa-caret-right"))) {
            try {
                expand.click();
            } catch (ElementNotVisibleException ex) {

            }
        }
    }

    public String getDisplayControlTextWithCaption(String controlCaption) {
        WebElement displayControl = findElement(By.xpath(".//h2[contains(text(),\"" + controlCaption + "\")]/ancestor::*[1]"));
        waitForSpinner();
        return displayControl.getText().replace("\n", "");
    }

    public String getActiveTab() {
        return selectedTab.getText();
    }

    public void openTab(String tabCaption) {
        waitForSpinner();
        findElement(By.cssSelector("#addDashboardButton")).click();
        findElement(By.xpath(".//a[contains(text(),\"" + tabCaption + "\")]")).click();
    }


    public void verifyPatientDetails(Patient patient) {
        Assert.assertTrue("Patient ID don't match", patientNameAndID.getText().contains(patient.getIdNumber()));
        Assert.assertTrue("First Name don't match", patientNameAndID.getText().contains(patient.getIdNumber()));
        Assert.assertTrue("Last Name don't match", patientNameAndID.getText().contains(patient.getIdNumber()));

        Assert.assertTrue("Gender don't match", patientGenderAndAge.getText().contains(patient.getGender()));


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date dob = null;
        try {
            dob = df.parse(patient.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int age = getDiffYears(new Date(), dob);
        Assert.assertTrue("Age don't match", patientGenderAndAge.getText().contains((age + "")));
    }

    public void openRetrospectiveVisit(String date){
       WebElement visitDate= driver.findElement(By.xpath("(//a[@class='visit']/span[contains(text(),'"+date+"')])[1]"));
        Actions builder = new Actions(driver);
        builder.moveToElement(visitDate).click().perform();
        waitForSpinner();
    }

    private static int getDiffYears(Date today, Date dob) {
        Calendar date1 = getCalendar(dob);
        Calendar date2 = getCalendar(today);
        int diff = date2.get(YEAR) - date1.get(YEAR);
        if (date1.get(MONTH) > date2.get(MONTH) ||
                (date1.get(MONTH) == date2.get(MONTH) && date1.get(DATE) > date2.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
