package org.bahmni.gauge.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public enum FormElement {
    INPUT("input") {
        public void fillUp(WebElement observationNode, String value) {
            WebElement element = observationNode.findElement(getSelector());
            if (!Objects.equals(element.getAttribute("type"), "date"))
                element.clear();
            element.sendKeys(value);
            if (element.getAttribute("role") != null){
                element.sendKeys(Keys.ENTER);
            }
        }
    },
    TEXT_AREA("textarea") {
        public void fillUp(WebElement observationNode, String value) {
            List<WebElement> elementList = observationNode.findElements(getSelector());

            for (WebElement element : elementList) {
                if(element.getText().isEmpty()){
                    element.clear();
                    element.sendKeys(value);
                    break;
                }
            }
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

    DIV_SELECT_SINGLE(".Select--single"){
        @Override
        public void fillUp(WebElement observationNode, String value) {
            WebElement dropdown = (observationNode.findElement(getSelector())).findElement(By.className("Select-input"));
            Actions actions = new Actions(getCurrentDriver(dropdown));
            actions.moveToElement(dropdown).perform();
            Integer index = Integer.parseInt(value);
            for (int i = 0; i < index; i++) {
                dropdown.sendKeys(Keys.ARROW_DOWN);
            }
            dropdown.sendKeys(Keys.ENTER);
        }
    },

    SELECTWITHAUTOCOMPLETE("selectwithautocomplete") {
        public void fillUp(WebElement observationNode, String value) {
            new Select(observationNode.findElement(getSelector())).selectByValue(value);
        }
    },
    UNKNOWN("") {
        public void fillUp(WebElement observationNode, String value) {
            System.out.println("Value :" + value + " not entered.");
        }
    };

    public static final FormElement[] allTypes = {INPUT, TEXT_AREA, SELECT, BUTTON, DIV_SELECT_SINGLE};

    private final String cssSelector;
    FormElement(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    abstract public void fillUp(WebElement observationNode, String value);

    public By getSelector() {
        return By.cssSelector(this.cssSelector);
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
