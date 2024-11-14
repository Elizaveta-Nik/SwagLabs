package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    String productName = "Sauce Labs Backpack";
    String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};

    @Test
    public void addItemToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        softAssert.assertEquals(cartPage.getItemCount(),
                1,
                "Количество товаров в корзине должно быть 1, после добавления.");
        softAssert.assertEquals(cartPage.getItemName(0),
                productName,
                "Название товара должно совпадать с добавленным товаром");
        softAssert.assertAll();
    }

    @Test
    public void addMultipleItemsToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }
        productsPage.clickShoppingCart();

        softAssert.assertEquals(cartPage.getItemCount(),
                productNames.length,
                "Количество товаров в корзине должно совпадать с количеством добавленных продуктов");
        for (int i = 0; i < productNames.length; i++) {
            softAssert.assertEquals(cartPage.getItemName(i),
                    productNames[i],
                    "Название товара в корзине должно совпадать с добавленным продуктом");
        }
        softAssert.assertAll();
    }

    @Test
    public void checkItemPricesInCart() {
        SoftAssert softAssert = new SoftAssert();
        String expectedPrice = "$29.99";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        softAssert.assertEquals(cartPage.getItemPrice(0),
                expectedPrice,
                "Стоимость товара в корзине должна совпадать с ожидаемой ценой");
        softAssert.assertAll();
    }

    @Test
    public void removeItemFromCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();
        cartPage.removeItem(productName);

        softAssert.assertTrue(cartPage.isCartEmpty(),
                "Корзина должна быть пустой после удаления товара");
        softAssert.assertAll();
    }

    @Test
    public void addAndRemoveMultipleItems() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }
        productsPage.clickShoppingCart();

        for (String productName : productNames) {
            cartPage.removeItem(productName);
        }

        softAssert.assertTrue(cartPage.isCartEmpty(),
                "Корзина должна быть пустой после удаления всех товаров");
        softAssert.assertAll();
    }

    @Test
    public void continueShopping() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickShoppingCart();

        cartPage.clickContinueShopping();
        softAssert.assertEquals(productsPage.getTitle(),
                "Products",
                "Название страницы должно быть 'Products' после продолжения покупок.");
        softAssert.assertAll();
    }
}