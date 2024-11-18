package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By CART_ITEMS = By.cssSelector(".cart_item");
    private final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    private final By ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private final String REMOVE_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button[text() = 'Remove']";
    private final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[@id='continue-shopping']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    public String getItemName(int index) {
        return driver.findElements(ITEM_NAME).get(index).getText();
    }

    public String getItemPrice(int index) {
        return driver.findElements(ITEM_PRICE).get(index).getText();
    }

    public void removeItem(String itemName) {
        By removeButton = By.xpath(String.format(REMOVE_BUTTON, itemName));
        driver.findElement(removeButton).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(CART_ITEMS).isEmpty();
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
}