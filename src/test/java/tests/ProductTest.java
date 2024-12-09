package tests;

import io.qameta.allure.Description;
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

    @Test(testName = "Проверка выхода из меню",
            description = "Проверяет, что пользователь может выйти через меню бургера.",
            priority = 1,
            groups = {"product"})
    @Description("Проверяет, что пользователь может выйти через меню бургера.")
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

    @Test(testName = "Проверка сортировки товаров по имени A-Z",
            description = "Проверяет, что товары сортируются по имени от A до Z.",
            priority = 2,
            groups = {"product"})
    @Description("Проверяет, что товары сортируются по имени от A до Z.")
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

    @Test(testName = "Проверка сортировки товаров по имени Z-A",
            description = "Проверяет, что товары сортируются по имени от Z до A.",
            priority = 3,
            groups = {"product"})
    @Description("Проверяет, что товары сортируются по имени от Z до A.")
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

    @Test(testName = "Проверка сортировки товаров по цене от низкой к высокой",
            description = "Проверяет, что товары сортируются по цене от низкой к высокой.",
            priority = 4,
            groups = {"product"})
    @Description("Проверяет, что товары сортируются по цене от низкой к высокой.")
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

    @Test(testName = "Проверка сортировки товаров по цене от высокой к низкой",
            description = "Проверяет, что товары сортируются по цене от высокой к низкой.",
            priority = 5,
            groups = {"product"})
    @Description("Проверяет, что товары сортируются по цене от высокой к низкой.")
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