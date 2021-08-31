package org.bahmni.gauge.common;

import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bahmni.gauge.common.admin.domain.OrderSet;
import org.bahmni.gauge.common.formBuilder.domain.Form;
import org.bahmni.gauge.common.registration.RegistrationFirstPage;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.data.StoreHelper;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;

public class DriverFactorySpec {

    private static WebDriver driver;

    // TODO: Remove this static method!!!!!
    public static WebDriver getDriver() {

        return driver;

    }

    @BeforeSpec
    public void setup() {
        if ("true".equals(System.getenv("RUNS_IN_DOCKER"))) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");

            ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort()
                    .withEnvironment(ImmutableMap.of("DISPLAY", ":99"))
                    .usingDriverExecutable(new File("/usr/local/bin/chromedriver")).build();

            try {
                service.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            driver = new ChromeDriver(service, options);
        } else {
            System.out.print("Driver about to start");
            // ChromeDriverManager.getInstance().setup();
            WebDriverManager.chromedriver().setup();

            DesiredCapabilities capability = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors");
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new ChromeDriver(options);
            System.out.print("Driver started successfully");
           
        }

        driver.manage().window().setSize(new Dimension(1440, 1200));

    }

    @AfterSpec
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @AfterScenario
    public void tearDownScenario() {
        Patient patient = new RegistrationFirstPage().getPatientFromSpecStore();
        if (patient != null) {
            if (patient.isAdmitted()) {
                if (BahmniRestClient.get().dischargePatient(patient)) {
                    patient.setBedNumber(null);
                    BahmniRestClient.get().retirePatient(patient);
                }
            } else
                BahmniRestClient.get().retirePatient(patient);

        }
        OrderSet orderSet = new BahmniPage().getOrderSetInSpecStore();
        if (orderSet != null) {
            BahmniRestClient.get().retireOrderSet(orderSet);
        }

        List<Form> forms = StoreHelper.getAll(Form.class);
        for(Form form : forms) {
            if(form != null) {
                BahmniRestClient.get().retireObsForm(form);
            }
        }
    }
}
