package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {
    String user = "standard_user";
    String password = "secret_sauce";
    String productName = "Sauce Labs Backpack";
    String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};

    @Test(testName = "Проверка добавления товара в корзину",
            description = "Проверка позитивного добавления товара в корзину.",
            priority = 1,
            groups = {"cart"})
    @Description("Проверка позитивного добавления товара в корзину.")
    public void addItemToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        softAssert.assertEquals(
                cartPage.getItemCount(),
                1,
                "Количество товаров в корзине должно быть 1, после добавления.");
        softAssert.assertEquals(cartPage.getItemName(0),
                productName,
                "Название товара должно совпадать с добавленным товаром");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка добавления нескольких товаров в корзину",
            description = "Проверка позитивного добавления товаров в корзину",
            priority = 2,
            groups = {"cart"})
    @Description("Проверка позитивного добавления нескольких товаров в корзину")
    public void addMultipleItemsToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
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

    @Test(testName = "Проверка стоимости товара в корзине",
            description = "Стоимость товара должна совпадать с ожидаемой ценой.",
            priority = 3,
            groups = {"cart"})
    @Description("Стоимость товара должна совпадать с ожидаемой ценой.")
    public void checkItemPricesInCart() {
        SoftAssert softAssert = new SoftAssert();
        String expectedPrice = "$29.99";
        loginPage.open();
        loginPage.login(user, password);
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        softAssert.assertEquals(cartPage.getItemPrice(0),
                expectedPrice,
                "Стоимость товара в корзине должна совпадать с ожидаемой ценой");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка удаления товара из корзины",
            description = "Товар должен удаляться из корзины",
            priority = 4,
            groups = {"cart"})
    @Description("Проверка удаления товара из корзины.")
    public void removeItemFromCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();
        cartPage.removeItem(productName);

        softAssert.assertTrue(cartPage.isCartEmpty(),
                "Корзина должна быть пустой после удаления товара");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка добавления и удаления товаров из корзины",
            description = "Проверяет, что товары могут быть добавлены и затем удалены из корзины.",
            priority = 5,
            groups = {"cart"})
    @Description("Проверяет, что товары могут быть добавлены и затем удалены из корзины.")
    public void addAndRemoveMultipleItems() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);

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

    @Test(testName = "Проверка работы кнопки 'ContinueShopping' для последующего добавления товаров в корзину",
            description = "Проверяет, что кнопка 'Continue Shopping' возвращает пользователя на страницу товаров.",
            priority = 6,
            groups = {"cart"})
    @Description("Проверяет, что кнопка 'Continue Shopping' возвращает пользователя на страницу товаров.")
    public void continueShopping() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        cartPage.clickContinueShopping();

        softAssert.assertEquals(productsPage.getTitle(),
                "Products",
                "Название страницы должно быть 'Products' после продолжения покупок.");
        softAssert.assertAll();
    }
}