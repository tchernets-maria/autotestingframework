package ssu.task.pages;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import ssu.task.config.AllureListener;

import java.util.List;

@Listeners(AllureListener.class)
public class FavoritePage extends BasePage {
    @FindBy(css = "img[alt='Избранное']")
    private WebElement favoriteButton;

    @FindBy(css = "div[class='post']")
    private List<WebElement> favoritePictures;

    public FavoritePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the favorite list page")
    public void openFavoriteListPage() {
        waitForElementEnable(favoriteButton);
        favoriteButton.click();
    }

    @Step("Check if the picture with name '{pictureName}' is in the favorites")
    public boolean checkPictureInFavorite(@Param("pictureName") String pictureName) {
        waitForAllElementsTimeoutSeconds(favoritePictures);

        if (!favoritePictures.isEmpty()) {
            for (var picture : favoritePictures) {
                if (picture.getText().contains(pictureName)) {
                    return true;
                }
            }
        }

        return false;
    }
}
