package ssu.task.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import ssu.task.config.AllureListener;

import java.util.List;

import static ssu.task.utils.FindElementSupport.findByLabel;

@Listeners(AllureListener.class)
public class CatalogPage extends BasePage {

    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id='genrebox']/span[1]/span[1]")
    private WebElement showMoreOnGenreButton;

    @FindBy(xpath = "//div[@id='genrebox']/div")
    private WebElement genreItemList;

    @FindBy(xpath = "//div[@id='applymsg']")
    private WebElement applyGenreButton;

    @FindBy(xpath = "//*[@id='sa_container']/div[@class='post']")
    private List<WebElement> pictureList;

    @Step("Select picture genre")
    public void selectPictureGenre(String genre) {
        WebElement element = findByLabel(genreItemList, genre);
        waitForElementEnable(element);
        element.click();

        waitForElementEnable(applyGenreButton);
        applyGenreButton.click();
    }

    @Step("Check catalog item name")
    public boolean selectCatalogItemName(String catalogItemName) {
        waitForAllElementsTimeoutSeconds(pictureList);

        for (var picture : pictureList) {
            if (picture.getText().contains(catalogItemName)) {
                return true;
            }
        }

        return false;
    }
}
