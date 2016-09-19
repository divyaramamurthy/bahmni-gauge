package org.bahmni.gauge.possible.clinical.domain;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.FormElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ObservationForm {
    private final List<WebElement> observationNodes;

    public ObservationForm(WebElement observationForm) {
        observationNodes = observationForm.findElements(By.cssSelector(".leaf-observation-node"));
    }

    public void fillUp(Table table) {
        List<TableRow> tableRows = table.getTableRows();
        for (TableRow tableRow : tableRows) {
            String fieldName = tableRow.getCell("key");
            String fieldValue = tableRow.getCell("value");
            for (WebElement fieldset : observationNodes) {
                if(hasField(fieldset, fieldName)){
                    getFieldType(fieldset).fillUp(fieldset,fieldValue);
                }
            }
        }
    }

    private static FormElement getFieldType(WebElement fieldset) {
        for(FormElement type : FormElement.allTypes){
            if(hasChild(fieldset,type.getSelector())){
                return type;
            }
        }
        return FormElement.UNKNOWN;
    }

    private static boolean hasField(WebElement fieldset, String fieldName) {
        return fieldset.findElement(By.tagName("label")).getText().contains(fieldName);
    }

    private static boolean hasChild(WebElement parent, By childSelector) {
        try{
            return null != parent.findElement(childSelector);
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
