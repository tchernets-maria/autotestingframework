package ssu.task.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ssu.task.config.BaseTest;
import ssu.task.pages.CatalogPage;
import ssu.task.pages.HomePage;
import ssu.task.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
public class SearchResultByGenreTests extends BaseTest {

    /* T��� 1
     * ������� � ������ "������� �������", ��������� ����� �� ����� "��������� ������",
     * � ��������� ������� ������� "���������� ����" � ����������� ������.
     * */
    @Test(testName = "�������� ������� '����������� ����' �� ������� ��������� � ����� '��������� ������'")
    public void checkPresenceOfCityscapePaintingInEmbroideredPictures() {
        log.info("Starting test: Navigating to '������� �������', searching for '��������� ������' genre, " +
                "and verifying the presence of '���������� ����'.");

        HomePage home = new HomePage(getDriver());
        CatalogPage catalog = new CatalogPage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("������� �������");
        catalog.selectPictureGenre("��������� ������");
        boolean isCatalogExists = picture.isPictureByNameExists("���������� ����");

        assertTrue(isCatalogExists);
    }

    /* ���� 2
     * ������� � �������� ��������, ���������� ����� �� ����� ���������� ������, �������
     * ����������� ������� ����������� �����, ���������, ��� ����� ������� ��������.
     * */
    @Test(testName = "�������� ����� '�������' � ������� '���������� ����' � ������� '������� �������'")
    public void checkPaintingStyleTramwayPath() {
        log.info("Starting test: Navigate to '������� �������', search for '��������� ������', " +
                "open details of painting '���������� ����', and verify its style - '�������'.");

        HomePage home = new HomePage(getDriver());
        CatalogPage catalog = new CatalogPage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("������� �������");
        catalog.selectPictureGenre("��������� ������");

        boolean isCatalogExists = picture.isPictureByNameExists("���������� ����");

        if (isCatalogExists) {
            picture.openPictureInfo("���������� ����");
            boolean isRealism = picture.checkStylePicture("�������");

            assertTrue(isRealism);
        }
    }
}
