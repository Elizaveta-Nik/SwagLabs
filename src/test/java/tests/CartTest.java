package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart() {
        String productName = "Sauce Labs Backpack";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        assertEquals(cartPage.getItemCount(), 1);
        assertEquals(cartPage.getItemName(0), productName);
    }

    @Test
    public void addMultipleItemsToCart() {
        String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }
        productsPage.clickShoppingCart();

        assertEquals(cartPage.getItemCount(), productNames.length);
        for (int i = 0; i < productNames.length; i++) {
            assertEquals(cartPage.getItemName(i), productNames[i]);
        }
    }

    @Test
    public void checkItemPricesInCart() {
        String productName = "Sauce Labs Backpack";
        String expectedPrice = "$29.99";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();

        assertEquals(cartPage.getItemPrice(0), expectedPrice);
    }

    @Test
    public void removeItemFromCart() {
        String productName = "Sauce Labs Backpack";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton(productName);
        productsPage.clickShoppingCart();
        cartPage.removeItem(productName);

        assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void addAndRemoveMultipleItems() {
        String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String productName : productNames) {
            productsPage.clickAddButton(productName);
        }
        productsPage.clickShoppingCart();

        for (String productName : productNames) {
            cartPage.removeItem(productName);
        }

        assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void continueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickShoppingCart();

        cartPage.clickContinueShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }
}