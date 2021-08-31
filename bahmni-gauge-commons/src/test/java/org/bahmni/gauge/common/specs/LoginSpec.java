package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactorySpec;
import org.bahmni.gauge.common.login.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSpec {

	private final WebDriver driver;

	public LoginSpec(){
		this.driver = DriverFactory.getDriver();
	}

	@BeforeClassSteps
	public void waitForAppReady(){
		BahmniPage.waitForSpinner(driver);
	}

	@Step("On the login page")
	public void navigateToLoginPage() {
		driver.manage().deleteAllCookies();
        driver.get(PageFactorySpec.get(LoginPage.class).LOGIN_URL);
    }

	@Step("Login with username <username> and password <password>")
	public void login(String username, String password){
        LoginPage loginPage = PageFactorySpec.get(LoginPage.class);
		loginPage.waitForElementOnPage("#username");
		loginPage.login(System.getenv(username), System.getenv(password));
    }

	@Step("Login with username <username> and password <password> with location <location>")
	public void login(String username, String password, String location){
        LoginPage loginPage = PageFactorySpec.get(LoginPage.class);
		loginPage.waitForElementOnPage("#username");
		loginPage.login(System.getenv(username), System.getenv(password), System.getenv(location));
	}

	@Step("Login with user <username> and password <password> with location <location>")
	public void loginWith(String username, String password, String location){
		LoginPage loginPage = PageFactorySpec.get(LoginPage.class);
		loginPage.waitForElementOnPage("#username");
		loginPage.login(username, System.getenv(password),System.getenv(location));
	}
	@Step("Close current browser tab")
	public void closeCurrentBrowserTab(){
		LoginPage loginPage = PageFactorySpec.get(LoginPage.class);
		loginPage.closeCurrentBrowserTab();
	}

    @Step("Login to the application")
	public void login() {
		login("BAHMNI_GAUGE_APP_USER", "BAHMNI_GAUGE_APP_PASSWORD");
	}
}
