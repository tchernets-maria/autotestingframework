package ssu.task.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

import static ssu.task.config.TestConfig.SITE_URL;
import static ssu.task.models.BrowsersSupport.CHROME;
import static ssu.task.models.BrowsersSupport.FIREFOX;

@Slf4j
public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriverWait webDriverWait;

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod(description = "Start webdriver, set up browser")
    @Parameters(value = "browser")
    public void setup(String browser) {

        log.info("""
                Initializing browser setup for automated testing...
                Selected site for testing: {}
                """, SITE_URL);
        log.info("Selected browser for testing: {}", browser);

        if (browser.equalsIgnoreCase(FIREFOX.getBrowserName())) {
//            WebDriverManager.firefoxdriver().setup();
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setBrowserVersion(TestConfig.FIREFOX_VERSION);
//            firefoxOptions.addArguments("--headless");
//            firefoxOptions.addArguments("--window-size=1280,800");
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
        } else if (browser.equalsIgnoreCase(CHROME.getBrowserName())) {
            ChromeOptions optionsChrome = new ChromeOptions();
//            optionsChrome.addArguments("--headless");
            optionsChrome.addArguments("--window-size=1280,800");
            optionsChrome.addArguments("--disable-gpu");
            optionsChrome.addArguments("--no-sandbox");
            optionsChrome.addArguments("--allow-insecure-localhost");
            optionsChrome.setBrowserVersion(TestConfig.CHROME_VERSION);
            driverThreadLocal.set(new ChromeDriver(optionsChrome));
        } else {
            throw new IllegalCallerException("You need to add a specific browser in the configurations");
        }

        getDriver().manage().deleteAllCookies();
        webDriverWait = new WebDriverWait(getDriver(), Duration.parse("PT20S"));

        log.info("Navigating to site: {}", SITE_URL);
        getDriver().get(SITE_URL);
    }

    @AfterMethod(description = "Stop browser")
    public void stopMethod() {
        getDriver().quit();
        log.info("Browser successfully quited.");
    }

    @Attachment(value = "Failure screenshot", type = "image/png")
    public static byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}