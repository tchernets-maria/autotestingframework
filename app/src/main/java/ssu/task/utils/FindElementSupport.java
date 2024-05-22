package ssu.task.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FindElementSupport {
    public static WebElement findByLabel(WebElement element, String label) {
        return element.findElement(By.xpath(String.format("//label[contains(text(),'%s')]", label)));
    }
    public static WebElement findByLink(WebElement element, String a) {
        return element.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", a)));
    }
}
