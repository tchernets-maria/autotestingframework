package ssu.task.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ssu.task.config.BaseTest;
import ssu.task.models.Picture;
import ssu.task.pages.BasketPage;
import ssu.task.pages.FavoritePage;
import ssu.task.pages.HomePage;
import ssu.task.pages.PicturePage;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class AddToFavoritesTests extends BaseTest {

    /* Тест 3
     * Перейти в “Батик”, потом добавить первую картину в избранное, проверить,
     * что выбранная картина сохранилась в разделе «Избранное».
     * */
    @Test(testName = "Проверка добавления первой картины в разделе 'Батик' в избранное")
    public void checkAddingFirstBatikPictureToFavorites() {
        log.info("Starting test: Verify adding the first 'Батик' picture to Favorites");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        FavoritePage favoritePage = new FavoritePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Батик");

        String pictureName = picture.putInFavoriteSpecificPicture(0);

        favoritePage.openFavoriteListPage();
        boolean isExist = favoritePage.checkPictureInFavorite(pictureName);

        assertTrue(isExist);
    }

    /* Тест 7
     * Перейти в "Батик", потом добавить первую картину в избранное, проверить,
     * что выбранная картина сохранилась в разделе "Избранное".
     * */
    @Test(testName = "Неудачная проверка добавления первой картины в разделе 'Батик' в избранное")
    @Description("Результат специально был сделан ошибочным")
    @Attachment(value = "Failure screenshot", type = "image/png")
    public void checkAddingFirstBatikPictureToFavoritesBadResult() {
        log.info("Starting test: Verify adding the first 'Батик' picture to Favorites");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        FavoritePage favoritePage = new FavoritePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Батик");

        String pictureName = picture.putInFavoriteSpecificPicture(0);

        favoritePage.openFavoriteListPage();
        boolean isExist = favoritePage.checkPictureInFavorite(pictureName);

        try {
            assertFalse(isExist);
        } catch (AssertionError e) {
            byte[] bytes = captureScreenshot(getDriver());
            Allure.addAttachment("Failure screen", new ByteArrayInputStream(bytes));
            Allure.addAttachment("Failure details", "Тест завершился неудачей: " + e.getMessage());
            throw e;
        }
    }
}
