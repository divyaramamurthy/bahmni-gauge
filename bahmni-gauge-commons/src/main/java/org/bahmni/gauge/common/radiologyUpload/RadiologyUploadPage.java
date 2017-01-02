package org.bahmni.gauge.common.radiologyUpload;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.BahmniPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.io.IOException;

/**
 * Created by atmaramn on 17/11/2016.
 */
public class RadiologyUploadPage extends BahmniPage{

    public void uploadImage(int visitNumber, Table table) {
        WebElement root=findElement(By.cssSelector(".doc-upload-accordion>li:nth-of-type("+visitNumber+")"));
        for(TableRow row:table.getTableRows()) {
            root.findElement(By.cssSelector(".fa-plus")).click();
            try {
                uploadFile(row.getCell("image"));
                root=findElement(By.cssSelector(".doc-upload-accordion>li:nth-of-type("+visitNumber+")"));
               // Thread.sleep(1500);
                    root.findElements(By.cssSelector(".concept-name.ng-pristine.ng-untouched.ui-autocomplete-input.ng-invalid.ng-invalid-required.ng-valid-selection")).get(0).sendKeys(row.getCell("name"));
                waitForElementOnPage(By.cssSelector(".ui-menu[style*=\"display: block\"] .ui-menu-item>a"));
                driver.findElement(By.cssSelector(".ui-menu[style*=\"display: block\"] .ui-menu-item>a")).click();
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    public void save(){
        findElement(By.cssSelector("button.btn.btn-primary:not([disabled])")).click();
    }

    public void createNewVisit(Table table) {

        findElement(By.cssSelector("#newVisit")).click();


        Select select = new Select(findElement(By.cssSelector("[ng-model=\"newVisit.visitType\"]")));
        select.selectByVisibleText(table.getColumnValues("type").get(0));
        findElement(By.cssSelector("[name=\"startDate\"]")).sendKeys(table.getColumnValues("startDate").get(0));
        findElement(By.cssSelector("[name=\"endDate\"]")).sendKeys(table.getColumnValues("endDate").get(0));
        findElement(By.cssSelector(".btn.btn-primary[for=\"file-browse\"]")).click();

        try {
            uploadFile(table.getColumnValues("image").get(0));

            WebElement root=findElement(By.cssSelector(".doc-upload-accordion>li:nth-of-type(1)"));
            root.findElements(By.cssSelector(".concept-name.ng-pristine.ng-untouched.ui-autocomplete-input.ng-invalid.ng-invalid-required.ng-valid-selection")).get(0).sendKeys(table.getColumnValues("name").get(0));
            waitForElementOnPage(By.cssSelector(".ui-menu[style*=\"display: block\"] .ui-menu-item>a"));
            driver.findElement(By.cssSelector(".ui-menu[style*=\"display: block\"] .ui-menu-item>a")).click();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
