package org.bahmni.gauge.common.clinical.displaycontrol;

import org.bahmni.gauge.common.clinical.domain.DrugRegimenRow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DrugRegimenTest {

    @Mock
    private WebElement regimenElement;

    @Mock
    private WebElement headerElement;

    @Mock
    private WebElement errorMessageElement;

    @Mock
    private WebElement drugRegimenSection;

    @Mock
    private WebElement drug;

    @Mock
    private WebElement regimenMonth;

    @Mock
    private WebElement regimenDate;

    @Mock
    private WebElement spanTag = new RemoteWebElement();

    private static DateFormat ddMMMyyDateFormat = new SimpleDateFormat("dd MMM yy", Locale.ENGLISH);

    @Before
    public void setUp() {
        initMocks(this);
    }

    /*@Test
    public void ensureThatTitleIsReturnedProperly() {
        DrugRegimen drugRegimen = new DrugRegimen(regimenElement);
        when(regimenElement.findElement(By.tagName("h2"))).thenReturn(headerElement);
        when(headerElement.getText()).thenReturn("Drug Regimen");

        assertEquals("Drug Regimen", drugRegimen.getTitle());
    }

    @Test
    public void ensureThatPlaceHolderTextIsReturnedProperly() {
        DrugRegimen drugRegimen = new DrugRegimen(regimenElement);
        when(regimenElement.findElement(By.xpath("//p[@translate='DISPLAY_CONTROL_ERROR_MESSAGE_KEY']")))
                .thenReturn(errorMessageElement);
        when(errorMessageElement.getText()).thenReturn("No data captured");
        assertEquals("No data captured", drugRegimen.getNoRecordsFoundText());
    }

    @Test
    public void ensureThatPlaceHolderTextIsNullWhenNotFound() {
        DrugRegimen drugRegimen = new DrugRegimen(regimenElement);
        when(regimenElement.findElement(By.xpath("//p[@translate='DISPLAY_CONTROL_ERROR_MESSAGE_KEY']")))
                .thenReturn(null);
        assertNull(drugRegimen.getNoRecordsFoundText());
    }

    @Test
    public void ensureGetDrugRegimenRowsReturnsCorrectRegimenRows() throws ParseException {
        List<WebElement> drugRows = new ArrayList<>();
        drugRows.add(drug);

        when(drugRegimenSection.findElements(By.className("regimen-row"))).thenReturn(drugRows);
        when(drug.findElement(By.className("current-month"))).thenReturn(regimenMonth);
        when(drug.findElement(By.className("regimen-date"))).thenReturn(regimenDate);
        when(drug.findElements(By.className("regimen-drug"))).thenReturn(drugRows);
        when(drug.getAttribute("id")).thenReturn("DrugName");
        when(drug.findElement(By.tagName("span"))).thenReturn(spanTag);
        when(spanTag.getText()).thenReturn("Value");
        when(regimenDate.getText()).thenReturn("05 Aug 16");
        when(regimenMonth.getText()).thenReturn("5.5");

        DrugRegimen drugRegimen = new DrugRegimen(drugRegimenSection);

        List<DrugRegimenRow> rows = drugRegimen.getDrugRegimenRows();
        assertEquals(rows.size(),1);
        DrugRegimenRow row = rows.get(0);
        assertEquals(row.getMonth() == 5.5, true);
        assertEquals(ddMMMyyDateFormat.parse("05 Aug 16").equals(row.getRegimenDate()), true);
        assertEquals(row.getDrugOGram().containsKey("DrugName"), true);
        assertEquals(row.getDrugOGram().get("DrugName"), "Value");
    }

    @Test
    public void ensureGetDrugRegimenRowsReturnsZeroRegimenRowsWhenNoDrugs() throws ParseException {
        List<WebElement> drugRows = new ArrayList<>();
        drugRows.add(drug);

        when(drugRegimenSection.findElements(By.className("regimen-row"))).thenReturn(drugRows);
        when(drug.findElement(By.className("current-month"))).thenReturn(regimenMonth);
        when(drug.findElement(By.className("regimen-date"))).thenReturn(regimenDate);
        when(drug.findElements(By.className("regimen-drug"))).thenReturn(drugRows);
        when(drug.getAttribute("id")).thenReturn(null);

        DrugRegimen drugRegimen = new DrugRegimen(drugRegimenSection);

        List<DrugRegimenRow> rows = drugRegimen.getDrugRegimenRows();
        assertEquals(rows.size(),0);
    }*/
}