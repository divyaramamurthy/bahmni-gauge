package org.bahmni.gauge.common.clinical;

import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.clinical.domain.Disposition;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by atmaramn on 25/10/2016.
 */
public class ConsultationTabPage extends BahmniPage {
    @FindBy(how = How.CSS, using = ".disposition .disposition-state")
    public WebElement dispositionDisplayControl;

    public void verifyDisposition(Disposition disposition) {
        Assert.assertEquals("Disposition data dont match", (disposition.getType() + " on " + disposition.getDate()).toLowerCase(), dispositionDisplayControl.getText().toLowerCase());
        findElement(By.cssSelector(".toggle.fr.has-notes")).click();
        Assert.assertTrue("Disposition notes dont match", findElement(By.cssSelector(".disposition .notes")).getText().contains(disposition.getNotes()));
    }

    public String getDisplayControlTextWithCaption(String displayControlCaption) {
        WebElement displayControl = findElement(By.xpath(".//*[contains(text(),\"" + displayControlCaption + "\")]/../../../../../table/tbody"));
        waitForSpinner();
        return displayControl.getText().replace("\n", "");

    }
}
