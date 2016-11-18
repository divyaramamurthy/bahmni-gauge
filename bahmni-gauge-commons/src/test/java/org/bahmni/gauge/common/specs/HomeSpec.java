package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.home.HomePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HomeSpec {
	private HomePage homePage;


	@BeforeClassSteps
	public void waitForAppReady(){
		homePage=PageFactory.get(HomePage.class);
		homePage.waitForSpinner();
	}

	@Step("Click on registration app")
	public void goToRegistrationPage(){
		homePage.clickRegistrationApp();
		homePage.waitForSpinner();
	}

	@Step("Click on programs app")
	public void goToProgramsPage(){
		homePage.clickProgramsApp();
	}

	@Step("Click on clinical app")
	public void goToClinicalPage(){
		homePage.clickClinicalApp();
	}

	@Step("Click on inpatient app")
	public void goToInpatientPage(){
		homePage.clickInpatientApp();
	}

	@Step("Click on admin app")
	public void goToAdminPage(){
		homePage.clickAdminApp();
	}

	@Step("Click on orders app")
	public void goToOrdersPage(){
		homePage.clickOrdersApp();
	}
	@Step("Click on radiology upload app")
	public void goToRadiologyUploadPage(){
		homePage.clickRadiologyUploadApp();
	}

	@Step("Logout the user")
	public void logout(){
		homePage.logout();
	}

	@Step("Verify Login Page")
	public void validateLogin() {
		Assert.assertTrue(homePage.registration.isDisplayed());
		Assert.assertTrue(homePage.admin.isDisplayed());
		Assert.assertTrue(homePage.exports.isDisplayed());
	}

}
