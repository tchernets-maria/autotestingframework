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

    /* ���� 3
     * ������� � ������, ����� �������� ������ ������� � ���������, ���������,
     * ��� ��������� ������� ����������� � ������� ����������.
     * */
    @Test(testName = "�������� ���������� ������ ������� � ������� '�����' � ���������")
    public void checkAddingFirstBatikPictureToFavorites() {
        log.info("Starting test: Verify adding the first '�����' picture to Favorites");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        FavoritePage favoritePage = new FavoritePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("�����");

        String pictureName = picture.putInFavoriteSpecificPicture(0);

        favoritePage.openFavoriteListPage();
        boolean isExist = favoritePage.checkPictureInFavorite(pictureName);

        assertTrue(isExist);
    }

    /* ���� 7
     * ������� � "�����", ����� �������� ������ ������� � ���������, ���������,
     * ��� ��������� ������� ����������� � ������� "���������".
     * */
    @Test(testName = "��������� �������� ���������� ������ ������� � ������� '�����' � ���������")
    @Description("��������� ���������� ��� ������ ���������")
    @Attachment(value = "Failure screenshot", type = "image/png")
    public void checkAddingFirstBatikPictureToFavoritesBadResult() {
        log.info("Starting test: Verify adding the first '�����' picture to Favorites");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        FavoritePage favoritePage = new FavoritePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("�����");

        String pictureName = picture.putInFavoriteSpecificPicture(0);

        favoritePage.openFavoriteListPage();
        boolean isExist = favoritePage.checkPictureInFavorite(pictureName);

        try {
            assertFalse(isExist);
        } catch (AssertionError e) {
            byte[] bytes = captureScreenshot(getDriver());
            Allure.addAttachment("Failure screen", new ByteArrayInputStream(bytes));
            Allure.addAttachment("Failure details", "���� ���������� ��������: " + e.getMessage());
            throw e;
        }
    }
}
