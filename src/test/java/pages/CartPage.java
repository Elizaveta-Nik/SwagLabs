package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class CartPage extends BasePage {

    private final By CART_ITEMS = By.cssSelector(".cart_item");
    private final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    private final By ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private final String REMOVE_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button[text() = 'Remove']";
    private final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[@id='continue-shopping']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение количества товаров в корзине")
    public int getItemCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    @Step("Получение имени товара по индексу {index}")
    public String getItemName(int index) {
        return driver.findElements(ITEM_NAME).get(index).getText();
    }

    @Step("Получение цены товара по индексу {index}")
    public String getItemPrice(int index) {
        return driver.findElements(ITEM_PRICE).get(index).getText();
    }

    @Step("Удаление товара с именем {itemName} из корзины")
    public void removeItem(String itemName) {
        By removeButton = By.xpath(String.format(REMOVE_BUTTON, itemName));
        driver.findElement(removeButton).click();
    }

    @Step("Проверка, что корзина пуста")
    public boolean isCartEmpty() {
        return driver.findElements(CART_ITEMS).isEmpty();
    }

    @Step("Нажатие на кнопку 'Продолжить покупки'")
    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
}
