package org.bahmni.gauge.endtb.clinical;

import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.clinical.domain.Specimen;
import org.bahmni.gauge.util.StringUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.bahmni.gauge.common.clinical.BacteriologyPage;


import java.util.List;

public class EndTBBacteriologyPage extends BacteriologyPage{

    @FindBy(how= How.CSS, using = ".fa-caret-right.ng-scope")
    public WebElement results;

    public void addSamples(List<Specimen> specimens) {
        int index=0;
        for(Specimen specimen:specimens){
            enterDetails(index++,specimen);
        }

    }

    @Override
    public void enterDetails(int index,Specimen specimen){

        if(index!=0){
            scrollToTop();
            driver.findElements(By.cssSelector(".add-more-concept-set")).get(0).click();
        }
        WebElement form=driver.findElements(By.cssSelector(".form")).get(index);

        form.findElement(By.cssSelector("#sample-date")).sendKeys(specimen.getDateOfSampleCollection());

        waitForSpinner();

        form.findElement(By.cssSelector(".grid-row-element[title=\""+specimen.getTypeOfSample()+"\"]")).click();

        if(specimen.getIdentifier() != null) {

            WebElement sampleId = form.findElement(By.cssSelector("#sample-id"));
            sampleId.clear();
            sampleId.sendKeys(specimen.getIdentifier());
        }

        if(specimen.getSmearResult() != null) {
            //expand All sections
            try {
                form.findElement(By.cssSelector(".fa-caret-right")).click();

                findElement(By.xpath(".//strong[contains(text(),\""+"Smear Microscopy Test Results"+"\")]")).click();

            } catch (NoSuchElementException ex) {

            }

            form.findElement(By.cssSelector(".grid-row-element[title=\"" + specimen.getSmearResult() + "\"]")).click();
        }

        if(specimen.getCultureResults() != null) {
            //expand All sections
            try {

                form.findElement(By.cssSelector(".fa-caret-right")).click();

                findElement(By.xpath(".//strong[contains(text(),\""+"Culture Results"+"\")]")).click();

            } catch (NoSuchElementException ex) {

            }
            form.findElement(By.cssSelector(".grid-row-element[title=\"" + specimen.getCultureResults()  + "\"]")).click();
        }
        if(specimen.getMICInterpretation() != null){
            try{
                form.findElement(By.cssSelector(".fa-caret-right")).click();
                findElement(By.xpath(".//strong[contains(text(),\""+"Non standard DST with MIC"+"\")]")).click();
            }
            catch(NoSuchElementException e){
            }
            form.findElements(By.cssSelector(".grid-row-element[title=\"" + specimen.getMICInterpretation()  + "\"]")).get(25).click();
            form.findElements(By.cssSelector(".grid-row-element[title=\"" + specimen.getMICInterpretation()  + "\"]")).getClass();
        }
    }

    public void verifyBacteriologySamples(String displayControlText, Table table) {
        for (String drugOrder : table.getColumnValues("details")) {
            drugOrder = StringUtil.transformPatternToData(drugOrder);
            Assert.assertTrue(StringUtil.stringDoesNotExist(drugOrder, displayControlText),displayControlText.contains(drugOrder));
        }

    }

    public void clickOnElementByID() {

        findElement(By.cssSelector("span.specimenSource.toggle-container")).click();


    }


    /*public org.bahmni.gauge.common.registration.domain.Patient getPatientFromSpecStore() {
    }*/
}
