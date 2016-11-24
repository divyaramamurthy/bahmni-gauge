package org.bahmni.gauge.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Field;

public enum FormElement {
    INPUT("input") {
        public void fillUp(WebElement observationNode, String value) {
            observationNode.findElement(getSelector()).sendKeys(value);
        }
    },
    TEXT_AREA("textarea") {
        public void fillUp(WebElement observationNode, String value) {
            observationNode.findElement(getSelector()).sendKeys(value);
        }
    },
    SELECT("select") {
        public void fillUp(WebElement observationNode, String value) {
            new Select(observationNode.findElement(getSelector())).selectByVisibleText(value);
        }
    },
    BUTTON("button") {
        public void fillUp(WebElement observationNode, String value) {
            for (WebElement button : observationNode.findElements(getSelector())) {
                if (button.getText().contains(value)) {
                    try {
                        button.click();
                        break;
                    } catch (WebDriverException e) {
                        JavascriptExecutor js = ((JavascriptExecutor) getCurrentDriver(button));
                        js.executeScript("scrollBy(0,2500)");
                        Actions actions = new Actions(getCurrentDriver(button));
                        actions.moveToElement(button).click().build().perform();
                        break;
                    }
                }
            }
        }
    },
    UNKNOWN("") {
        @Override
        public void fillUp(WebElement observationNode, String value) {
            System.out.println("Value :" + value + " not entered.");
        }
    };

    private final String tagName;

    FormElement(String tagName) {
        this.tagName = tagName;
    }

    public static final FormElement[] allTypes = {INPUT, TEXT_AREA, SELECT, BUTTON};

    abstract public void fillUp(WebElement observationNode, String value);

    public By getSelector() {
        return By.tagName(tagName);
    }

    public WebDriver getCurrentDriver(WebElement element) {
        WebDriver driver = null;
        try {
            Field f = element.getClass().getDeclaredField("parent");
            f.setAccessible(true);
            Object obj = f.get(element);
            if (obj instanceof WebDriver) {
                driver = (WebDriver) obj;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
