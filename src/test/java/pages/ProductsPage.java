package pages;

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

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void clickAddButton(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    public void clickShoppingCart() {
        driver.findElement(CART_LINK).click();
    }

    public void clickBurgerMenu() {
        WebElement burgerMenuButton = wait.until(ExpectedConditions.elementToBeClickable(BURGER_MENU));
        burgerMenuButton.click();
    }

    public void clickLogoutButton() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));
        logoutButton.click();
    }

    public void clickSortDropdown() {
        driver.findElement(SORT_CONTAINER).click();
    }

    public void sort(String sortParameter) {
        Select sortSelect = new Select(driver.findElement(SORT_CONTAINER));
        sortSelect.selectByVisibleText(sortParameter);
    }

    public void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<String> getProductNames() {
        waitForElementVisibility(By.className("inventory_item_name"));
        List<WebElement> itemNames = driver.findElements(By.className("inventory_item_name"));
        return itemNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        waitForElementVisibility(By.className("inventory_item_price"));
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        return priceElements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}