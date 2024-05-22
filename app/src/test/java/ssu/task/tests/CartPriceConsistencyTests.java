package ssu.task.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ssu.task.config.BaseTest;
import ssu.task.models.Picture;
import ssu.task.pages.BasketPage;
import ssu.task.pages.HomePage;
import ssu.task.pages.PicturePage;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class CartPriceConsistencyTests extends BaseTest {

    /* ���� 5
     * ������� � ���������� ���������, �������� ������ ������� � �������,
     * ���������, ��� ��������� ����� ��������� � �������, ��������� ������ �� ����������.
     * */
    @Test(testName = "���������� ������� ������� � ������� � ������� '��������� ���������'")
    public void checkAdditionFirstJewelryItemToCartAndPriceConsistency() {
        log.info("Starting test: Adding the first jewelry item to the cart in the " +
                "'��������� ���������' section and verifying its presence and unchanged price.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        BasketPage basket = new BasketPage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("��������� ���������");

        Picture firstPicture = picture.putInBasketSpecificPicture(0);
        basket.openBasketFromModalPage();

        boolean isExistAndEqualPrice = basket.checkPictureInBasket(firstPicture);

        assertTrue(isExistAndEqualPrice);
    }

    /* ���� 6
     * ������� � ���������� ���������, �������� ������ ��� ������� � �������,
     * ���������, ��� ��������� ����� ��������� � �������, ��������� ������ �� ����������.
     * */
    @Test(testName = "���������� ������ ���� ������� � ������� � ������� '��������� ���������'")
    public void checkThatFirstThreeJewelryItemAreAddedToCartAndPriceConsistency() {
        log.info("Starting test: Add the first three items to the cart in the " +
                "'��������� ���������' section and check its availability and constant price.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        BasketPage basket = new BasketPage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("��������� ���������");

        Picture firstPicture = picture.putInBasketSpecificPicture(0);
        basket.continueBuy();
        Picture secondPicture = picture.putInBasketSpecificPicture(1);
        basket.continueBuy();
        Picture thirdPicture = picture.putInBasketSpecificPicture(2);
        basket.openBasketFromModalPage();

        boolean isExistAndEqualPrice1 = basket.checkPictureInBasket(firstPicture);
        boolean isExistAndEqualPrice2 = basket.checkPictureInBasket(secondPicture);
        boolean isExistAndEqualPrice3 = basket.checkPictureInBasket(thirdPicture);

        assertTrue(isExistAndEqualPrice1);
        assertTrue(isExistAndEqualPrice2);
        assertTrue(isExistAndEqualPrice3);
    }
}


