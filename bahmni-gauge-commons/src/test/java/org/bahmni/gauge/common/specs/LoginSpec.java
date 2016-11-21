package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.login.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSpec {

	LoginPage loginPage;

	@BeforeClassSteps
	public void waitForAppReady(){
		loginPage = PageFactory.get(LoginPage.class);

		loginPage.waitForSpinner();
	}

	@Step("On the login page")
	public void navigateToLoginPage() {
		loginPage.deleteAllCookies();
		loginPage.get(loginPage.LOGIN_URL);
    }

	@Step("Login with username <username> and password <password>")
	public void login(String username, String password){
		loginPage.waitForElementOnPage("#username");
		loginPage.login(System.getenv(username), System.getenv(password));
    }

	@Step("Login with username <username> and password <password> with location <location>")
	public void login(String username, String password, String location){
        LoginPage loginPage = PageFactory.get(LoginPage.class);
		loginPage.waitForElementOnPage("#username");
		loginPage.login(System.getenv(username), System.getenv(password), System.getenv(location));
	}

    @Step("Login to the application")
    public void login() {
        login("BAHMNI_GAUGE_APP_USER", "BAHMNI_GAUGE_APP_PASSWORD");
    }
}
