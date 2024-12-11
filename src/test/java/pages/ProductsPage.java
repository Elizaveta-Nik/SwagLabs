package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private final By BURGER_MENU = By.className("bm-burger-button");
    private final By LOGOUT_BUTTON = By.id("logout_sidebar_link");
    private final By SORT_CONTAINER = By.className("product_sort_container");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение заголовка страницы.")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Нажатие на кнопку добавления товара {product} в корзину")
    public void clickAddButton(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    @Step("Нажатие на ссылку корзины")
    public void clickShoppingCart() {
        driver.findElement(CART_LINK).click();
    }

    @Step("Нажатие на кнопку бургер-меню")
    public void clickBurgerMenu() {
        WebElement burgerMenuButton = wait.until(ExpectedConditions.elementToBeClickable(BURGER_MENU));
        burgerMenuButton.click();
    }

    @Step("Нажатие на кнопку выхода из аккаунта")
    public void clickLogoutButton() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));
        logoutButton.click();
    }

    @Step("Нажатие на выпадающий список сортировки")
    public void clickSortDropdown() {
        driver.findElement(SORT_CONTAINER).click();
    }


    @Step("Сортировка товаров по параметру {sortParameter}")
    public void sort(String sortParameter) {
        Select sortSelect = new Select(driver.findElement(SORT_CONTAINER));
        sortSelect.selectByVisibleText(sortParameter);
    }

    @Step("Ожидание видимости элемента по локатору {locator}")
    public void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Получение имен товаров")
    public List<String> getProductNames() {
        waitForElementVisibility(By.className("inventory_item_name"));
        List<WebElement> itemNames = driver.findElements(By.className("inventory_item_name"));
        return itemNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Получение цен товаров")
    public List<Double> getProductPrices() {
        waitForElementVisibility(By.className("inventory_item_price"));
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        return priceElements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}