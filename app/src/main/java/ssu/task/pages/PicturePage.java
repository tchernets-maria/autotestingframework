package ssu.task.pages;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import ssu.task.config.AllureListener;
import ssu.task.models.Picture;

import java.util.List;

import static ssu.task.utils.PictureServiceSupport.getPictureName;
import static ssu.task.utils.PictureServiceSupport.getPicturePrice;

@Listeners(AllureListener.class)
public class PicturePage extends BasePage {
    @FindBy(xpath = "//*[@id='sa_container']/div[@class='post']")
    private List<WebElement> pictureList;

    @FindBy(xpath = "//div[@class='infocontainer']//div[@class='txtline lft']")
    private List<WebElement> pictureInfo;

    private WebElement favButton;

    @FindBy(css = "#CartButton1127052")
    private WebElement basketButton;

    public PicturePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Check if a picture with name '{pictureName}' exists")
    public boolean isPictureByNameExists(@Param("pictureName") String pictureName) {
        waitForAllElementsTimeoutSeconds(pictureList);

        for (var picture : pictureList) {
            if (picture.getText().contains(pictureName)) {
                return true;
            }
        }

        return false;
    }

    @Step("Open detailed information for the picture with name '{pictureName}'")
    public void openPictureInfo(@Param("pictureName") String pictureName) {
        waitForAllElementsTimeoutSeconds(pictureList);

        for (WebElement picture : pictureList) {
            if (picture.getText().contains(pictureName)) {
                picture.click();
                break;
            }
        }
    }

    @Step("Check if the style of the picture is '{style}'")
    public boolean checkStylePicture(@Param("style") String style) {
        waitForAllElementsTimeoutSeconds(pictureInfo);

        for (WebElement picture : pictureInfo) {
            if (picture.getText().contains("Стиль:") && picture.getText().contains(style)) {
                return true;
            }
        }
        return false;
    }

    @Step("Check if the name of the picture contains the request '{request}'")
    public boolean checkContainsRequestInName(@Param("request") String request) {
        waitForAllElementsTimeoutSeconds(pictureList);

        WebElement picture = null;
        if (!pictureList.isEmpty()) {
            picture = pictureList.get(0);
        }

        assert picture != null;
        String pictureName = getPictureName(picture, "ssize");

        return pictureName.contains(request);
    }

    @Step("Put the picture at index {index} into favorites")
    public String putInFavoriteSpecificPicture(@Param("index") int index) {
        waitForAllElementsTimeoutSeconds(pictureList);

        WebElement picture = null;
        if (pictureList.size() >= index + 1) {
            picture = pictureList.get(index);
        }

        assert picture != null;
        favButton = picture.findElement(By.className("heart"));

        favButton.click();
        timeSleep();
        return getPictureName(picture, "ssize");
    }

    @Step("Put the picture at index {index} into the basket")
    public Picture putInBasketSpecificPicture(@Param("index") int index) {
        waitForAllElementsTimeoutSeconds(pictureList);

        WebElement picture = null;
        if (pictureList.size() >= index + 1) {
            picture = pictureList.get(index);
        }

        assert picture != null;
        basketButton = picture.findElement(By.className("oclick"));

        basketButton.click();
        timeSleep();

        timeSleep();
        return new Picture(getPictureName(picture, "ssize"), getPicturePrice(picture));
    }
}
