package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductTest extends BaseTest {

    String user = "standard_user";
    String password = "secret_sauce";

    @Test
    public void checkLogoutFromBurgerMenu() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickBurgerMenu();
        productsPage.clickLogoutButton();

        String actualTitle = driver.getTitle();
        softAssert.assertEquals(actualTitle,
                "Swag Labs",
                "Не получилось попасть на начальную страницу.");
        softAssert.assertAll();
    }

    @Test
    public void checkProductSortByNameAtoZ() {
        SoftAssert softAssert = new SoftAssert();

        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickSortDropdown();
        productsPage.sort("Name (A to Z)");

        List<String> actualNames = productsPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        softAssert.assertEquals(actualNames,
                expectedNames,
                "Товары не отсортированы по имени: (A to Z).");
        softAssert.assertAll();
    }

    @Test
    public void checkProductSortByNameZtoA() {
        SoftAssert softAssert = new SoftAssert();

        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickSortDropdown();
        productsPage.sort("Name (Z to A)");

        List<String> actualNames = productsPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);

        expectedNames.sort(Collections.reverseOrder());

        softAssert.assertEquals(actualNames,
                expectedNames,
                "Товары не отсортированы по имени: (Z to A).");
        softAssert.assertAll();
    }

    @Test
    public void checkSortByPriceLowToHigh() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickSortDropdown();
        productsPage.sort("Price (low to high)");

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> sortedPrices = actualPrices.stream().sorted().collect(Collectors.toList());
        softAssert.assertEquals(actualPrices,
                sortedPrices,
                "Цены не отсортированы по возрастанию.");
        softAssert.assertAll();
    }

    @Test
    public void checkSortByPriceHighToLow() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickSortDropdown();
        productsPage.sort("Price (high to low)");

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> sortedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        softAssert.assertEquals(actualPrices,
                sortedPrices,
                "Цены не отсортированы по убыванию.");
        softAssert.assertAll();
    }
}