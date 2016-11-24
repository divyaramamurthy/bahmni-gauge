package org.bahmni.gauge.common.clinical;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
//import javafx.scene.control.Tab;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.TestSpecException;
import org.bahmni.gauge.common.clinical.domain.ObservationForm;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

public class ObservationsPage extends BahmniPage {

    @FindBy(how = How.CSS, using = "#template-control-panel-button")
    public WebElement addFormbutton;

    @FindBy(how = How.CSS, using = "#dashboard-link")
    public WebElement dashboard;

    @FindBy(how = How.CSS, using = ".leaf-observation-node")  //.dashboard-content
    public List<WebElement> observation_nodes;

    @FindBy(how = How.CSS, using = ".template-control-panel")
    public WebElement templatePanel;

    @FindBy(how = How.CSS, using = ".concept-set-title")
    public List<WebElement> observationTemplates;

    @FindBy(how = How.CSS, using = ".save-consultation")
    public WebElement save;


    public void selectTemplate(String templateName) {
        clickTemplateButton();
        List<WebElement> allForms = templatePanel.findElements(By.tagName("button"));

        for (int i = 0; i < allForms.size(); i++) {
            String text = allForms.get(i).getText().replace(" ","_"); //For debugging
            if (text.contains(templateName)) {
                allForms.get(i).click();
                break;
            }
        }
    }

    public WebElement expandObservationTemplate(String templateId) {
        WebElement template = driver.findElement(getSectionWithChildHavingId(templateId));
        WebElement downKey = waitForElementwithTimeOut(driver, ExpectedConditions.presenceOfElementLocated(By.cssSelector("#" + templateId + " h2 i.fa-caret-down:not(.ng-hide)")), 1);
        if (downKey != null) { //checking if down key exists
            return template;
        }

        WebElement expandArrow = driver.findElement(By.cssSelector("#" + templateId + " h2 i.fa-caret-right:not(.ng-hide)"));
        if (null != expandArrow) {
            expandArrow.click();
        }
        waitForSpinner();
        return template;
    }

    public void clickTemplateButton() {
        addFormbutton.click();
    }

    @Deprecated
    public void fillTemplateData(Table table, ObservationForm form) {

        WebElement observ_label = null;
        WebElement answer;
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();

        for (int i = 0; i <= observation_nodes.size() - 1 && i <= columnNames.size() - 1; i++) {
            observ_label = observation_nodes.get(i).findElement(By.tagName("label"));
            if (observ_label.getText().contains(columnNames.get(i))) {
                answer = observation_nodes.get(i).findElement(By.cssSelector(".field-value"));
                if (answer.getText().length() != 0) {
                    if (answer.findElement(By.tagName("button")).isEnabled()) {
                        Point point = answer.findElement(By.tagName("button")).getLocation();
                        answer.findElement(By.tagName("button")).getLocation().move(point.getX(), point.getY());
                        answer.findElement(By.tagName("button")).click();
                    }
                } else {
                    if (answer.findElement(By.tagName("input")).isEnabled()) {
                        Point point = answer.findElement(By.tagName("input")).getLocation();
                        answer.findElement(By.tagName("input")).getLocation().move(point.getX(), point.getY());
                        answer.findElement(By.tagName("input")).sendKeys(rows.get(0).getCell(columnNames.get(i)));
                    }
                }

            }
        }
    }

    private By getSectionWithChildHavingId(String templateId) {
        return By.xpath("//div[contains(@class,\" section-grid\") and descendant::*[@id=\"" + templateId + "\"]]");
    }

    public void enterObservations(String template, Table data) {

        ObservationForm observationForm = new ObservationForm(expandObservationTemplate(template.replace(' ', '_')));
        observationForm.fillUp(data);
        save.click();
        storeObservationFormInSpecStore(observationForm);
    }

    public void navigateToDashboard() {
        dashboard.click();

    }

}
