package org.bahmni.gauge.common;

import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.bahmni.gauge.common.clinical.domain.DrugOrder;
import org.bahmni.gauge.common.clinical.domain.ObservationForm;
import org.bahmni.gauge.common.home.HomePage;
import org.bahmni.gauge.common.program.domain.PatientProgram;
import org.bahmni.gauge.common.program.domain.Program;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.List;

public class BahmniPage {

    protected static String BASE_URL = System.getenv("BAHMNI_GAUGE_APP_URL");

    public static final String PATIENT_KEY = "patient";
    public static final String PROGRAM_KEY = "program";
    public static final String PATIENT_PROGRAM_KEY = "patient_program";
    public static final String BASELINE_KEY = "baselineForm";
    public static final String OBSERVATION_KEY = "observation";
    public static final String DRUG_ORDER_KEY = "drug_order";
    protected WebDriver driver;

    protected static WebElement find(Collection<WebElement> elements, String elementText) {
        for (WebElement element : elements) {
            if(element.getText().contains(elementText)){
                return element;
            }
        }
        return null;
    }
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public void storePatientInSpecStore(Patient value) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(PATIENT_KEY, value);
    }

    public Patient getPatientFromSpecStore() {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        return (Patient) specStore.get(PATIENT_KEY);
    }

    public List<DrugOrder> getDrugOrderFromSpecStore() {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        return (List<DrugOrder>) specStore.get(DRUG_ORDER_KEY);
    }

    public void storeProgramInSpecStore(Program program) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(PROGRAM_KEY, program);
    }

    public void storeBaselineFormInSpecStore(ObservationForm baselineForm) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(BASELINE_KEY, baselineForm);
    }

    public void storeDrugOrderInSpecStore(List<DrugOrder> drugOrder) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(DRUG_ORDER_KEY, drugOrder);
    }

    //@Deprecated
    public static void waitForSpinner(WebDriver driver) {
        try {
            waitForElement(driver, ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#overlay")));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForSpinner() {
        waitForSpinner(driver);
    }

    public Program getProgramFromSpecStore() {
        return (Program) DataStoreFactory.getSpecDataStore().get(PROGRAM_KEY);
    }

    public void acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void handleExceptions(WebDriver driver) {
        driver.findElement(By.cssSelector(".error-message-container .show-btn")).click();
    }

    public void validateSystemException(WebDriver driver) {
        Assert.assertNotNull(driver.findElement(By.cssSelector(".error-message-container .show-btn")));
    }

    public void dismissAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void handleAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Inside Alert");
            alert.sendKeys(Keys.ENTER + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForAlertPopup(WebDriver driver) {
        waitForElement(driver, ExpectedConditions.alertIsPresent());
    }

    //instead use without the driver parameter
    @Deprecated
    public void waitForElementOnPage(WebDriver driver, String element) {
        waitForElement(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
    }

    public WebElement waitForElementOnPage(By locator) {
        ExpectedCondition<WebElement> expectedCondition = ExpectedConditions.visibilityOfElementLocated(locator);
        return waitForElement(this.driver, expectedCondition);
    }

    public WebElement waitForElementOnPage(String cssLocator) {
        ExpectedCondition<WebElement> expectedCondition = ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator));
        return waitForElement(this.driver, expectedCondition);
    }

    public static <T> T waitForElement(WebDriver driver, ExpectedCondition<T> expectedCondition) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            return wait.until(expectedCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storeObservationFormInSpecStore(ObservationForm observation) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(OBSERVATION_KEY, observation);
    }

    public ObservationForm getObservationFormInSpecStore() {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        return (ObservationForm) specStore.get(OBSERVATION_KEY);
    }

    public void storePatientProgramInSpecStore(PatientProgram patientProgram) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(PATIENT_PROGRAM_KEY, patientProgram);
    }

    public PatientProgram getPatientProgramFromSpecStore() {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        return (PatientProgram) specStore.get(PATIENT_PROGRAM_KEY);
    }

    public void closeApp(WebDriver driver) {
        driver.quit();
    }

    public void navigateToHomePage(WebDriver driver) {
        driver.get(HomePage.URL);
        dismissAlert(driver);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
