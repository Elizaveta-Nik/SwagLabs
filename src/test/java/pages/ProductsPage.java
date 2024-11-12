package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void clickAddButton(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN,product));
        driver.findElement(addToCart).click();
    }

    public void clickShoppingCart() {
        driver.findElement(CART_LINK).click();
    }
}