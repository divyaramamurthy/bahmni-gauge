package org.bahmni.gauge.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public enum FormElement {
    INPUT("input"){
        public void fillUp(WebElement observationNode, String value){
            observationNode.findElement(getSelector()).sendKeys(value);
        }
    },
    TEXT_AREA("textarea"){
        public void fillUp(WebElement observationNode, String value){
            observationNode.findElement(getSelector()).sendKeys(value);
        }
    },
    SELECT("select"){
        public void fillUp(WebElement observationNode, String value){
            new Select(observationNode.findElement(getSelector())).selectByVisibleText(value);
        }
    },
    BUTTON("button"){
        public void fillUp(WebElement observationNode, String value){
            for(WebElement button: observationNode.findElements(getSelector())){
                if (button.getText().contains(value)){
                    button.click();
                    break;
                }
            }
        }
    },
    UNKNOWN(""){
        @Override
        public void fillUp(WebElement observationNode, String value) { System.out.println("Value :"+value+" not entered."); }
    };

    private final String tagName;

    FormElement(String tagName) {
        this.tagName = tagName;
    }

    public static final FormElement[] allTypes = {INPUT,TEXT_AREA, SELECT, BUTTON};

    abstract public void fillUp(WebElement observationNode, String value);

    public By getSelector() {
        return By.tagName(tagName);
    }
}
