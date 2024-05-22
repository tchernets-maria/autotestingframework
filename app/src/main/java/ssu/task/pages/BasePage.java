package ssu.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 100;
    private static final int DEFAULT_TIME_OF_SECONDS = 1000;

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public BasePage(WebDriver driver) {
        driverThreadLocal.set(driver);
        PageFactory.initElements(driver, this);
    }

    protected void waitForAllElementsTimeoutSeconds(List<WebElement> elements) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitForElementEnable(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void timeSleep() {
        try {
            Thread.sleep(DEFAULT_TIME_OF_SECONDS);
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}
